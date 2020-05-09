//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fourfaith.reportManage.model.MonthAllDetails;
import com.fourfaith.reportManage.model.StAllDetailsFactor;
import com.fourfaith.reportManage.model.StDeviceFactor;
import com.fourfaith.reportManage.service.MonthAllDetailsService;
import com.fourfaith.reportManage.service.StAllDetailsFactorService;
import com.fourfaith.reportManage.service.StDeviceFactorService;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.model.StStsmtaskB;
import com.fourfaith.siteManage.service.StRsvrfcchBService;
import com.fourfaith.siteManage.service.StRvfcchBService;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.siteManage.service.StStsmtaskBService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.EChart;
import com.fourfaith.utils.FactorName;
import com.fourfaith.utils.MapKeyComparator;
import com.fourfaith.utils.Month;
import com.fourfaith.utils.PropertiesUtils;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.WritableFont.FontName;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/aloneReport"})
public class AloneFactorReportController {
    protected static final String commonJsp = "/page/reportdetail/commonReport";
    protected static final String rainReportJsp = "/page/reportdetail/rainReport";
    @Autowired
    private StStsmtaskBService stStsmtaskBService;
    @Autowired
    private StStbprpBService stStbprpBService;
    @Autowired
    private StDeviceFactorService stDeviceFactorService;
    @Autowired
    private StAllDetailsFactorService stAllDetailsFactorService;
    @Autowired
    private StRvfcchBService stRvfcchBService;
    @Autowired
    private StRsvrfcchBService stRsvrfcchBService;
    @Autowired
    private MonthAllDetailsService monthAllDetailsService;
    @Autowired
    private StFactorNameService stFactorNameService;

    public AloneFactorReportController() {
    }

