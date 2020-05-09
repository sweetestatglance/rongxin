//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.reportManage.model.StAllDetailsFactor;
import com.fourfaith.reportManage.service.StAllDetailsFactorService;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/stAllDetailsFactor"})
public class StAllDetailsFactorController {
    @Autowired
    private StAllDetailsFactorService stAllDetailsFactorService;

    public StAllDetailsFactorController() {
    }

    @RequestMapping(
            value = {"/getList"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public String getList(HttpServletRequest request, String stcd, String queryTime, String searchType, Integer month) {
        new ArrayList();
        Map<String, Object> result = new HashMap();
        Date beginTime = null;
        Date endTime = null;
        if (StringUtils.isBlank(searchType)) {
            searchType = "2";
            queryTime = DateUtils.DateToString(new Date(), "yyyy-MM-dd");
        }

        Date date = null;
        if ("2".equals(searchType)) {
            date = DateUtils.StringToDate(queryTime, "yyyy-MM-dd");
            beginTime = DateUtils.setMinTimeForDate(date);
            endTime = DateUtils.setMaxTimeForDate(date);
        } else if ("3".equals(searchType)) {
            date = DateUtils.StringToDate(queryTime, "yyyy-MM");
            beginTime = DateUtils.setMinTimeForMonthDate(date);
            endTime = DateUtils.setMaxTimeForMonthDate(date);
        } else {
            date = DateUtils.StringToDate(queryTime, "yyyy");
            date = DateUtils.set(date, 2, month - 1);
            beginTime = DateUtils.setMinTimeForMonthDate(date);
            endTime = DateUtils.setMaxTimeForMonthDate(date);
        }

        Map<String, Object> params = new HashMap();
        params.put("tableName", getTableName(date));
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        params.put("stcd", stcd);

        try {
            String pageNo = request.getParameter("pageNo");
            int count = this.stAllDetailsFactorService.getCount(params);
            PagingBean pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
            params.put("start", pagingBean.getStart());
            params.put("limit", pagingBean.getLimit());
            List<StAllDetailsFactor> list = this.stAllDetailsFactorService.getList(params);
            result.put("list", list);
            result.put("pagingBean", pagingBean);
            result.put("success", true);
        } catch (Exception var15) {
            var15.printStackTrace();
            result.put("success", false);
        }

        result.put("month", month);
        return JSONObject.toJSONString(result);
    }

    public static String getTableName(Date date) {
        String tableName = "ST_AllDetails_Factor";
        if (date != null) {
            tableName = tableName + "_" + DateUtils.DateToString(date, "yyyyMM");
        }

        return tableName;
    }

    public static void main(String[] args) {
    }
}
