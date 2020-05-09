//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.reportManage.model.MonthAllDetails;
import com.fourfaith.reportManage.model.StAllDetailsFactor;
import com.fourfaith.reportManage.service.MonthAllDetailsService;
import com.fourfaith.reportManage.service.StAllDetailsFactorService;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserFactor;
import com.fourfaith.sysManage.service.SysUserFactorService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.FactorName;
import com.fourfaith.utils.MapKeyComparator;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.PropertiesUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
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
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/generalReport"})
public class GeneralReportController {
    protected static final String indexJsp = "/page/statist/generalReport/index";
    protected static final String listJsp = "/page/statist/generalReport/list";
    protected static final String detailListJsp = "/page/statist/generalReport/detailList";
    private DecimalFormat decimalFormat = new DecimalFormat("########.###");
    @Autowired
    StAllDetailsFactorService stAllDetailsFactorService;
    @Autowired
    private SysUserFactorService sysUserFactorService;
    @Autowired
    private StFactorNameService stFactorNameService;
    @Autowired
    private MonthAllDetailsService monthAllDetailsService;

    public GeneralReportController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/statist/generalReport/index");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(2, -1);
        Date m = c.getTime();
        mav.addObject("defaultTime", new Date());
        mav.addObject("defaultTime1", m);
        return mav;
    }

    @RequestMapping({"generalList"})
    public ModelAndView generalList(HttpServletRequest request, String nodeIds, String searchType, boolean isDevice) {
        ModelAndView mav = new ModelAndView("/page/statist/generalReport/list");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Date beginTime = null;
        Date endTime = null;
        String query_beginTime = request.getParameter("query_beginTime");
        String query_endTime = request.getParameter("query_endTime");
        String query_Time = request.getParameter("query_Time");
        String tableName = "ST_AllDetails_Factor";
        Date date;
        if ("1".equals(searchType)) {
            beginTime = DateUtils.StringToDate(query_beginTime, "yyyy-MM-dd HH:mm");
            endTime = DateUtils.StringToDate(query_endTime, "yyyy-MM-dd HH:mm");
        } else {
            String pattern;
            if ("2".equals(searchType)) {
                pattern = "yyyy-MM-dd";
                date = DateUtils.StringToDate(query_Time, pattern);
                beginTime = DateUtils.setMinTimeForDate(date);
                endTime = DateUtils.setMaxTimeForDate(date);
            } else if ("3".equals(searchType)) {
                pattern = "yyyy-MM";
                date = DateUtils.StringToDate(query_Time, pattern);
                beginTime = DateUtils.setMinTimeForMonthDate(date);
                endTime = DateUtils.setMaxTimeForMonthDate(date);
            } else if ("4".equals(searchType)) {
                pattern = "yyyy";
                date = DateUtils.StringToDate(query_Time, pattern);
                beginTime = DateUtils.setMinTimeForYearDate(date);
                endTime = DateUtils.setMaxTimeForYearDate(date);
            }
        }

        Map<String, Object> params = new HashMap();
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        date = null;
        ArrayList stcdList;
        if (StringUtils.isNotBlank(nodeIds)) {
            stcdList = new ArrayList(Arrays.asList(nodeIds.split(",")));
        } else {
            stcdList = new ArrayList(Arrays.asList(""));
        }

        if (isDevice) {
            params.put("stcd", stcdList.get(0));
        } else if (stcdList.size() == 1) {
            params.put("addvcd", stcdList.get(0));
        } else {
            params.put("addvcdList", stcdList);
        }

        tableName = StAllDetailsFactorController.getTableName(beginTime);
        new ArrayList();
        String pageNo = request.getParameter("pageNo");
        PagingBean pagingBean = null;
        List list;
        if (!"4".equals(searchType)) {
            params.put("tableName", tableName);
            int counts = this.stAllDetailsFactorService.getStatistCount(params);
            pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, counts, PagingBean.DEFAULT_PAGE_SIZE);
            params.put("start", pagingBean.getStart());
            params.put("limit", pagingBean.getLimit());
            list = this.stAllDetailsFactorService.getStatistList(params);
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(beginTime);
            int year = cal.get(1);
            params.put("year", year);
            int count = this.monthAllDetailsService.getStatistCount(params);
            pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
            params.put("start", pagingBean.getStart());
            params.put("limit", pagingBean.getLimit());
            List<String> stdList = this.monthAllDetailsService.getStatistStcd(params);
            params.put("stcdList", stdList);
            list = this.getYearStatistList(params);
        }

        mav.addObject("list", list);
        mav.addObject("pagingBean", pagingBean);
        Map<String, Object> sfparams = new HashMap();
        sfparams.put("userid", login_user.getId());
        List<SysUserFactor> sysUserFactorList = this.sysUserFactorService.getList(sfparams);
        sysUserFactorList = SysUserFactor.setDefault(login_user.getId(), sysUserFactorList);
        List<String> viewHead = getViewHeadList(request, 1, sysUserFactorList, this.stFactorNameService);
        mav.addObject("viewHead", viewHead);
        mav.addObject("sysUserFactorList", sysUserFactorList);
        return mav;
    }

    @RequestMapping({"generalList1"})
    public ModelAndView generalList1(HttpServletRequest request, String nodeIds, String searchType, boolean isDevice) {
        ModelAndView mav = new ModelAndView("/page/statist/generalReport/list");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Date beginTime = null;
        Date endTime = null;
        String query_Time = request.getParameter("query_Time");
        String pattern;
        Date date;
        if ("2".equals(searchType)) {
            pattern = "yyyy-MM-dd";
            date = DateUtils.StringToDate(query_Time, pattern);
            beginTime = DateUtils.setMinTimeForDate(date);
            endTime = DateUtils.setMaxTimeForDate(date);
        } else if ("3".equals(searchType)) {
            pattern = "yyyy-MM";
            date = DateUtils.StringToDate(query_Time, pattern);
            beginTime = DateUtils.setMinTimeForMonthDate(date);
            endTime = DateUtils.setMaxTimeForMonthDate(date);
        } else if ("4".equals(searchType)) {
            pattern = "yyyy";
            date = DateUtils.StringToDate(query_Time, pattern);
            beginTime = DateUtils.setMinTimeForYearDate(date);
            endTime = DateUtils.setMaxTimeForYearDate(date);
        }

        Map<String, Object> params = new HashMap();
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        date = null;
        ArrayList stcdList;
        if (StringUtils.isNotBlank(nodeIds)) {
            stcdList = new ArrayList(Arrays.asList(nodeIds.split(",")));
        } else {
            stcdList = new ArrayList(Arrays.asList(""));
        }

        if (isDevice) {
            params.put("stcd", stcdList.get(0));
        } else if (stcdList.size() == 1) {
            params.put("addvcd", stcdList.get(0));
        } else {
            params.put("addvcdList", stcdList);
        }

        new ArrayList();
        new ArrayList();
        String pageNo = request.getParameter("pageNo");
        PagingBean pagingBean = null;
        List list1;
        Calendar cal;
        int year;
        int month;
        int day;
        if ("3".equals(searchType)) {
            cal = Calendar.getInstance();
            cal.setTime(beginTime);
            year = cal.get(1);
            month = cal.get(2);
            params.put("year", year);
            params.put("month", month + 1);
            day = this.monthAllDetailsService.getStatistCount(params);
            pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, day, PagingBean.DEFAULT_PAGE_SIZE);
            params.put("start", pagingBean.getStart());
            params.put("limit", pagingBean.getLimit());
            List<String> stdList = this.monthAllDetailsService.getStatistStcd(params);
            params.put("stcdList", stdList);
            list1 = this.getYearStatistList(params);
            mav.addObject("list", list1);
        } else if ("2".equals(searchType)) {
            cal = Calendar.getInstance();
            cal.setTime(beginTime);
            year = cal.get(1);
            month = cal.get(2);
            day = cal.get(5);
            params.put("year", year);
            params.put("month", month + 1);
            params.put("day", day);
            int count = this.monthAllDetailsService.getStatistCount(params);
            pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
            params.put("start", pagingBean.getStart());
            params.put("limit", pagingBean.getLimit());
            List<String> stdList = this.monthAllDetailsService.getStatistStcd(params);
            params.put("stcdList", stdList);
            list1 = this.getYearStatistList(params);
            mav.addObject("list", list1);
        } else {
            cal = Calendar.getInstance();
            cal.setTime(beginTime);
            year = cal.get(1);
            params.put("year", year);
            month = this.monthAllDetailsService.getStatistCount(params);
            pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, month, PagingBean.DEFAULT_PAGE_SIZE);
            params.put("start", pagingBean.getStart());
            params.put("limit", pagingBean.getLimit());
            List<String> stdList = this.monthAllDetailsService.getStatistStcd(params);
            params.put("stcdList", stdList);
            list1 = this.getYearStatistList(params);
            mav.addObject("list", list1);
        }

        mav.addObject("pagingBean", pagingBean);
        Map<String, Object> sfparams = new HashMap();
        sfparams.put("userid", login_user.getId());
        List<SysUserFactor> sysUserFactorList = this.sysUserFactorService.getList(sfparams);
        sysUserFactorList = SysUserFactor.setDefault(login_user.getId(), sysUserFactorList);
        List<String> viewHead = getViewHeadList(request, 1, sysUserFactorList, this.stFactorNameService);
        mav.addObject("viewHead", viewHead);
        mav.addObject("sysUserFactorList", sysUserFactorList);
        return mav;
    }

    @RequestMapping({"/detailList"})
    public ModelAndView detailList(HttpServletRequest request, String stcd, String query_beginTime, String query_endTime) {
        ModelAndView mav = new ModelAndView("/page/statist/generalReport/detailList");
        String pattern = "yyyy-MM-dd HH:mm:ss";
        Date beginTime = DateUtils.StringToDate(query_beginTime, pattern);
        Date endTime = DateUtils.StringToDate(query_endTime, pattern);
        Map<String, Object> params = new HashMap();
        params.put("stcd", stcd);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        String tableName = StAllDetailsFactorController.getTableName(beginTime);
        params.put("tableName", tableName);
        String pageNo = request.getParameter("pageNo");
        int count = this.stAllDetailsFactorService.getCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("start", pagingBean.getStart());
        params.put("limit", pagingBean.getLimit());
        List<StAllDetailsFactor> detList = this.stAllDetailsFactorService.getList(params);
        mav.addObject("detList", detList);
        mav.addObject("stcd", stcd);
        mav.addObject("pagingBean", pagingBean);
        mav.addObject("url", "generalReport/detailList.do?stcd=" + stcd + "&query_beginTime=" + query_beginTime + "&query_endTime=" + query_endTime);
        return mav;
    }

    @RequestMapping(
            value = {"/exportExcelData"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String exportExcelData(HttpServletRequest request, HttpServletResponse response, String nodeIds, String searchType, boolean isDevice) {
        Date beginTime = null;
        Date endTime = null;
        String tableName = "ST_AllDetails_Factor";
        String query_Time;
        String pattern;
        if ("1".equals(searchType)) {
            query_Time = request.getParameter("query_beginTime");
            pattern = request.getParameter("query_endTime");
            beginTime = DateUtils.StringToDate(query_Time, "yyyy-MM-dd HH:mm");
            endTime = DateUtils.StringToDate(pattern, "yyyy-MM-dd HH:mm");
        } else {
            query_Time = request.getParameter("query_Time");
            Date date;
            if ("2".equals(searchType)) {
                pattern = "yyyy-MM-dd";
                date = DateUtils.StringToDate(query_Time, pattern);
                beginTime = DateUtils.setMinTimeForDate(date);
                endTime = DateUtils.setMaxTimeForDate(date);
            } else if ("3".equals(searchType)) {
                pattern = "yyyy-MM";
                date = DateUtils.StringToDate(query_Time, pattern);
                beginTime = DateUtils.setMinTimeForMonthDate(date);
                endTime = DateUtils.setMaxTimeForMonthDate(date);
            } else if ("4".equals(searchType)) {
                pattern = "yyyy";
                date = DateUtils.StringToDate(query_Time, pattern);
                beginTime = DateUtils.setMinTimeForYearDate(date);
                endTime = DateUtils.setMaxTimeForYearDate(date);
            }
        }

        Map<String, Object> params = new HashMap();
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        pattern = null;
        ArrayList stcdList;
        if (StringUtils.isNotBlank(nodeIds)) {
            stcdList = new ArrayList(Arrays.asList(nodeIds.split(",")));
        } else {
            stcdList = new ArrayList(Arrays.asList(""));
        }

        if (isDevice) {
            params.put("stcd", stcdList.get(0));
        } else if (stcdList.size() == 1) {
            params.put("addvcd", stcdList.get(0));
        } else {
            params.put("addvcdList", stcdList);
        }

        tableName = StAllDetailsFactorController.getTableName(beginTime);
        new ArrayList();
        List sysUserFactorList;
        List list;
        if (!"4".equals(searchType)) {
            params.put("tableName", tableName);
            list = this.stAllDetailsFactorService.getStatistList(params);
        } else {
            Calendar cal = Calendar.getInstance();
            cal.setTime(beginTime);
            int year = cal.get(1);
            params.put("year", year);
            sysUserFactorList = this.monthAllDetailsService.getStatistStcd(params);
            params.put("stcdList", sysUserFactorList);
            list = this.getYearStatistList(params);
        }

        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> sfparams = new HashMap();
        sfparams.put("userid", login_user.getId());
        sysUserFactorList = this.sysUserFactorService.getList(sfparams);
        sysUserFactorList = SysUserFactor.setDefault(login_user.getId(), sysUserFactorList);
        List<String> viewHead = getViewHeadList(request, 1, sysUserFactorList, this.stFactorNameService);
        Map<String, Object> hm = new HashMap();
        StringBuilder str = new StringBuilder();
        String fileName = null;
        str.setLength(0);
        str.append("数据报表");
        str.append("(");
        str.append(DateUtils.DateToString(beginTime, "yyyyMMddHHmm"));
        str.append("-");
        str.append(DateUtils.DateToString(endTime, "yyyyMMddHHmm"));
        str.append(")");
        str.append(".xls");

        try {
            fileName = this.dataWrite(request, str.toString(), list, beginTime, endTime, sysUserFactorList, viewHead);
            hm.put("success", true);
            hm.put("fileName", fileName);
        } catch (Exception var20) {
            hm.put("success", false);
            var20.printStackTrace();
        }

        return JSONObject.toJSONString(hm);
    }

    private List<StAllDetailsFactor> getYearStatistList(Map<String, Object> params) {
        List<StAllDetailsFactor> list = new ArrayList();
        params.put("orderByCon", "stcd");
        List<MonthAllDetails> monthList = this.monthAllDetailsService.getList(params);
        Map<String, List<MonthAllDetails>> map = new LinkedHashMap();
        String _lastStnm;
        if (monthList != null) {
            List<MonthAllDetails> _list = new ArrayList();
            String _lastStcd = null;
            _lastStnm = null;

            for(int i = 0; i < monthList.size(); ++i) {
                MonthAllDetails model = (MonthAllDetails)monthList.get(i);
                if (_lastStcd != null && !_lastStcd.equals(model.getStcd())) {
                    map.put(_lastStcd + "," + _lastStnm, _list);
                    _list = new ArrayList();
                }

                _list.add(model);
                _lastStcd = model.getStcd();
                _lastStnm = model.getStnm();
                if (i == monthList.size() - 1) {
                    map.put(_lastStcd + "," + _lastStnm, _list);
                }
            }
        }

        StAllDetailsFactor model;
        for(Iterator var20 = map.entrySet().iterator(); var20.hasNext(); list.add(model)) {
            Entry<String, List<MonthAllDetails>> entry = (Entry)var20.next();
            _lastStnm = (String)entry.getKey();
            String stcd = _lastStnm.split(",")[0];
            String stnm = _lastStnm.split(",")[1];
            model = new StAllDetailsFactor();
            List<MonthAllDetails> _list = (List)entry.getValue();
            model.setStcd(stcd);
            model.setStnm(stnm);
            if (_list != null && _list.size() > 0) {
                for(int i = 0; i < _list.size(); ++i) {
                    MonthAllDetails _mad = (MonthAllDetails)_list.get(i);
                    String factor = _mad.getFactor();
                    if (factor != null && !"pj".equals(factor)) {
                        try {
                            Field minField = StAllDetailsFactor.class.getDeclaredField("min" + factor);
                            minField.setAccessible(true);
                            minField.set(model, _mad.getMinFactorValue());
                            Field maxField = StAllDetailsFactor.class.getDeclaredField("max" + factor);
                            maxField.setAccessible(true);
                            maxField.set(model, _mad.getMaxFactorValue());
                            Field sField = StAllDetailsFactor.class.getDeclaredField(factor);
                            sField.setAccessible(true);
                            sField.set(model, "pn05".equals(factor) ? _mad.getSumFactorValue() : _mad.getAvgFactorValue());
                        } catch (Exception var18) {
                            var18.printStackTrace();
                        }
                    }
                }
            }
        }

        return list;
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

    public String dataWrite(HttpServletRequest request, String fileName, List<StAllDetailsFactor> list, Date beginTime, Date endTime, List<SysUserFactor> sysUserFactorList, List<String> viewHead) {
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
        } catch (IOException var38) {
            var38.printStackTrace();
        }

        if (wwb != null) {
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
            } catch (WriteException var37) {
                var37.printStackTrace();
            }

            try {
                WritableSheet ws = wwb.createSheet("数据报表", 0);
                ws.mergeCells(0, 0, viewHead.size() * 3, 0);
                String titleView = "数据报表";
                if (beginTime != null) {
                    String bTime = DateUtils.DateToString(beginTime, "yyyy/MM/dd HH:mm:ss");
                    String eTime = DateUtils.DateToString(endTime, "yyyy/MM/dd HH:mm:ss");
                    titleView = titleView + "(" + bTime + "至" + eTime + ")";
                }

                Label headerTitle = new Label(0, 0, titleView, headerFormat);
                ws.addCell(headerTitle);
                Label title1 = new Label(0, 1, "测站名称", headerFormat2);
                ws.addCell(title1);
                ws.setColumnView(0, 35);
                ws.mergeCells(0, 1, 0, 2);
                int col = 1;

                int i;
                for(i = 0; i < viewHead.size(); ++i) {
                    ws.mergeCells(col, 1, col + 2, 1);
                    String head = (String)viewHead.get(i);
                    ws.addCell(new Label(col, 1, head, headerFormat2));
                    ws.setColumnView(0, 40);
                    ws.addCell(new Label(col, 2, "最小值", headerFormat2));
                    ws.addCell(new Label(col + 1, 2, "最大值", headerFormat2));
                    ws.addCell(new Label(col + 2, 2, "雨量".equals(head) ? "累计值" : "平均值", headerFormat2));
                    col += 3;
                }

                if (list != null) {
                    for(i = 0; i < list.size(); ++i) {
                        StAllDetailsFactor model = (StAllDetailsFactor)list.get(i);
                        int row = i + 3;
                        Label labelC0 = new Label(0, row, model.getStnm(), contentFormat);
                        ws.addCell(labelC0);
                        int cols = 1;

                        for(int j = 0; j < sysUserFactorList.size(); ++j) {
                            String factor = ((SysUserFactor)sysUserFactorList.get(j)).getFactor();
                            if (!factor.equals("td11")) {
                                Field minField = StAllDetailsFactor.class.getDeclaredField("min" + factor);
                                minField.setAccessible(true);
                                ws.addCell(new Label(cols, row, this.dataFormat((BigDecimal)minField.get(model)), contentFormat));
                                Field maxField = StAllDetailsFactor.class.getDeclaredField("max" + factor);
                                maxField.setAccessible(true);
                                ws.addCell(new Label(cols + 1, row, this.dataFormat((BigDecimal)maxField.get(model)), contentFormat));
                                Field sField = StAllDetailsFactor.class.getDeclaredField(factor);
                                sField.setAccessible(true);
                                ws.addCell(new Label(cols + 2, row, this.dataFormat((BigDecimal)sField.get(model)), contentFormat));
                                cols += 3;
                            }
                        }
                    }
                }
            } catch (Exception var39) {
                var39.printStackTrace();
            }
        }

        try {
            wwb.write();
            wwb.close();
        } catch (IOException var35) {
            var35.printStackTrace();
        } catch (WriteException var36) {
            var36.printStackTrace();
        }

        return fileName;
    }

    private String dataFormat(BigDecimal bigDecimal) {
        String result = "";
        if (bigDecimal != null) {
            result = this.decimalFormat.format(bigDecimal.doubleValue());
        } else {
            result = "--";
        }

        return result;
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

    public static List<String> getViewHeadList(HttpServletRequest request, int isView, List<SysUserFactor> sysUserFactorList, StFactorNameService stFactorNameService) {
        List<String> viewHead = new ArrayList();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        ArrayList filterFactor;
        if (isView == 0) {
            filterFactor = new ArrayList(Arrays.asList("", ""));
        } else {
            filterFactor = new ArrayList(Arrays.asList("td11", ""));
        }

        Map<String, FactorName> factorNameMap = stFactorNameService.getFactorMap(login_user.getEnterpriseid(), filterFactor, request);
        if (sysUserFactorList != null) {
            for(int i = 0; i < sysUserFactorList.size(); ++i) {
                SysUserFactor model = (SysUserFactor)sysUserFactorList.get(i);
                if (factorNameMap.containsKey(model.getFactor())) {
                    viewHead.add(((FactorName)factorNameMap.get(model.getFactor())).getName());
                }
            }
        }

        return viewHead;
    }
}
