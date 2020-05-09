//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.reportManage.model.StAllDetailsFactor;
import com.fourfaith.reportManage.service.MonthAllDetailsService;
import com.fourfaith.reportManage.service.StAllDetailsFactorService;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.siteManage.service.StAddvcdDService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserFactor;
import com.fourfaith.sysManage.service.SysUserFactorService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.FactorName;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import com.fourfaith.utils.PropertiesUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping({"/almanacReport"})
public class AlmanacReportController {
    protected static final String indexJsp = "/page/statist/almanacReport/index";
    protected static final String listJsp = "/page/statist/almanacReport/list";
    private DecimalFormat decimalFormat = new DecimalFormat("########.###");
    @Autowired
    StAllDetailsFactorService stAllDetailsFactorService;
    @Autowired
    private SysUserFactorService sysUserFactorService;
    @Autowired
    private StFactorNameService stFactorNameService;
    @Autowired
    private StAddvcdDService stAddvcdDService;
    @Autowired
    private MonthAllDetailsService monthAllDetailsService;

    public AlmanacReportController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/statist/almanacReport/index");
        mav.addObject("defaultTime", new Date());
        return mav;
    }

    @RequestMapping({"/list"})
    public ModelAndView historyReportList(HttpServletRequest request, String nodeIds, String searchType, boolean isDevice) {
        ModelAndView mav = new ModelAndView("/page/statist/almanacReport/list");
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
        params.put("tableName", tableName);
        int count = this.stAllDetailsFactorService.getAlmanacReportCount(params);
        pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("start", pagingBean.getStart());
        params.put("limit", pagingBean.getLimit());
        List<StAllDetailsFactor> list = this.stAllDetailsFactorService.getAlmanacReportList(params);
        mav.addObject("list", list);
        mav.addObject("pagingBean", pagingBean);
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
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
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
        String pageNo = request.getParameter("pageNo");
        PagingBean pagingBean = null;
        params.put("tableName", tableName);
        this.stAllDetailsFactorService.getAlmanacReportCount(params);
        List<StAllDetailsFactor> list = this.stAllDetailsFactorService.getAlmanacReportList(params);
        Map<String, Object> sfparams = new HashMap();
        sfparams.put("userid", login_user.getId());
        List<SysUserFactor> sysUserFactorList = this.sysUserFactorService.getList(sfparams);
        sysUserFactorList = SysUserFactor.setDefault(login_user.getId(), sysUserFactorList);
        List<String> viewHead = getViewHeadList(request, 1, sysUserFactorList, this.stFactorNameService);
        Map<String, Object> hm = new HashMap();
        StringBuilder str = new StringBuilder();
        String fileName = null;
        str.setLength(0);
        str.append("年鉴报表");
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
        } catch (Exception var23) {
            hm.put("success", false);
            var23.printStackTrace();
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
        } catch (IOException var39) {
            var39.printStackTrace();
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
            } catch (WriteException var38) {
                var38.printStackTrace();
            }

            try {
                WritableSheet ws = wwb.createSheet("数据报表", 0);
                ws.mergeCells(0, 0, viewHead.size() + 2, 0);
                String titleView = "年鉴报表";
                if (beginTime != null) {
                    String bTime = DateUtils.DateToString(beginTime, "yyyy/MM/dd HH:mm:ss");
                    String eTime = DateUtils.DateToString(endTime, "yyyy/MM/dd HH:mm:ss");
                    titleView = titleView + "(" + bTime + "至" + eTime + ")";
                }

                Label headerTitle = new Label(0, 0, titleView, headerFormat2);
                ws.addCell(headerTitle);
                ws.setColumnView(0, 20);
                Label title1 = new Label(0, 1, "测站编码", headerFormat2);
                ws.addCell(title1);
                ws.setColumnView(1, 20);
                Label title2 = new Label(1, 1, "测站名称", headerFormat2);
                ws.addCell(title2);
                ws.setColumnView(2, 20);
                Label title3 = new Label(2, 1, "日降雨量", headerFormat2);
                ws.addCell(title3);
                ws.setColumnView(3, 20);
                Label title4 = new Label(3, 1, "电压", headerFormat2);
                ws.addCell(title4);
                ws.setColumnView(4, 20);
                Label title5 = new Label(4, 1, "信号强度", headerFormat2);
                ws.addCell(title5);
                ws.setColumnView(5, 20);
                Label title6 = new Label(5, 1, "采集时间", headerFormat2);
                ws.addCell(title6);
                ws.setColumnView(6, 20);
                SimpleDateFormat sdf = new SimpleDateFormat();
                if (list != null && list.size() > 0) {
                    for(int i = 0; i < list.size(); ++i) {
                        StAllDetailsFactor model = (StAllDetailsFactor)list.get(i);
                        Label labelC1 = new Label(0, i + 2, model.getStcd(), contentFormat);
                        Label labelC2 = new Label(1, i + 2, model.getStnm(), contentFormat);
                        ws.addCell(labelC1);
                        ws.addCell(labelC2);
                        Label labelC6;
                        if (model.getpJ() != null) {
                            labelC6 = new Label(2, i + 2, "" + model.getpJ(), contentFormat);
                            ws.addCell(labelC6);
                        } else {
                            labelC6 = new Label(2, i + 2, "-", contentFormat);
                            ws.addCell(labelC6);
                        }

                        if (model.getVoltage() != null) {
                            labelC6 = new Label(3, i + 2, "" + model.getVoltage(), contentFormat);
                            ws.addCell(labelC6);
                        } else {
                            labelC6 = new Label(3, i + 2, "-", contentFormat);
                            ws.addCell(labelC6);
                        }

                        if (model.getSignalinten() != null) {
                            labelC6 = new Label(4, i + 2, "" + model.getSignalinten(), contentFormat);
                            ws.addCell(labelC6);
                        } else {
                            labelC6 = new Label(4, i + 2, "-", contentFormat);
                            ws.addCell(labelC6);
                        }

                        labelC6 = null;
                        if (model.getTm() != null) {
                            String data = sdf.format(model.getTm());
                            labelC6 = new Label(5, i + 2, data, contentFormat);
                        } else {
                            labelC6 = new Label(5, i + 2, "--", contentFormat);
                        }

                        ws.addCell(labelC6);
                    }
                }
            } catch (Exception var40) {
                var40.printStackTrace();
            }
        }

        try {
            wwb.write();
            wwb.close();
        } catch (IOException var36) {
            var36.printStackTrace();
        } catch (WriteException var37) {
            var37.printStackTrace();
        }

        return fileName;
    }
}
