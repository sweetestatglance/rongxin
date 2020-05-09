//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.siteManage.model.StModel;
import com.fourfaith.siteManage.service.StModelService;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.sysManage.model.SysEnterprise;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysEnterpriseService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/stModel"})
public class StModelController {
    protected static final String addJsp = "/page/sysmanage/model/add";
    protected static final String editJsp = "/page/sysmanage/model/edit";
    @Autowired
    private StModelService stModelService;
    @Autowired
    private SysEnterpriseService sysEnterpriseService;
    @Autowired
    private StStbprpBService stStbprpBService;
    public String logContent = "";

    public StModelController() {
    }

    @RequestMapping({"list"})
    @ResponseBody
    public String list(HttpServletRequest request) {
        List list = new ArrayList();
        String enterId = request.getParameter("enterId");
        HashMap<String, Object> params = new HashMap();
        params.put("enterpriseid", enterId);
        SysEnterprise enter = this.sysEnterpriseService.findById(enterId);
        Map<String, Object> enterHm = new HashMap();
        enterHm.put("name", enter.getEnterprisename());
        enterHm.put("id", enter.getId());
        enterHm.put("pId", 0);
        enterHm.put("nocheck", true);
        enterHm.put("open", true);
        list.add(enterHm);
        List<StModel> modelList = this.stModelService.getList(params);
        JSONObject jsonObject;
        if (modelList != null && modelList.size() > 0) {
            for(Iterator var9 = modelList.iterator(); var9.hasNext(); list.add(jsonObject)) {
                StModel model = (StModel)var9.next();
                jsonObject = new JSONObject();
                if (model.getPmid().equals("0")) {
                    jsonObject.put("id", model.getId());
                    jsonObject.put("pId", enter.getId());
                    jsonObject.put("name", model.getName());
                    jsonObject.put("open", true);
                } else {
                    jsonObject.put("id", model.getId());
                    jsonObject.put("pId", model.getPmid());
                    jsonObject.put("name", model.getName());
                    jsonObject.put("iconSkin", "deviceIcon");
                }
            }
        }

        return JSONObject.toJSONString(list);
    }

    @RequestMapping({"addPage"})
    public ModelAndView addPage(HttpServletRequest request) {
        String pId = request.getParameter("pId");
        ModelAndView mav = new ModelAndView("/page/sysmanage/model/add");
        mav.addObject("pId", pId);
        return mav;
    }

    @RequestMapping({"editPage"})
    public ModelAndView editPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/model/edit");
        String id = request.getParameter("id");
        StModel stmodel = this.stModelService.findById(id);
        mav.addObject("stModel", stmodel);
        return mav;
    }

    @RequestMapping({"/logAddModel"})
    @ResponseBody
    public String logAddModel(HttpServletRequest request, StModel model) {
        AjaxJson ajax = new AjaxJson();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        String enterId = login_user.getEnterpriseid();
        model.setId(CommonUtil.getRandomUUID());
        model.setModitime(new Date());
        model.setEnterpriseid(enterId);
        RequestContext requestContext = new RequestContext(request);
        Object[] arg = null;
        arg = new Object[]{model.getName()};
        this.logContent = "";

        try {
            Integer result = this.stModelService.add(model);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            this.logContent = requestContext.getMessage("addModelData", arg);
            ajax.setMsg(msg);
            ajax.setSuccess(true);
        } catch (Exception var10) {
            var10.printStackTrace();
            ajax.setMsg(requestContext.getMessage("operateFailedMsg") + var10.getMessage());
            ajax.setSuccess(false);
        }

        ajax.setObj(enterId);
        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"/logEditModel"})
    @ResponseBody
    public String logEditModel(HttpServletRequest request, StModel model) {
        AjaxJson ajax = new AjaxJson();
        model.setModitime(new Date());
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        String enterId = login_user.getEnterpriseid();
        RequestContext requestContext = new RequestContext(request);
        Object[] arg = null;
        arg = new Object[]{model.getName()};
        this.logContent = "";

        try {
            Integer result = this.stModelService.update(model);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            this.logContent = requestContext.getMessage("editModelData", arg);
            ajax.setMsg(msg);
            ajax.setSuccess(true);
            ajax.setObj(model);
        } catch (Exception var10) {
            var10.printStackTrace();
            ajax.setMsg(requestContext.getMessage("operateFailedMsg") + var10.getMessage());
            ajax.setSuccess(false);
        }

        ajax.setObj(enterId);
        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"/delModel"})
    @ResponseBody
    public String delModel(HttpServletRequest request, String id) {
        AjaxJson ajax = new AjaxJson();
        this.logContent = "";
        StModel model = this.stModelService.findById(id);
        Map<String, Object> params = new HashMap();
        params.put("model", id);
        RequestContext requestContext = new RequestContext(request);
        Object[] arg = null;
        arg = new Object[]{model.getName()};
        int count = this.stStbprpBService.getCount(params);
        if (count > 0) {
            ajax.setMsg(requestContext.getMessage("deleteModelFailed"));
            ajax.setSuccess(false);
        } else {
            Integer result = this.stModelService.delete(id);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            ajax.setMsg(msg);
            ajax.setSuccess(true);
            this.logContent = requestContext.getMessage("deleteModelData", arg);
        }

        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"/checkNmExist"})
    @ResponseBody
    public String checkNmExist(HttpServletRequest request, String name, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> params = new HashMap();
        params.put("enterpriseid", login_user.getEnterpriseid());
        params.put("name", name);
        StModel model = this.stModelService.getNm(params);
        if (model != null && !model.getId().equals(id)) {
            ajaxJson.setSuccess(true);
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }
}