    @RequestMapping({"reportPage"})
    public ModelAndView reportPage(HttpServletRequest request, String flag, String stcd, Integer type, String childFlag) {
        ModelAndView mav = new ModelAndView();
        String searchType = request.getParameter("searchType");
        String queryTime = request.getParameter("queryTime");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Date beginTime = null;
        Date endTime = null;
        String preDayTime = null;
        String preMonthTime = null;
        String preYearTime = null;
        Date date = null;
        Map<String, Object> monthParams = new HashMap();
        monthParams.put("stcd", stcd);
        String tableName = "ST_AllDetails_Factor";
        if (StringUtils.isBlank(searchType)) {
            searchType = "2";
            queryTime = DateUtils.DateToString(new Date(), "yyyy-MM-dd");
        }

        String pattern = "yyyy-MM-dd";
        if ("2".equals(searchType)) {
            date = DateUtils.StringToDate(queryTime, pattern);
            beginTime = DateUtils.setMinTimeForDate(date);
            endTime = DateUtils.setMaxTimeForDate(date);
            preDayTime = queryTime;
            preMonthTime = DateUtils.DateToString(DateUtils.add(new Date(), 2, 0), "yyyy-MM");
            preYearTime = DateUtils.DateToString(DateUtils.add(new Date(), 1, -1), "yyyy");
        } else if ("3".equals(searchType)) {
            pattern = "yyyy-MM";
            date = DateUtils.StringToDate(queryTime, pattern);
            beginTime = DateUtils.setMinTimeForMonthDate(date);
            endTime = DateUtils.setMaxTimeForMonthDate(date);
            preDayTime = DateUtils.DateToString(new Date(), "yyyy-MM-dd");
            preMonthTime = queryTime;
            preYearTime = DateUtils.DateToString(DateUtils.add(new Date(), 1, -1), "yyyy");
        } else {
            pattern = "yyyy";
            date = DateUtils.StringToDate(queryTime, pattern);
            preDayTime = DateUtils.DateToString(new Date(), "yyyy-MM-dd");
            preMonthTime = DateUtils.DateToString(DateUtils.add(new Date(), 2, -1), "yyyy-MM");
            preYearTime = queryTime;
        }

        if (StringUtils.isBlank(flag)) {
            flag = "z";
        }

        Map<String, Object> params = new HashMap();
        params.put("stcd", stcd);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        StStsmtaskB smtask = this.stStsmtaskBService.findById(stcd);
        StStbprpB stBprpB = this.stStbprpBService.findById(stcd);
        Calendar cal = Calendar.getInstance();
        Date dateTime = DateUtils.StringToDate(queryTime, pattern);
        cal.setTime(dateTime);
        int year = cal.get(1);
        int month = cal.get(2) + 1;
        tableName = StAllDetailsFactorController.getTableName(date);
        String factorName = "";
        if ("pn05".equals(flag)) {
            mav.setViewName("/page/reportdetail/rainReport");
            factorName = "rain";
            mav.addObject("viewName1", "雨量");
            mav.addObject("viewName3", "日累计降雨量");
            mav.addObject("viewName2", "雨雪标识");
            mav.addObject("factor1", "pn05");
            mav.addObject("factor3", "pJ");
            mav.addObject("factor2", "td11");
        } else {
            mav.setViewName("/page/reportdetail/commonReport");
        }

        try {
            this.commonFactor(mav, params, monthParams, searchType, tableName, year, month, flag);
        } catch (Exception var32) {
            var32.printStackTrace();
        }

        Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), new ArrayList(), request);
        mav.addObject("factorNameMap", factorNameMap);
        mav.addObject("stBprpB", stBprpB);
        mav.addObject("flag", flag);
        mav.addObject("stcd", stcd);
        mav.addObject("smtask", smtask);
        mav.addObject("preDayTime", preDayTime);
        mav.addObject("searchType", searchType);
        mav.addObject("preMonthTime", preMonthTime);
        mav.addObject("preYearTime", preYearTime);
        Calendar cals = Calendar.getInstance();
        int viewYear = cals.get(1);
        int viewMonth = cals.get(2) + 1;
        Map<Integer, Object> map = this.viewMonth(request, year);
        mav.addObject("monthMap", map);
        if (year < viewYear) {
            viewMonth = 12;
        }

        mav.addObject("viewMonth", viewMonth);
        return mav;
    }

    public Map<Integer, Object> viewMonth(HttpServletRequest request, int year) {
        Map<Integer, Object> map = new HashMap();
        Calendar cal = Calendar.getInstance();
        int viewYear = cal.get(1);
        int viewMonth = cal.get(2) + 1;
        if (year < viewYear) {
            viewMonth = 12;
        }

        for(int i = 1; i <= viewMonth; ++i) {
            Month[] var11;
            int var10 = (var11 = Month.values()).length;

            for(int var9 = 0; var9 < var10; ++var9) {
                Month r = var11[var9];
                if (i == r.getMonths()) {
                    map.put(i, r.getChineseName());
                }
            }
        }

        return map;
    }

    private void commonFactor(ModelAndView mav, Map<String, Object> params, Map<String, Object> monthParams, String searchType, String tableName, int year, int month, String flag) {
        params.put("tableName", tableName);
        params.put("factorCon", flag);
        params.put("orderByCon", "TM DESC");
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(1);
        int currMonth = calendar.get(2) + 1;
        StAllDetailsFactor lastest = null;
        if (!"3".equals(searchType) && !"4".equals(searchType)) {
            lastest = this.stAllDetailsFactorService.getLastest(params);
        }

        StAllDetailsFactor detailFactor = new StAllDetailsFactor();
        Field minField;
        if ("2".equals(searchType)) {
            params.put("tableName", tableName);
            detailFactor = this.stAllDetailsFactorService.getDetail(params);
        } else {
            MonthAllDetails monthAllDetail;
            Field minDateField;
            Field maxDateField;
            Field newDateField;
            Field maxField;
            Field newField;
            if ("3".equals(searchType)) {
                monthParams.put("year", year);
                monthParams.put("month", month);
                if (year == currYear && month == currMonth) {
                    params.put("tableName", tableName);
                    detailFactor = this.stAllDetailsFactorService.getDetail(params);
                } else {
                    monthParams.put("factor", flag);
                    monthAllDetail = this.monthAllDetailsService.getDetail(monthParams);

                    try {
                        minField = StAllDetailsFactor.class.getDeclaredField("minFactorValue");
                        minField.setAccessible(true);
                        minField.set(detailFactor, monthAllDetail.getMinFactorValue());
                        minDateField = StAllDetailsFactor.class.getDeclaredField("minDate");
                        minDateField.setAccessible(true);
                        minDateField.set(detailFactor, monthAllDetail.getMinDate());
                        maxDateField = StAllDetailsFactor.class.getDeclaredField("maxDate");
                        maxDateField.setAccessible(true);
                        maxDateField.set(detailFactor, monthAllDetail.getMaxDate());
                        newDateField = StAllDetailsFactor.class.getDeclaredField("newDate");
                        newDateField.setAccessible(true);
                        newDateField.set(detailFactor, monthAllDetail.getNewDate());
                        maxField = StAllDetailsFactor.class.getDeclaredField("maxFactorValue");
                        maxField.setAccessible(true);
                        maxField.set(detailFactor, monthAllDetail.getMaxFactorValue());
                        newField = StAllDetailsFactor.class.getDeclaredField("newFactorValue");
                        newField.setAccessible(true);
                        newField.set(detailFactor, monthAllDetail.getNewFactorValue());
                    } catch (Exception var23) {
                        var23.printStackTrace();
                    }
                }
            } else if ("4".equals(searchType)) {
                monthParams.put("year", year);
                monthParams.put("factor", flag);
                monthAllDetail = this.monthAllDetailsService.getYearDetail(monthParams);

                try {
                    minField = StAllDetailsFactor.class.getDeclaredField("minFactorValue");
                    minField.setAccessible(true);
                    minField.set(detailFactor, monthAllDetail.getMinFactorValue());
                    minDateField = StAllDetailsFactor.class.getDeclaredField("maxFactorValue");
                    minDateField.setAccessible(true);
                    minDateField.set(detailFactor, monthAllDetail.getMaxFactorValue());
                    maxDateField = StAllDetailsFactor.class.getDeclaredField("newFactorValue");
                    maxDateField.setAccessible(true);
                    maxDateField.set(detailFactor, monthAllDetail.getNewFactorValue());
                    newDateField = StAllDetailsFactor.class.getDeclaredField("minDate");
                    newDateField.setAccessible(true);
                    newDateField.set(detailFactor, monthAllDetail.getMinDate());
                    maxField = StAllDetailsFactor.class.getDeclaredField("maxDate");
                    maxField.setAccessible(true);
                    maxField.set(detailFactor, monthAllDetail.getMaxDate());
                    newField = StAllDetailsFactor.class.getDeclaredField("newDate");
                    newField.setAccessible(true);
                    newField.set(detailFactor, monthAllDetail.getNewDate());
                } catch (Exception var22) {
                    var22.printStackTrace();
                }
            }
        }

        if (lastest != null) {
            try {
                Field getNewField = StAllDetailsFactor.class.getDeclaredField(flag);
                getNewField.setAccessible(true);
                minField = StAllDetailsFactor.class.getDeclaredField("newFactorValue");
                minField.setAccessible(true);
                minField.set(detailFactor, getNewField.get(lastest));
            } catch (Exception var21) {
                var21.printStackTrace();
            }
        }

        mav.addObject("detailFactor", detailFactor);
    }

    @RequestMapping(
            value = {"/getChartsData"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public String getChartsData(HttpServletRequest request, String flag, String stcd, Integer type, String childFlag) {
        EChart echart = new EChart();
        RequestContext requestContext = new RequestContext(request);
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        this.stStsmtaskBService.findById(stcd);
        Map<String, Object> params = new HashMap();
        params.put("stcd", stcd);
        String pattern = "";
        String dateUnit = "";
        if (StringUtils.isBlank(flag)) {
            flag = "water";
        }

        Map<String, Object> result = new HashMap();
        String searchType = request.getParameter("searchType");
        String queryTime = request.getParameter("queryTime");
        Date beginTime = null;
        Date endTime = null;
        if (StringUtils.isBlank(searchType)) {
            searchType = "2";
            queryTime = DateUtils.DateToString(new Date(), "yyyy-MM-dd");
        }

        Date tDate;
        if ("2".equals(searchType)) {
            tDate = DateUtils.StringToDate(queryTime, "yyyy-MM-dd");
            beginTime = DateUtils.setMinTimeForDate(tDate);
            endTime = DateUtils.setMaxTimeForDate(tDate);
        } else if ("3".equals(searchType)) {
            tDate = DateUtils.StringToDate(queryTime, "yyyy-MM");
            beginTime = DateUtils.setMinTimeForMonthDate(tDate);
            endTime = DateUtils.setMaxTimeForMonthDate(tDate);
        } else {
            tDate = DateUtils.StringToDate(queryTime, "yyyy");
            beginTime = DateUtils.setMinTimeForYearDate(tDate);
            endTime = DateUtils.setMaxTimeForYearDate(tDate);
        }

        if (DateUtils.in24Hours(beginTime, endTime)) {
            params.put("groupByCon", "DATE_FORMAT(TM, '%Y-%m-%d %H')");
            pattern = "H";
            dateUnit = requestContext.getMessage("hour");
        } else if (DateUtils.in1Month(beginTime, endTime)) {
            params.put("groupByCon", "DAYOFMONTH (TM)");
            pattern = "d";
            dateUnit = requestContext.getMessage("day");
        } else if (DateUtils.in1Year(beginTime, endTime)) {
            params.put("groupByCon", "MONTH (TM)");
            pattern = "M";
            dateUnit = requestContext.getMessage("months");
        } else {
            params.put("groupByCon", "YEAR (TM)");
            pattern = "y";
            dateUnit = requestContext.getMessage("year");
        }

        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        params.put("orderByCon", "TM ASC");
        Map<String, Object> fMap = new HashMap();
        new ArrayList();
        List<String> LegendDataList = new ArrayList();
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), new ArrayList(), request);
        fMap.put("name", ((FactorName)factorNameMap.get(flag)).getName());
        fMap.put("type", "pn05".equals(flag) ? "bar" : "line");
        fMap.put("showAllSymbol", "true");
        fMap.put("smooth", "true");
        List<Object> fData = new ArrayList();
        LegendDataList.add(((FactorName)factorNameMap.get(flag)).getName());
        Map<String, Object> pjMap = new HashMap();
        List<Object> pjData = new ArrayList();
        if ("pn05".equals(flag)) {
            pjMap.put("name", "日累计降雨量");
            pjMap.put("type", "bar");
            pjMap.put("showAllSymbol", "true");
            pjMap.put("smooth", "true");
            pjData = new ArrayList();
            LegendDataList.add("日累计降雨量");
        }

        List<String> categories = this.getCategories(beginTime, endTime, type, request);
        int i;
        if ("4".equals(searchType)) {
            Map<String, Object> paramMap = new HashMap();
            paramMap.put("stcd", stcd);
            paramMap.put("year", queryTime);
            List<MonthAllDetails> monthList = this.monthAllDetailsService.getList(paramMap);

            for(i = 1; i < 13; ++i) {
                if (monthList != null) {
                    if (fData != null) {
                        fData.add(this.filterFactorValue(monthList, flag, i));
                    }

                    if ("pn05".equals(flag) && pjData != null) {
                        pjData.add(this.filterFactorValue(monthList, "pJ", i));
                    }
                }
            }
        } else {
            try {
                params.put("tableName", StAllDetailsFactorController.getTableName(beginTime));
                List<StDeviceFactor> detailList = this.stAllDetailsFactorService.getReportStaticList(params);
                SimpleDateFormat df = new SimpleDateFormat(pattern);

                for(i = 0; i < categories.size(); ++i) {
                    String categor = (String)categories.get(i);
                    StDeviceFactor model = new StDeviceFactor();
                    if (detailList != null && detailList.size() > 0) {
                        Iterator var31 = detailList.iterator();

                        while(var31.hasNext()) {
                            StDeviceFactor stDeviceFactor = (StDeviceFactor)var31.next();
                            if (stDeviceFactor.getTm() != null && (df.format(stDeviceFactor.getTm()) + dateUnit).equals(categor)) {
                                model = stDeviceFactor;
                                detailList.remove(stDeviceFactor);
                                break;
                            }
                        }
                    }

                    if (fData != null) {
                        Field getField = StDeviceFactor.class.getDeclaredField(flag);
                        getField.setAccessible(true);
                        fData.add(getField.get(model));
                        if ("pn05".equals(flag)) {
                            Field getField1 = StDeviceFactor.class.getDeclaredField("pJ");
                            getField1.setAccessible(true);
                            pjData.add(getField1.get(model));
                        }
                    }
                }
            } catch (Exception var32) {
                var32.printStackTrace();
            }
        }

        result.put("queryTime", queryTime);
        result.put("searchType", searchType);
        List<Map<String, Object>> series = new ArrayList();
        List<Map<String, Object>> yAxis = new ArrayList();
        i = 0;
        String[] colorAry = new String[]{"#2ec7c9", "#b6a2de", "#5ab1ef", "#ffb980", "#d87a80", "#8d98b3", "#e5cf0d", "#97b552", "#95706d", "#dc69aa"};
        if (fMap != null) {
            fMap.put("data", fData);
            series.add(fMap);
            if ("pn05".equals(flag)) {
                pjMap.put("data", pjData);
                series.add(pjMap);
            }

            Map<String, Object> yAxi = new HashMap();
            if (((FactorName)factorNameMap.get(flag)).getName().length() > 8) {
                yAxi.put("name", ((FactorName)factorNameMap.get(flag)).getName().substring(0, 7) + "..");
            } else {
                yAxi.put("name", ((FactorName)factorNameMap.get(flag)).getName());
            }

            yAxi.put("width", "400");
            yAxi.put("nameGap", "10");
            yAxi.put("position", i % 2 == 0 ? "left" : "right");
            yAxi.put("offset", i / 2 * 70);
            Map<String, Object> axisLine = new HashMap();
            Map<String, Object> lineStyle = new HashMap();
            lineStyle.put("color", colorAry[i]);
            axisLine.put("lineStyle", lineStyle);
            yAxi.put("axisLine", axisLine);
            yAxis.add(yAxi);
            ++i;
        }

        --i;
        if (i > 1) {
            int left = 20 + i / 2 * 45;
            int right = 45 + (i / 2 - 1) * 45;
            echart.setGridLeft(left);
            echart.setGridRight(right);
        }

        echart.setyAxis(yAxis);
        echart.setLegendDataList(LegendDataList);
        echart.setxAxiDataList(categories);
        echart.setSeries(series);
        result.put("echart", echart);
        result.put("factorMap", factorNameMap);
        return JSONObject.toJSONString(result, new SerializerFeature[]{SerializerFeature.WriteEnumUsingToString});
    }

    private BigDecimal filterFactorValue(List<MonthAllDetails> monthList, String factorname, Integer month) {
        BigDecimal value = null;
        if (monthList != null && monthList.size() > 0) {
            Iterator var6 = monthList.iterator();

            while(var6.hasNext()) {
                MonthAllDetails model = (MonthAllDetails)var6.next();
                if (model != null && model.getFactorname().equals(factorname) && model.getMonth() == month) {
                    value = factorname.equals("rain") ? model.getSumFactorValue() : model.getAvgFactorValue();
                    break;
                }
            }
        }

        return value;
    }

    private List<String> getCategories(Date beginTime, Date endTime, Integer type, HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        List<String> categories = new ArrayList();
        Date tmp;
        if (DateUtils.in24Hours(beginTime, endTime)) {
            for(tmp = beginTime; !tmp.after(endTime); tmp = DateUtils.add(tmp, 11, 1)) {
                categories.add(DateUtils.DateToCalendar(tmp).get(11) + requestContext.getMessage("hour"));
            }
        } else if (DateUtils.in1Month(beginTime, endTime)) {
            for(tmp = beginTime; !tmp.after(endTime); tmp = DateUtils.add(tmp, 5, 1)) {
                categories.add(DateUtils.DateToCalendar(tmp).get(5) + requestContext.getMessage("day"));
            }
        } else if (DateUtils.in1Year(beginTime, endTime)) {
            for(tmp = beginTime; !tmp.after(endTime); tmp = DateUtils.add(tmp, 2, 1)) {
                categories.add(DateUtils.DateToCalendar(tmp).get(2) + 1 + requestContext.getMessage("months"));
            }
        } else {
            for(tmp = beginTime; !tmp.after(endTime); tmp = DateUtils.add(tmp, 1, 1)) {
                categories.add(DateUtils.DateToCalendar(tmp).get(1) + requestContext.getMessage("year"));
            }
        }

        return categories;
    }

    @RequestMapping(
            value = {"/getTime"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public String getTime(HttpServletRequest request, Integer type) {
        AjaxJson ajax = new AjaxJson();
        String beginTime = null;
        String endTime = null;
        Date date = new Date();
        if (type == 1) {
            beginTime = DateUtils.DateToString(date, "yyyy-MM-dd 00:00");
            endTime = DateUtils.DateToString(date, "yyyy-MM-dd HH:mm");
        } else {
            Date thday;
            if (type == 2) {
                thday = DateUtils.add(date, 5, -1);
                beginTime = DateUtils.DateToString(thday, "yyyy-MM-dd 00:00");
                endTime = DateUtils.DateToString(thday, "yyyy-MM-dd 23:59");
            } else if (type == 3) {
                thday = DateUtils.add(date, 5, -6);
                beginTime = DateUtils.DateToString(thday, "yyyy-MM-dd 00:00");
                endTime = DateUtils.DateToString(date, "yyyy-MM-dd HH:mm");
            } else if (type == 4) {
                beginTime = DateUtils.DateToString(DateUtils.setMinTimeForMonthDate(date), "yyyy-MM-dd HH:mm");
                endTime = DateUtils.DateToString(date, "yyyy-MM-dd HH:mm");
            } else if (type == 5) {
                thday = DateUtils.add(date, 5, -29);
                beginTime = DateUtils.DateToString(thday, "yyyy-MM-dd 00:00");
                endTime = DateUtils.DateToString(date, "yyyy-MM-dd HH:mm");
            } else if (type == 6) {
                beginTime = DateUtils.DateToString(DateUtils.setMinTimeForYearDate(date), "yyyy-MM-dd HH:mm");
                endTime = DateUtils.DateToString(date, "yyyy-MM-dd HH:mm");
            }
        }

        Map<String, Object> attr = new HashMap();
        attr.put("beginTime", beginTime);
        attr.put("endTime", endTime);
        ajax.setAttributes(attr);
        return ajax.getJsonStr();
    }

    @RequestMapping(
            value = {"/getFactorTime"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public String getFactorTime(HttpServletRequest request, Integer type) {
        AjaxJson ajax = new AjaxJson();
        String time = null;
        Date date = new Date();
        int searchType = 2;
        if (type == 1) {
            time = DateUtils.DateToString(date, "yyyy-MM-dd");
        } else if (type == 2) {
            Date yestoday = DateUtils.add(date, 5, -1);
            time = DateUtils.DateToString(yestoday, "yyyy-MM-dd");
        } else if (type == 4) {
            searchType = 3;
            time = DateUtils.DateToString(DateUtils.setMinTimeForMonthDate(date), "yyyy-MM");
        } else if (type == 6) {
            searchType = 4;
            time = DateUtils.DateToString(DateUtils.add(new Date(), 1, -1), "yyyy");
        }

        Map<String, Object> attr = new HashMap();
        attr.put("time", time);
        attr.put("searchType", Integer.valueOf(searchType));
        ajax.setAttributes(attr);
        return ajax.getJsonStr();
    }

    @RequestMapping(
            value = {"/exportExcelData"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String exportExcelData(HttpServletRequest request, String queryTime, String searchType, Integer month) {
        Map<String, Object> hm = new HashMap();
        new RequestContext(request);
        String stnm = request.getParameter("stnm");
        String flag = request.getParameter("flag");
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

        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        String sdate = DateUtils.DateToString(new Date(), "yyyyMMddHHmmss");
        StringBuilder str = new StringBuilder();
        String fileName = null;
        List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), filterFactor, request);
        String flagName = ((FactorName)factorNameMap.get(flag)).getName();
        str.setLength(0);
        str.append("[");
        str.append(stnm);
        str.append("]");
        str.append(flagName + "(");
        str.append(DateUtils.DateToString(beginTime, "yyyyMMddHHmm"));
        str.append("-");
        str.append(DateUtils.DateToString(endTime, "yyyyMMddHHmm"));
        str.append(")_");
        str.append(sdate);
        str.append(".xls");

        try {
            fileName = this.dataWrite(request, str.toString(), beginTime, endTime, stnm);
            hm.put("success", true);
            hm.put("fileName", fileName);
        } catch (Exception var20) {
            hm.put("success", false);
            var20.printStackTrace();
        }

        return JSONObject.toJSONString(hm);
    }

    @RequestMapping({"/download"})
    public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String fileNameStr = request.getParameter("fileName");
        String fileNameUtf = null;
        String fileName = null;

        try {
            fileNameUtf = URLDecoder.decode(fileNameStr, "UTF-8");
            fileName = URLEncoder.encode(fileNameUtf, "UTF-8");
            fileName = new String(fileNameUtf.getBytes("GB2312"), "ISO_8859_1");
        } catch (Exception var13) {
            var13.printStackTrace();
        }

        String basePath = PropertiesUtils.getPara("downFileDir");
        String savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        File file = new File(savePath + fileNameUtf);

        try {
            FileInputStream inputStream = new FileInputStream(file);
            ServletOutputStream out = response.getOutputStream();
            byte[] buffer = new byte[1024];
            boolean var12 = true;

            int length;
            while((length = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }

            inputStream.close();
            out.close();
            out.flush();
        } catch (IOException var14) {
            var14.printStackTrace();
        }

    }

    public String dataWrite(HttpServletRequest request, String fileName, Date beginTime, Date endTime, String stnm) {
        RequestContext requestContext = new RequestContext(request);
        String flag = request.getParameter("flag");
        String childFlag = request.getParameter("childFlag");
        String basePath = PropertiesUtils.getPara("downFileDir");
        String savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }

        String fileFullPath = savePath + fileName;
        WritableWorkbook wwb = null;

        try {
            wwb = Workbook.createWorkbook(new File(fileFullPath));
        } catch (IOException var48) {
            var48.printStackTrace();
        }

        if (wwb != null) {
            Map<String, Object> params = new HashMap();
            String stcd = request.getParameter("stcd");
            params.put("beginTime", beginTime);
            params.put("endTime", endTime);
            params.put("stcd", stcd);
            params.put("tableName", StAllDetailsFactorController.getTableName(beginTime));
            List<StAllDetailsFactor> allList = this.stAllDetailsFactorService.getList(params);
            FontName defaultFont = WritableFont.createFont("宋体");
            WritableFont titleFont = new WritableFont(defaultFont, 14, WritableFont.BOLD);
            WritableFont titleFont2 = new WritableFont(defaultFont, 11, WritableFont.BOLD);
            WritableFont normalFont = new WritableFont(defaultFont, 10, WritableFont.NO_BOLD);
            WritableCellFormat headerFormat = new WritableCellFormat(titleFont);
            WritableCellFormat headerFormat2 = new WritableCellFormat(titleFont2);
            WritableCellFormat contentFormat = new WritableCellFormat(normalFont);

            try {
                headerFormat.setAlignment(Alignment.CENTRE);
                headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
                headerFormat2.setAlignment(Alignment.CENTRE);
                headerFormat2.setVerticalAlignment(VerticalAlignment.CENTRE);
                contentFormat.setAlignment(Alignment.CENTRE);
                contentFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
            } catch (WriteException var47) {
                var47.printStackTrace();
            }

            try {
                List<String> factorList = new ArrayList();
                if ("waterQuality".equals(flag)) {
                    if (StringUtils.isNotBlank(childFlag)) {
                        factorList = new ArrayList(Arrays.asList(childFlag.split(",")));
                    }
                } else {
                    factorList.add(flag);
                }

                int sheetNum = 0;
                int var50 = sheetNum + 1;
                WritableSheet ws = wwb.createSheet("sheet1", sheetNum);
                ws.mergeCells(0, 0, 2 + factorList.size(), 0);
                String titleView = requestContext.getMessage("reportDetails");
                if (beginTime != null) {
                    String bTime = DateUtils.DateToString(beginTime, "yyyy/MM/dd HH:mm:ss");
                    String eTime = DateUtils.DateToString(endTime, "yyyy/MM/dd HH:mm:ss");
                    titleView = titleView + "(" + bTime + " " + requestContext.getMessage("to") + " " + eTime + ")";
                }

                Label headerTitle = new Label(0, 0, titleView, headerFormat);
                ws.addCell(headerTitle);
                Label title1 = new Label(0, 1, requestContext.getMessage("deviceCode"), headerFormat2);
                ws.addCell(title1);
                ws.setColumnView(0, 25);
                Label title2 = new Label(1, 1, requestContext.getMessage("siteName"), headerFormat2);
                ws.addCell(title2);
                ws.setColumnView(1, 25);
                Label title3 = new Label(2, 1, requestContext.getMessage("time"), headerFormat2);
                ws.addCell(title3);
                ws.setColumnView(2, 25);
                Label labelC1;
                if ("pn05".equals(flag)) {
                    ws.addCell(new Label(3, 1, "降水量", headerFormat2));
                    ws.setColumnView(3, 25);
                    ws.addCell(new Label(4, 1, "日累计降水量", headerFormat2));
                    ws.setColumnView(4, 25);
                    ws.addCell(new Label(5, 1, "雨雪标识", headerFormat2));
                    ws.setColumnView(5, 25);
                } else {
                    List<String> filterFactor = new ArrayList(Arrays.asList("td11", ""));
                    SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
                    Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), filterFactor, request);
                    if (factorNameMap.containsKey(flag)) {
                        FactorName factor = (FactorName)factorNameMap.get(flag);
                        labelC1 = new Label(3, 1, factor.getName(), headerFormat2);
                        ws.addCell(labelC1);
                        ws.setColumnView(3, 20);
                    }
                }

                int row = 2;
                if (allList != null) {
                    for(int i = 0; i < allList.size(); ++i) {
                        StAllDetailsFactor model = (StAllDetailsFactor)allList.get(i);
                        if (row > 65535) {
                            ws.setColumnView(0, 25);
                            ws.setColumnView(1, 25);
                            ws.setColumnView(2, 25);

                            for(int j = 0; j < factorList.size(); ++j) {
                                ws.setColumnView(3 + j, 20);
                            }

                            row = 0;
                        }

                        Label labelC0 = new Label(0, row, model.getStcd(), contentFormat);
                        ws.addCell(labelC0);
                        labelC1 = new Label(1, row, stnm, contentFormat);
                        ws.addCell(labelC1);
                        Label labelC2 = new Label(2, row, model.getTm() != null ? DateUtils.DateToString(model.getTm(), "yyyy-MM-dd HH:mm:ss") : "", contentFormat);
                        ws.addCell(labelC2);
                        BigDecimal bigValuey;
                        if ("pn05".equals(flag)) {
                            BigDecimal bigValuex = null;
                            if ("pn05".equals(flag)) {
                                bigValuex = model.getPn05();
                            }

                            Double valuex = bigValuex != null ? bigValuex.doubleValue() : null;
                            ws.addCell(new Label(3, row, "" + (valuex == null ? "--" : valuex), contentFormat));
                            bigValuex = model.getpJ();
                            Double valuez = bigValuex != null ? bigValuex.doubleValue() : null;
                            ws.addCell(new Label(4, row, "" + (valuez == null ? "--" : valuez), contentFormat));
                            bigValuey = null;
                            BigDecimal bigDecimal = new BigDecimal("0");
                            BigDecimal bigDecimal2 = new BigDecimal("1");
                            if (bigValuey != null) {
                                bigValuey.doubleValue();
                            } else {
                                Object var10000 = null;
                            }

                            if ("pn05".equals(flag)) {
                                bigValuey = model.getTd11();
                                if (bigValuey.compareTo(bigDecimal) == 0) {
                                    ws.addCell(new Label(5, row, "雨", contentFormat));
                                } else if (bigValuey.compareTo(bigDecimal2) == 0) {
                                    ws.addCell(new Label(5, row, "雪", contentFormat));
                                } else {
                                    ws.addCell(new Label(5, row, "--", contentFormat));
                                }
                            }
                        }

                        for(int j = 0; j < factorList.size(); ++j) {
                            PropertyDescriptor pd = new PropertyDescriptor((String)factorList.get(j), StAllDetailsFactor.class);
                            Method rM = pd.getReadMethod();
                            bigValuey = (BigDecimal)rM.invoke(model);
                            Double value = bigValuey != null ? bigValuey.doubleValue() : null;
                            Label title = new Label(3 + j, row, "" + (value == null ? "--" : value), contentFormat);
                            ws.addCell(title);
                            ws.setColumnView(3 + i, 15);
                        }

                        ++row;
                    }
                }
            } catch (Exception var49) {
                var49.printStackTrace();
            }
        }

        try {
            wwb.write();
            wwb.close();
        } catch (IOException var45) {
            var45.printStackTrace();
        } catch (WriteException var46) {
            var46.printStackTrace();
        }

        return fileName;
    }

    public static Map sortMapByKey(Map map) {
        if (map != null && !map.isEmpty()) {
            Map sortMap = new TreeMap(new MapKeyComparator());
            sortMap.putAll(map);
            return sortMap;
        } else {
            return null;
        }
    }
}
