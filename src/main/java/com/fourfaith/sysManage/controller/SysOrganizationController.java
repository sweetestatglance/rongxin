//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fourfaith.sysManage.model.SysEnterprise;
import com.fourfaith.sysManage.model.SysOrganization;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysEnterpriseService;
import com.fourfaith.sysManage.service.SysOrganizationService;
import com.fourfaith.sysManage.service.SysUserService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.Constant;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/sysOrganization"})
public class SysOrganizationController {
    protected static final String addJsp = "/page/sysmanage/organization/add";
    protected static final String editJsp = "/page/sysmanage/organization/edit";
    @Autowired
    private SysOrganizationService sysOrganizationService;
    @Autowired
    private SysEnterpriseService sysEnterpriseService;
    @Autowired
    private SysUserService sysUserService;
    public String logContent = "";

    public SysOrganizationController() {
    }

    @RequestMapping({"/organListToUser"})
    @ResponseBody
    public String organListToUser(HttpServletRequest request, String enterId) {
        RequestContext requestContext = new RequestContext(request);
        Map<String, Object> params = new HashMap();
        JSONArray jsonarray = new JSONArray();
        params.put("enterpriseid", enterId);
        params.put("enabledstate_notEqual", -1);
        SysEnterprise enterPrise = this.sysEnterpriseService.findById(enterId);
        JSONObject enterObject = new JSONObject();
        enterObject.put("id", enterId);
        if (enterPrise.getEnterprisename().length() > 10) {
            enterObject.put("name", enterPrise.getEnterprisename().substring(0, 7) + "..." + "【" + requestContext.getMessage("topArea") + "】");
        } else {
            enterObject.put("name", enterPrise.getEnterprisename() + "【" + requestContext.getMessage("topArea") + "】");
        }

        enterObject.put("pId", 0);
        enterObject.put("open", true);
        List<SysOrganization> list = this.sysOrganizationService.getList(params);
        jsonarray.add(enterObject);
        Iterator var10 = list.iterator();

        while(var10.hasNext()) {
            SysOrganization organ = (SysOrganization)var10.next();
            JSONObject jsonObject = new JSONObject();
            if (organ.getPid().equals("0")) {
                jsonObject.put("id", organ.getId());
                jsonObject.put("pId", enterId);
                if (organ.getOrganname().length() > 10) {
                    jsonObject.put("name", organ.getOrganname().substring(0, 10) + "...");
                } else {
                    jsonObject.put("name", organ.getOrganname());
                }

                jsonObject.put("open", true);
                jsonarray.add(jsonObject);
            } else {
                jsonObject.put("id", organ.getId());
                jsonObject.put("pId", organ.getPid());
                if (organ.getOrganname().length() > 10) {
                    jsonObject.put("name", organ.getOrganname().substring(0, 10) + "...");
                } else {
                    jsonObject.put("name", organ.getOrganname());
                }

                jsonarray.add(jsonObject);
            }
        }

        return JSONObject.toJSONString(jsonarray);
    }

    @RequestMapping({"addPage"})
    public ModelAndView addPage(HttpServletRequest request, String enterId, String organName, String organId, int level) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/organization/add");
        mav.addObject("enterId", enterId);

