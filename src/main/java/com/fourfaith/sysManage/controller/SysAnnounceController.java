//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.sysManage.model.SysAnnounce;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserAnnounce;
import com.fourfaith.sysManage.service.SysAnnounceService;
import com.fourfaith.sysManage.service.SysUserAnnounceService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
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
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/sysAnnounce"})
public class SysAnnounceController {
    protected static final String indexJsp = "/page/sysmanage/announce/announceIndex";
    protected static final String addJsp = "/page/sysmanage/announce/add";
    protected static final String viewJsp = "/page/sysmanage/announce/view";
    @Autowired
    private SysAnnounceService sysAnnounceService;
    @Autowired
    private SysUserAnnounceService sysUserAnnounceService;
    public String logContent = "";

    public SysAnnounceController() {
    }

    @RequestMapping({"/index"})
    public ModelAndView index(HttpServletRequest request, String enterName, Integer isRead, boolean isopt) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/announce/announceIndex");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> params = new HashMap();
        if (isRead != null) {
            params.put("isread", isRead);
        }

        params.put("userid", login_user.getId());
        String pageNo = request.getParameter("pageNo");
        int count = this.sysAnnounceService.getCountByRead(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("pageStart", pagingBean.getStart());
        params.put("pageEnd", pagingBean.getLimit());
        List<SysAnnounce> list = this.sysAnnounceService.getListByRead(params);
        mav.addObject("announceList", list);
        mav.addObject("isopt", isopt);
        mav.addObject("pagingBean", pagingBean);
        mav.addObject("isRead", isRead);
        return mav;
    }

    @RequestMapping({"/addPage"})
    public ModelAndView addPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/announce/add");
        return mav;
    }

    @RequestMapping({"/view"})
    public ModelAndView view(HttpServletRequest request, String Id) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/announce/view");
        SysAnnounce model = this.sysAnnounceService.findById(Id);
        mav.addObject("model", model);
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> params = new HashMap();
        params.put("userId", login_user.getId());
        params.put("noticeId", model.getId());
        SysUserAnnounce sysUserAnnounce = this.sysUserAnnounceService.findByUserIdAndAnnId(params);
        if (sysUserAnnounce != null && StringUtils.isNotBlank(sysUserAnnounce.getId())) {
            sysUserAnnounce.setIsread(1);
            this.sysUserAnnounceService.updateByPrimaryKey(sysUserAnnounce);
        }

        return mav;
    }

    @RequestMapping(
            value = {"/logAddAnnounce"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String logAddAnnounce(HttpServletRequest request, SysAnnounce announce) throws Exception {
        this.logContent = "";
        RequestContext requestContext = new RequestContext(request);
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        announce.setId(CommonUtil.getRandomUUID());
        announce.setModitime(new Date());
        announce.setAdmin(login_user.getUsername());
        AjaxJson ajaxJson = new AjaxJson();

        try {
            ajaxJson = this.sysAnnounceService.saveAnnounce(announce, request);
        } catch (Exception var7) {
            var7.printStackTrace();
            ajaxJson.setSuccess(false);
            ajaxJson.setObj(requestContext.getMessage("optFailExcept") + "：" + var7.getMessage());
        }

        this.logContent = ajaxJson.getObj().toString();
        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/logDelAnnounce"})
    @ResponseBody
    public String logDelAnnounce(HttpServletRequest request) throws Exception {
        String items = request.getParameter("items");
        this.logContent = "";
        AjaxJson ajaxJson = this.sysAnnounceService.deleteAnnounce(items, request);
        this.logContent = ajaxJson.getObj().toString();
        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"/getUnReadNoticeCount"})
    @ResponseBody
    public String getUnReadNoticeCount(HttpServletRequest request) throws Exception {
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        AjaxJson ajaxJson = new AjaxJson();
        int count = 0;
        Map<String, Object> noticeParam = new HashMap();
        noticeParam.put("userid", login_user.getId());
        noticeParam.put("isread", 0);
        List<SysAnnounce> noticeList = this.sysAnnounceService.getListByRead(noticeParam);
        if (noticeList != null && noticeList.size() > 0) {
            count = noticeList.size();
        }

        ajaxJson.setObj(count);
        return JSONObject.toJSONString(ajaxJson);
    }
}
