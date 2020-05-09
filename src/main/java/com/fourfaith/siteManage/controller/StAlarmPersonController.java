//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.siteManage.model.StAlarmConfigure;
import com.fourfaith.siteManage.model.StAlarmPerson;
import com.fourfaith.siteManage.service.StAlarmConfigureService;
import com.fourfaith.siteManage.service.StAlarmPersonService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.BeanUtils;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/stAlarmPerson"})
public class StAlarmPersonController {
    protected static final String indexJsp = "/page/alarm/person/index";
    protected static final String addJsp = "/page/alarm/person/add";
    protected static final String editJsp = "/page/alarm/person/edit";
    @Autowired
    private StAlarmPersonService stAlarmPersonService;
    @Autowired
    protected StAlarmConfigureService stAlarmConfigureService;
    public String logContent = "";

    public StAlarmPersonController() {
    }

    @RequestMapping({"/index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/alarm/person/index");
        String name_query = request.getParameter("name_query");
        String phone_query = request.getParameter("phone_query");

        try {
            if (StringUtils.isNotBlank(name_query)) {
                name_query = URLDecoder.decode(name_query, "UTF-8");
                mav.addObject("name", name_query);
            } else {
                name_query = null;
                mav.addObject("name", (Object)null);
            }

            if (StringUtils.isNotBlank(phone_query)) {
                phone_query = URLDecoder.decode(phone_query, "UTF-8");
                mav.addObject("phone", phone_query);
            } else {
                phone_query = null;
                mav.addObject("phone", (Object)null);
            }
        } catch (UnsupportedEncodingException var11) {
            var11.printStackTrace();
        }

        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> params = new HashMap();
        params.put("enterpriseid", login_user.getEnterpriseid());
        params.put("query_name", name_query);
        params.put("query_phone", phone_query);
        String pageNo = request.getParameter("pageNo");
        int count = this.stAlarmPersonService.getCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("pageStart", pagingBean.getStart());
        params.put("pageEnd", pagingBean.getLimit());
        List<StAlarmPerson> personList = this.stAlarmPersonService.getList(params);
        mav.addObject("personList", personList);
        mav.addObject("pagingBean", pagingBean);
        return mav;
    }

    @RequestMapping({"/addPage"})
    public ModelAndView addPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/alarm/person/add");
        return mav;
    }

    @RequestMapping({"/editPage"})
    public ModelAndView editPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/alarm/person/edit");
        String id = request.getParameter("id");
        StAlarmPerson alarmPerson = this.stAlarmPersonService.selectByPrimaryKey(id);
        mav.addObject("alarmPerson", alarmPerson);
        return mav;
    }

    @RequestMapping(
            value = {"/logAddAlarmPerson"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String logAddAlarmPerson(HttpServletRequest request, StAlarmPerson alarmPerson) throws Exception {
        this.logContent = "";
        AjaxJson ajaxJson = new AjaxJson();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        alarmPerson.setId(CommonUtil.getRandomUUID());
        alarmPerson.setEnterpriseid(login_user.getEnterpriseid());
        alarmPerson.setTm(new Date());
        int result = this.stAlarmPersonService.insert(alarmPerson);
        if (result > 0) {
            ajaxJson.setMsg("操作成功");
            this.logContent = "添加" + alarmPerson.getName() + "预警人员成功";
        } else {
            ajaxJson.setMsg("操作失败");
            this.logContent = "添加" + alarmPerson.getName() + "预警人员失败";
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping(
            value = {"/logEditAlarmPerson"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String logEditAlarmPerson(HttpServletRequest request, StAlarmPerson alarmPerson) throws Exception {
        this.logContent = "";
        AjaxJson ajaxJson = new AjaxJson();
        StAlarmPerson rel = this.stAlarmPersonService.selectByPrimaryKey(alarmPerson.getId());
        rel.setTm(new Date());
        BeanUtils.copyPropertiesExcludeNull(alarmPerson, rel);
        Integer result = this.stAlarmPersonService.updateByPrimaryKey(rel);
        String msg = null;
        if (result > 0) {
            ajaxJson.setMsg("操作成功");
            this.logContent = "编辑" + alarmPerson.getName() + "预警人员成功";
        } else {
            ajaxJson.setMsg("操作失败");
            this.logContent = "编辑" + alarmPerson.getName() + "预警人员失败";
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"logDelAlarmPerson"})
    @ResponseBody
    public String logDelAlarmPerson(HttpServletRequest request) {
        this.logContent = "";
        String ids = request.getParameter("ids");
        AjaxJson ajaxJson = new AjaxJson();
        boolean isDelete = true;
        String[] idArray;
        int i;
        if (ids != null) {
            idArray = ids.split(",");

            for(i = 0; i < idArray.length; ++i) {
                List<StAlarmConfigure> configList = this.stAlarmConfigureService.getByPerson(idArray[i]);
                if (configList != null && configList.size() > 0) {
                    isDelete = false;
                }
            }
        }

        if (isDelete) {
            if (ids != null) {
                idArray = ids.split(",");

                for(i = 0; i < idArray.length; ++i) {
                    this.stAlarmPersonService.deleteByPrimaryKey(idArray[i]);
                }
            }

            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("操作成功");
            this.logContent = "删除预警人员成功";
        } else {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("预警配置存在相关记录，无法删除");
            this.logContent = "预警配置存在相关记录，无法删除";
        }

        return JSONObject.toJSONString(ajaxJson);
    }
}