        try {
            if (StringUtils.isNotEmpty(organName)) {
                organName = URLDecoder.decode(organName, "UTF-8");
                mav.addObject("organName", organName);
            } else {
                mav.addObject("organName", (Object)null);
            }
        } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
        }

        if (organId.equals(enterId)) {
            organId = "0";
        }

        mav.addObject("organId", organId);
        mav.addObject("level", level + 2);
        return mav;
    }

    @RequestMapping({"logAddOrgan"})
    @ResponseBody
    public String logAddOrgan(HttpServletRequest request, SysOrganization organ) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajax = new AjaxJson();
        organ.setId(CommonUtil.getRandomUUID());
        organ.setCreatetime(new Date());
        organ.setUpdatetime(new Date());
        if (organ.getEnabledstate() == null) {
            organ.setEnabledstate(0);
        }

        organ.setEnabledstate(1);
        this.logContent = "";

        try {
            Integer result = this.sysOrganizationService.add(organ);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            this.logContent = requestContext.getMessage("organAddLog", new Object[]{"[" + organ.getOrganname() + "]"});
            if (!organ.getPid().equals("0")) {
                Map<String, Object> params = new HashMap();
                params.put("organizationid", organ.getPid());
                List<SysUser> userList = this.sysUserService.getList(params);
                Iterator var10 = userList.iterator();

                while(var10.hasNext()) {
                    SysUser sysUser = (SysUser)var10.next();
                    sysUser.setOrganizationid(organ.getId());
                    this.sysUserService.update(sysUser);
                }
            }

            ajax.setMsg(msg);
            ajax.setSuccess(true);
        } catch (Exception var11) {
            var11.printStackTrace();
            ajax.setMsg(requestContext.getMessage("optFailExcept") + "：" + var11.getMessage());
            ajax.setSuccess(false);
        }

        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"editPage"})
    public ModelAndView editPage(HttpServletRequest request, String id, String enterId, String parentName) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/organization/edit");
        SysOrganization organ = this.sysOrganizationService.findById(id);
        mav.addObject("sysOrganization", organ);
        mav.addObject("enterId", enterId);

        try {
            if (StringUtils.isNotEmpty(parentName)) {
                parentName = URLDecoder.decode(parentName, "UTF-8");
                mav.addObject("parentName", parentName);
            } else {
                mav.addObject("parentName", (Object)null);
            }
        } catch (UnsupportedEncodingException var8) {
            var8.printStackTrace();
        }

        return mav;
    }

    @RequestMapping({"logEditOrgan"})
    @ResponseBody
    public String logEditOrgan(HttpServletRequest request, SysOrganization organ) {
        RequestContext requestContext = new RequestContext(request);
        AjaxJson ajax = new AjaxJson();
        organ.setUpdatetime(new Date());
        if (organ.getEnabledstate() == null) {
            organ.setEnabledstate(0);
        }

        organ.setEnabledstate(1);
        this.logContent = "";

        try {
            Integer result = this.sysOrganizationService.update(organ);
            String msg = null;
            if (result > 0) {
                msg = requestContext.getMessage("operateSuccess");
            } else {
                msg = requestContext.getMessage("operateFailed");
            }

            this.logContent = requestContext.getMessage("organEditLog", new Object[]{"[" + organ.getOrganname() + "]"});
            ajax.setMsg(msg);
            ajax.setSuccess(true);
        } catch (Exception var7) {
            var7.printStackTrace();
            ajax.setMsg(requestContext.getMessage("optFailExcept") + "：" + var7.getMessage());
            ajax.setSuccess(false);
        }

        return JSONObject.toJSONString(ajax);
    }

    @RequestMapping({"/checkOrganCodeExist"})
    @ResponseBody
    public String checkOrganCodeExist(String organCode, String enterId, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        HashMap map = new HashMap();

        try {
            if (StringUtils.isNotEmpty(organCode)) {
                organCode = URLDecoder.decode(organCode, "UTF-8");
                map.put("organCode", organCode);
            } else {
                map.put("organCode", (Object)null);
            }
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
        }

        map.put("enterpriseid", enterId);
        SysOrganization organ = this.sysOrganizationService.getByOrgan(map);
        if (organ != null && !organ.getId().equals(id)) {
            ajaxJson.setSuccess(true);
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/checkOrganNameExist"})
    @ResponseBody
    public String checkOrganNameExist(String organName, String enterId, String id) {
        AjaxJson ajaxJson = new AjaxJson();
        HashMap map = new HashMap();

        try {
            if (StringUtils.isNotEmpty(organName)) {
                organName = URLDecoder.decode(organName, "UTF-8");
                map.put("organName", organName);
            } else {
                map.put("organName", (Object)null);
            }
        } catch (UnsupportedEncodingException var7) {
            var7.printStackTrace();
        }

        map.put("enterpriseid", enterId);
        SysOrganization organ = this.sysOrganizationService.getByOrgan(map);
        if (organ != null && !organ.getId().equals(id)) {
            ajaxJson.setSuccess(true);
        } else {
            ajaxJson.setSuccess(false);
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"logDelOrgan"})
    @ResponseBody
    public String logDelOrgan(HttpServletRequest request, String id) {
        AjaxJson ajax = new AjaxJson();
        RequestContext requestContext = new RequestContext(request);
        this.logContent = "";
        Map<String, Object> params = new HashMap();
        params.put("organizationid", id);
        params.put("enabledstate_notEqual", Constant.delStatus);
        List<SysUser> userList = this.sysUserService.getList(params);
        if (userList != null && userList.size() > 0) {
            ajax.setMsg(requestContext.getMessage("organDeleteErr"));
            ajax.setSuccess(false);
        } else {
            try {
                SysOrganization org = this.sysOrganizationService.findById(id);
                Integer result = this.sysOrganizationService.delete(id);
                String msg = null;
                if (result > 0) {
                    msg = requestContext.getMessage("operateSuccess");
                } else {
                    msg = requestContext.getMessage("operateFailed");
                }

                this.logContent = requestContext.getMessage("organDeleteLog", new Object[]{"[" + org.getOrganname() + "]"});
                ajax.setMsg(msg);
                ajax.setSuccess(true);
            } catch (Exception var10) {
                var10.printStackTrace();
                ajax.setMsg(requestContext.getMessage("optFailExcept") + "：" + var10.getMessage());
                ajax.setSuccess(false);
            }
        }

        return JSONObject.toJSONString(ajax);
    }
}
