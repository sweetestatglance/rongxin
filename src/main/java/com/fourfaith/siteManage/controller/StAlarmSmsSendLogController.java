//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.controller;

import com.fourfaith.siteManage.model.StAlarmSmsSendLog;
import com.fourfaith.siteManage.service.StAlarmSmsSendLogService;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/stAlarmSmsSendLog"})
public class StAlarmSmsSendLogController {
    protected static final String indexJsp = "/page/alarm/sendSMS/index";
    @Autowired
    private StAlarmSmsSendLogService stAlarmSmsSendLogService;

    public StAlarmSmsSendLogController() {
    }

    @RequestMapping({"/index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/alarm/sendSMS/index");
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
        } catch (UnsupportedEncodingException var10) {
            var10.printStackTrace();
        }

        Map<String, Object> params = new HashMap();
        params.put("username", name_query);
        params.put("phone", phone_query);
        String pageNo = request.getParameter("pageNo");
        int count = this.stAlarmSmsSendLogService.getCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("pageStart", pagingBean.getStart());
        params.put("pageEnd", pagingBean.getLimit());
        List<StAlarmSmsSendLog> smsList = this.stAlarmSmsSendLogService.getList(params);
        mav.addObject("smsLogList", smsList);
        mav.addObject("pagingBean", pagingBean);
        return mav;
    }
}
