//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.controller;

import com.fourfaith.reportManage.model.StAllDetailsFactor;
import com.fourfaith.reportManage.service.StAllDetailsFactorService;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.service.StAddvcdDService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserFactor;
import com.fourfaith.sysManage.service.SysUserFactorService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.DateUtils;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/history"})
public class HistoryController {
    protected static final String indexJsp = "/page/statist/history/index";
    protected static final String listJsp = "/page/statist/history/list";
    private DecimalFormat decimalFormat = new DecimalFormat("########.###");
    @Autowired
    StAllDetailsFactorService stAllDetailsFactorService;
    @Autowired
    private SysUserFactorService sysUserFactorService;
    @Autowired
    private StFactorNameService stFactorNameService;
    @Autowired
    private StAddvcdDService stAddvcdDService;

    public HistoryController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/statist/history/index");
        mav.addObject("defaultTime", new Date());
        return mav;
    }

    @RequestMapping(
            value = {"/historyReportList"},
            method = {RequestMethod.POST}
    )
    public ModelAndView historyReportList(HttpServletRequest request, boolean isDevice) {
        ModelAndView mav = new ModelAndView("/page/statist/history/list");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        String nodeIds = request.getParameter("nodeIds");
        String stcd_query = request.getParameter("stcd");
        String beginTimeStr = request.getParameter("beginTime");
        String endTimeStr = request.getParameter("endTime");

        try {
            if (StringUtils.isNotBlank(stcd_query)) {
                stcd_query = URLDecoder.decode(stcd_query, "UTF-8");
            } else {
                stcd_query = null;
            }
        } catch (UnsupportedEncodingException var23) {
            var23.printStackTrace();
        }

        List<String> stcdList = null;
        if (StringUtils.isNotBlank(nodeIds)) {
            stcdList = new ArrayList(Arrays.asList(nodeIds.split(",")));
        } else {
            stcdList = new ArrayList(Arrays.asList(""));
        }

        Map<String, Object> params = new HashMap();
        if (isDevice) {
            params.put("stcd", stcdList.get(0));
        } else if (stcdList.size() == 1) {
            params.put("addvcd", stcdList.get(0));
        } else {
            params.put("addvcdList", stcdList);
        }

        params.put("stcd_query", stcd_query);
        Date beginTime = new Date();
        Date endTime = new Date();
        if (beginTimeStr != null) {
            beginTime = DateUtils.StringToDate(beginTimeStr, "yyyy-MM-dd HH:mm");
        }

        if (endTimeStr != null) {
            endTime = DateUtils.StringToDate(endTimeStr, "yyyy-MM-dd HH:mm");
        }

        String tableName = "ST_AllDetails_Factor";
        tableName = StAllDetailsFactorController.getTableName(beginTime);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        params.put("tableName", tableName);
        String s_start = request.getParameter("pageNo");
        int count = 0;

        try {
            count = this.stAllDetailsFactorService.getCount(params);
        } catch (Exception var22) {
            var22.printStackTrace();
        }

        PagingBean pagingBean = PageUtil.page(s_start, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("start", pagingBean.getStart());
        params.put("limit", pagingBean.getLimit());
        mav.addObject("pagingBean", pagingBean);
        List allDeviceFactor = null;

        try {
            allDeviceFactor = this.stAllDetailsFactorService.getHistoryList(params);
        } catch (Exception var21) {
            var21.printStackTrace();
        }

        if (allDeviceFactor != null && allDeviceFactor.size() > 0) {
            Iterator var19 = allDeviceFactor.iterator();

            while(var19.hasNext()) {
                StAllDetailsFactor stAllDetailsFactor = (StAllDetailsFactor)var19.next();
                StAddvcdD stAddvcdD = this.stAddvcdDService.findById(stAllDetailsFactor.getFaddvcd());
                if (stAddvcdD != null && !"".equals(stAddvcdD)) {
                    if ("0".equals(stAddvcdD.getFaddvcd())) {
                        stAllDetailsFactor.setAddvnm2(stAllDetailsFactor.getAddvnm1());
                    } else {
                        stAllDetailsFactor.setAddvnm2(stAddvcdD.getAddvnm());
                    }
                }
            }
        }

        mav.addObject("allDeviceFactorList", allDeviceFactor);
        mav.addObject("stcd", stcd_query);
        mav.addObject("beginTime", beginTime);
        mav.addObject("endTime", endTime);
        mav.addObject("url", "statistic/historyReportList.do?nodeIds=" + nodeIds + "&beginTime=" + beginTime + "&endTime=" + endTime + "&isDevice=" + isDevice + "&stcd=" + (stcd_query == null ? "" : stcd_query));
        Map<String, Object> sfparams = new HashMap();
        sfparams.put("userid", login_user.getId());
        List<SysUserFactor> sysUserFactorList = this.sysUserFactorService.getList(sfparams);
        sysUserFactorList = SysUserFactor.setDefault(login_user.getId(), sysUserFactorList);
        List<String> viewHead = GeneralReportController.getViewHeadList(request, 0, sysUserFactorList, this.stFactorNameService);
        mav.addObject("viewHead", viewHead);
        mav.addObject("sysUserFactorList", sysUserFactorList);
        return mav;
    }

    @RequestMapping(
            value = {"/export"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String historyExport(HttpServletRequest request, HttpServletResponse response) {
        return this.commonExport(request, response, "history");
    }

    @RequestMapping({"/commonExport"})
    public String commonExport(HttpServletRequest request, HttpServletResponse response, String tagType) {
        String nodeIds = request.getParameter("nodeIds");
        String stcd_query = request.getParameter("stcd_query");
        String query_beginTime = request.getParameter("query_beginTime");
        String query_endTime = request.getParameter("query_endTime");
        String isDeviceStr = request.getParameter("isDevice");
        boolean isDevice = StringUtils.isNotBlank(isDeviceStr) ? Boolean.parseBoolean(isDeviceStr) : false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String sdate = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int)(random.nextDouble() * 90000.0D) + 10000;
        String str = sdate + rannum;
        SimpleDateFormat sp = new SimpleDateFormat("yyyy");
        String year = sp.format(date);
        str = str + year + PropertiesUtils.getPara("exportHistoryInfo");
        String pathName = "";

        try {
            pathName = this.historyWrite2(request, nodeIds, stcd_query, query_beginTime, query_endTime, isDevice);
        } catch (Exception var20) {
            var20.printStackTrace();
        }

        return pathName;
    }

    @RequestMapping({"/historyDownload"})
    public void historyDownload(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
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

    public String historyWrite2(HttpServletRequest request, String nodeIds, String stcd_query, String query_beginTime, String query_endTime, boolean isDevice) throws Exception {
        Map<String, Object> params = new HashMap();
        List<String> stcdList = null;
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

        try {
            if (StringUtils.isNotBlank(stcd_query)) {
                stcd_query = URLDecoder.decode(stcd_query, "UTF-8");
            } else {
                stcd_query = null;
            }
        } catch (UnsupportedEncodingException var56) {
            var56.printStackTrace();
        }

        params.put("stcd_query", stcd_query);
        String pattern = "yyyy-MM-dd HH:mm";
        Date beginTime = DateUtils.StringToDate(query_beginTime, pattern);
        Date endTime = DateUtils.StringToDate(query_endTime, pattern);
        String tableName = StAllDetailsFactorController.getTableName(beginTime);
        params.put("tableName", tableName);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        String basePath = PropertiesUtils.getPara("downFileDir");
        String savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String sdate = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int)(random.nextDouble() * 90000.0D) + 10000;
        String str = sdate + rannum;
        SimpleDateFormat sp = new SimpleDateFormat("yyyy");
        String year = sp.format(date);
        str = "历史报表(" + str + year + ")";
        String fileName = savePath + str + ".xls";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        WritableWorkbook wwb = null;

        try {
            wwb = Workbook.createWorkbook(new File(fileName));
        } catch (IOException var55) {
            var55.printStackTrace();
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
            } catch (WriteException var54) {
                var54.printStackTrace();
            }

            SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
            Map<String, Object> sfparams = new HashMap();
            sfparams.put("userid", login_user.getId());
            List<SysUserFactor> sysUserFactorList = this.sysUserFactorService.getList(sfparams);
            sysUserFactorList = SysUserFactor.setDefault(login_user.getId(), sysUserFactorList);
            List<String> viewHead = GeneralReportController.getViewHeadList(request, 0, sysUserFactorList, this.stFactorNameService);
            WritableSheet ws = wwb.createSheet("历史报表", 0);

            Label labelC1;
            Label labelC2;
            Label labelC3;
            Label labelC4;
            try {
                ws.mergeCells(0, 0, viewHead.size() + 2, 0);
                String titleView = "历史报表";
                if (beginTime != null) {
                    String bTime = sdf.format(beginTime);
                    String eTime = sdf.format(endTime);
                    titleView = titleView + "(" + bTime + "到" + eTime + ")";
                }

                Label headerTitle = new Label(0, 0, titleView, headerFormat2);
                ws.addCell(headerTitle);
                ws.setColumnView(0, 10);
                Label title1 = new Label(0, 1, "测站名称", headerFormat2);
                ws.addCell(title1);
                ws.setColumnView(0, 20);
                Label title2 = new Label(1, 1, "测站编码", headerFormat2);
                ws.addCell(title2);
                ws.setColumnView(1, 20);
                labelC1 = new Label(2, 1, "水文站", headerFormat2);
                ws.addCell(labelC1);
                ws.setColumnView(1, 20);
                labelC2 = new Label(3, 1, "水文局", headerFormat2);
                ws.addCell(labelC2);
                ws.setColumnView(1, 20);
                labelC3 = new Label(4, 1, "日降雨量", headerFormat2);
                ws.addCell(labelC3);
                ws.setColumnView(1, 20);

                for(int i = 0; i < viewHead.size(); ++i) {
                    String head = (String)viewHead.get(i);
                    ws.addCell(new Label(5 + i, 1, head, headerFormat2));
                    ws.setColumnView(5 + i, 15);
                }

                labelC4 = new Label(viewHead.size() + 5, 1, "采集时间", headerFormat2);
                ws.addCell(labelC4);
                ws.setColumnView(viewHead.size() + 2, 25);
            } catch (RowsExceededException var57) {
                var57.printStackTrace();
            } catch (WriteException var58) {
                var58.printStackTrace();
            }

            List<StAllDetailsFactor> historyAllList = this.stAllDetailsFactorService.getHistoryList(params);
            if (historyAllList != null && historyAllList.size() > 0) {
                for(int i = 0; i < historyAllList.size(); ++i) {
                    StAllDetailsFactor model = (StAllDetailsFactor)historyAllList.get(i);
                    StAddvcdD stAddvcdD = this.stAddvcdDService.findById(model.getFaddvcd());
                    if (stAddvcdD != null && !"".equals(stAddvcdD)) {
                        if ("0".equals(stAddvcdD.getFaddvcd())) {
                            model.setAddvnm2(model.getAddvnm1());
                        } else {
                            model.setAddvnm2(stAddvcdD.getAddvnm());
                        }
                    }

                    labelC1 = new Label(0, i + 2, model.getStnm(), contentFormat);
                    labelC2 = new Label(1, i + 2, model.getStcd(), contentFormat);
                    labelC3 = new Label(2, i + 2, model.getAddvnm1(), contentFormat);
                    labelC4 = new Label(3, i + 2, model.getAddvnm2(), contentFormat);
                    Label labelC5 = new Label(4, i + 2, "" + model.getpJ(), contentFormat);
                    ws.addCell(labelC1);
                    ws.addCell(labelC2);
                    ws.addCell(labelC3);
                    ws.addCell(labelC4);
                    ws.addCell(labelC5);

                    String data;
                    for(int j = 0; j < sysUserFactorList.size(); ++j) {
                        data = ((SysUserFactor)sysUserFactorList.get(j)).getFactor();
                        Field sField = StAllDetailsFactor.class.getDeclaredField(data);
                        sField.setAccessible(true);
                        String value = this.dataFormat((BigDecimal)sField.get(model));
                        if (data.equals("td11")) {
                            if (value.equals("0")) {
                                ws.addCell(new Label(j + 5, i + 2, "雨", contentFormat));
                            } else if (value.equals("1")) {
                                ws.addCell(new Label(j + 5, i + 2, "雪", contentFormat));
                            } else {
                                ws.addCell(new Label(j + 5, i + 2, "", contentFormat));
                            }
                        } else {
                            ws.addCell(new Label(j + 5, i + 2, value, contentFormat));
                        }
                    }

                    Label labelC10;
                    if (model.getTm() != null) {
                        data = sdf.format(model.getTm());
                        labelC10 = new Label(sysUserFactorList.size() + 5, i + 2, data, contentFormat);
                    } else {
                        labelC10 = new Label(sysUserFactorList.size() + 5, i + 2, "--", contentFormat);
                    }

                    ws.addCell(labelC10);
                }
            }
        }

        try {
            wwb.write();
            wwb.close();
        } catch (IOException var52) {
            var52.printStackTrace();
        } catch (WriteException var53) {
            var53.printStackTrace();
        }

        return str + ".xls";
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
}
