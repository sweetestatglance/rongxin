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
import java.util.ArrayList;
import java.util.Arrays;
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
@RequestMapping({"/stAlarmConfigure"})
public class StAlarmConfigureController {
    protected static final String indexJsp = "/page/alarm/configure/index";
    protected static final String listJsp = "/page/alarm/configure/list";
    protected static final String configJsp = "/page/alarm/configure/config";
    protected static final String personListJsp = "/page/alarm/configure/personList";
    @Autowired
    private StAlarmPersonService stAlarmPersonService;
    @Autowired
    private StAlarmConfigureService stAlarmConfigureService;

    public StAlarmConfigureController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/alarm/configure/index");
        return mav;
    }

    @RequestMapping(
            value = {"/configList"},
            method = {RequestMethod.GET}
    )
    public ModelAndView configList(HttpServletRequest request, boolean isDevice) {
        ModelAndView mav = new ModelAndView("/page/alarm/configure/list");
        String nodeIds = request.getParameter("nodeIds");
        List<String> stcdList = null;
        if (StringUtils.isNotBlank(nodeIds)) {
            stcdList = new ArrayList(Arrays.asList(nodeIds.split(",")));
        } else {
            stcdList = new ArrayList(Arrays.asList(""));
        }

        Map<String, Object> params = new HashMap();
        if (isDevice) {
            params.put("stcdList", stcdList);
        } else if (stcdList.size() == 1) {
            params.put("addvcd", stcdList.get(0));
        } else {
            params.put("addvcdList", stcdList);
        }

        String stcd_query = request.getParameter("stcd_query");

        try {
            if (StringUtils.isNotBlank(stcd_query)) {
                stcd_query = URLDecoder.decode(stcd_query, "UTF-8");
                mav.addObject("stcd", stcd_query);
            } else {
                stcd_query = null;
                mav.addObject("stcd", (Object)null);
            }
        } catch (UnsupportedEncodingException var12) {
            var12.printStackTrace();
        }

        params.put("stcd_query", stcd_query);
        String pageNo = request.getParameter("pageNo");
        Integer count = this.stAlarmConfigureService.getCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("start", pagingBean.getStart());
        params.put("limit", pagingBean.getLimit());
        List<StAlarmConfigure> alarmConfigList = this.stAlarmConfigureService.getList(params);
        mav.addObject("alarmConfigList", alarmConfigList);
        mav.addObject("pagingBean", pagingBean);
        return mav;
    }

    @RequestMapping({"smsAlarmSetPage"})
    public ModelAndView smsAlarmSetPage(HttpServletRequest request, String stcd) {
        ModelAndView mav = new ModelAndView("/page/alarm/configure/config");
        StAlarmConfigure model = this.stAlarmConfigureService.findByStcd(stcd);
        List<String> userNameList = new ArrayList();
        List<String> userIdList = new ArrayList();
        String nameList;
        if (model != null) {
            nameList = model.getPersonId();
            String[] idArray = nameList.split(",");

            for(int i = 0; i < idArray.length; ++i) {
                StAlarmPerson alarmPerson = this.stAlarmPersonService.selectByPrimaryKey(idArray[i]);
                if (alarmPerson != null) {
                    userNameList.add(alarmPerson.getName());
                    userIdList.add(alarmPerson.getId());
                }
            }
        }

        mav.addObject("stcd", stcd);
        mav.addObject("stAlarmConfigure", model);
        nameList = "";
        String idList = "";
        if (userNameList != null && userNameList.size() > 0) {
            nameList = this.listToString(userNameList, ",");
            idList = this.listToString(userIdList, ",");
        }

        mav.addObject("userNameList", nameList);
        mav.addObject("userIdList", idList);
        return mav;
    }

    public String listToString(List<String> list, String separator) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < list.size(); ++i) {
            sb.append((String)list.get(i)).append(separator);
        }

        return sb.toString().substring(0, sb.toString().length() - 1);
    }

    @RequestMapping({"logSaveSmsAlarm"})
    @ResponseBody
    public String logSaveSmsAlarm(HttpServletRequest request, StAlarmConfigure alarmConfigure) {
        AjaxJson ajaxJson = new AjaxJson();
        String msg = "操作成功";
        boolean success = true;
        String stcd = alarmConfigure.getStcd();
        StAlarmConfigure model = this.stAlarmConfigureService.findByStcd(stcd);
        if (model == null) {
            try {
                alarmConfigure.setId(CommonUtil.getRandomUUID());
                alarmConfigure.setTm(new Date());
                alarmConfigure.setPersonId(URLDecoder.decode(alarmConfigure.getPersonId(), "UTF-8"));
                alarmConfigure.setContent(URLDecoder.decode(alarmConfigure.getContent(), "UTF-8"));
            } catch (UnsupportedEncodingException var11) {
                var11.printStackTrace();
            }

            int reuslt = this.stAlarmConfigureService.insert(alarmConfigure);
            if (reuslt <= 0) {
                msg = "操作失败";
                success = false;
            }
        } else {
            StAlarmConfigure rel = this.stAlarmConfigureService.selectByPrimaryKey(alarmConfigure.getId());

            try {
                BeanUtils.copyPropertiesExcludeNull(alarmConfigure, rel);
                rel.setPersonId(URLDecoder.decode(alarmConfigure.getPersonId(), "UTF-8"));
                rel.setContent(URLDecoder.decode(alarmConfigure.getContent(), "UTF-8"));
                rel.setTm(new Date());
            } catch (Exception var10) {
                var10.printStackTrace();
            }

            int reuslt = this.stAlarmConfigureService.updateByPrimaryKey(rel);
            if (reuslt <= 0) {
                msg = "操作失败";
                success = false;
            }
        }

        ajaxJson.setMsg(msg);
        ajaxJson.setSuccess(success);
        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/personList"})
    public ModelAndView personList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/alarm/configure/personList");
        String name_query = request.getParameter("name_query");
        String ChoosedPersonList = request.getParameter("userIdList");
        mav.addObject("ChoosedPersonList", ChoosedPersonList);

        try {
            if (StringUtils.isNotBlank(name_query)) {
                name_query = URLDecoder.decode(name_query, "UTF-8");
                mav.addObject("name", name_query);
            } else {
                name_query = null;
                mav.addObject("name", (Object)null);
            }
        } catch (UnsupportedEncodingException var11) {
            var11.printStackTrace();
        }

        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> params = new HashMap();
        params.put("enterpriseid", login_user.getEnterpriseid());
        params.put("query_name", name_query);
        String pageNo = request.getParameter("pageNo");
        int count = this.stAlarmPersonService.getCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "7", count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("pageStart", pagingBean.getStart());
        params.put("pageEnd", pagingBean.getLimit());
        List<StAlarmPerson> personList = this.stAlarmPersonService.getList(params);
        mav.addObject("personList", personList);
        mav.addObject("pagingBean", pagingBean);
        return mav;
    }
}
