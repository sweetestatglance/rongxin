//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.controller;

import com.fourfaith.sysManage.model.SysEnterprise;
import com.fourfaith.sysManage.model.SysLog;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysEnterpriseService;
import com.fourfaith.sysManage.service.SysLogService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.StringUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/sysLog"})
public class SysLogController {
    protected static final String listJsp = "/page/sysmanage/log/logIndex";
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysEnterpriseService sysEnterpriseService;

    public SysLogController() {
    }

    @RequestMapping({"index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/log/logIndex");
        Map<String, Object> params = new HashMap();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        String enterpriseid = login_user.getEnterpriseid();
        String query_beginTime = request.getParameter("query_beginTime");
        String query_endTime = request.getParameter("query_endTime");
        String query_keyword = request.getParameter("query_keyword");
        String query_area_keyword = request.getParameter("query_area_keyword");

        try {
            if (!StringUtils.isNullOrEmpty(query_keyword)) {
                query_keyword = URLDecoder.decode(query_keyword, "UTF-8");
                mav.addObject("query_keyword", query_keyword);
            } else {
                mav.addObject("query_keyword", (Object)null);
            }

            if (!StringUtils.isNullOrEmpty(query_area_keyword)) {
                query_area_keyword = URLDecoder.decode(query_area_keyword, "UTF-8");
                mav.addObject("query_area_keyword", query_area_keyword);
            } else {
                mav.addObject("query_area_keyword", (Object)null);
            }
        } catch (UnsupportedEncodingException var17) {
            var17.printStackTrace();
        }

        SysEnterprise enterprise = this.sysEnterpriseService.findById(enterpriseid);
        Date beginTime = null;
        Date endTime = null;
        if (!StringUtils.isNullOrEmpty(query_beginTime)) {
            beginTime = DateUtils.StringToDate(query_beginTime, "yyyy-MM-dd HH:mm:ss");
        }

        if (!StringUtils.isNullOrEmpty(query_endTime)) {
            endTime = DateUtils.StringToDate(query_endTime, "yyyy-MM-dd HH:mm:ss");
        }

        if (!StringUtils.isNullOrEmpty(query_keyword)) {
            params.put("logcontent_fuzzy", query_keyword);
        }

        if (!StringUtils.isNullOrEmpty(query_area_keyword)) {
            params.put("loginarea_fuzzy", query_area_keyword);
        }

        params.put("enterpriseid", enterpriseid);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        String pageNo = request.getParameter("pageNo");
        int count = this.sysLogService.getCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("pageStart", pagingBean.getStart());
        params.put("pageEnd", pagingBean.getLimit());
        List<SysLog> logList = this.sysLogService.getList(params);
        query_beginTime = query_beginTime == null ? "" : query_beginTime;
        query_endTime = query_endTime == null ? "" : query_endTime;
        query_keyword = query_keyword == null ? "" : query_keyword;
        mav.addObject("query_beginTime", query_beginTime);
        mav.addObject("query_endTime", query_endTime);
        mav.addObject("pagingBean", pagingBean);
        mav.addObject("logList", logList);
        mav.addObject("enterpriseid", enterpriseid);
        mav.addObject("enterprise", enterprise);
        return mav;
    }
}
