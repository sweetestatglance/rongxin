//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.baseManager.controller;

import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.PropertiesUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/statistic"})
public class StatisticController {
    protected static final String indexJsp = "/page/statist/index";
    protected static final String historyReportJsp = "/page/statist/historyReport";
    protected static final String historyReportListJsp = "/page/statist/historyReportList";
    @Autowired
    private SysMenuService sysMenuService;

    public StatisticController() {
    }

    @RequestMapping({"/index"})
    public ModelAndView index(HttpServletRequest request, String menuId, String menuCode) {
        ModelAndView mav = new ModelAndView("/page/statist/index");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        new ArrayList();
        List<SysMenu> sysMenuList = this.sysMenuService.getListByRoleidAndMenuid(menuId, login_user.getSysrole().getId());
        mav.addObject("sysMenuList", sysMenuList);
        return mav;
    }

    @RequestMapping({"/historyReport"})
    public ModelAndView historyReport(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/statist/historyReport");
        return mav;
    }

    @RequestMapping(
            value = {"/historyExport"},
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
        pathName = this.historyWrite(request, nodeIds, stcd_query, query_beginTime, query_endTime, isDevice);
        return pathName;
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

    public String historyWrite(HttpServletRequest request, String nodeIds, String stcd_query, String query_beginTime, String query_endTime, boolean isDevice) {
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
        } catch (UnsupportedEncodingException var45) {
            var45.printStackTrace();
        }

        params.put("stcd_query", stcd_query);
        String pattern = "yyyy-MM-dd HH:mm";
        Date beginTime = DateUtils.StringToDate(query_beginTime, pattern);
        Date endTime = DateUtils.StringToDate(query_endTime, pattern);
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
        str = str + year + PropertiesUtils.getPara("exportHistoryInfo");
        String fileName = savePath + str + ".xls";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        WritableWorkbook wwb = null;

        try {
            wwb = Workbook.createWorkbook(new File(fileName));
        } catch (IOException var44) {
            var44.printStackTrace();
        }

        if (wwb != null) {
            WritableSheet ws = wwb.createSheet("历史报表", 0);

            try {
                ws.mergeCells(0, 0, 11, 0);
                String titleView = "历史报表";
                if (beginTime != null) {
                    String bTime = sdf.format(beginTime);
                    String eTime = sdf.format(endTime);
                    titleView = titleView + "(" + bTime + "到" + eTime + ")";
                }

                Label headerTitle = new Label(0, 0, titleView, this.getHeaderCellStyle());
                ws.addCell(headerTitle);
                ws.setColumnView(0, 10);
                Label title1 = new Label(0, 1, "序号", this.getHeaderCellStyle());
                ws.addCell(title1);
                ws.setColumnView(0, 10);
                Label title2 = new Label(1, 1, "测站名称", this.getHeaderCellStyle());
                ws.addCell(title2);
                ws.setColumnView(1, 15);
                Label title3 = new Label(2, 1, "测站编码", this.getHeaderCellStyle());
                ws.addCell(title3);
                ws.setColumnView(2, 15);
                Label title4 = new Label(3, 1, "水位(m)", this.getHeaderCellStyle());
                ws.addCell(title4);
                ws.setColumnView(3, 15);
                Label title5 = new Label(4, 1, "雨量(mm)", this.getHeaderCellStyle());
                ws.addCell(title5);
                ws.setColumnView(4, 15);
                Label title6 = new Label(5, 1, "流速(m/s)", this.getHeaderCellStyle());
                ws.addCell(title6);
                ws.setColumnView(5, 15);
                Label title7 = new Label(6, 1, "风向", this.getHeaderCellStyle());
                ws.addCell(title7);
                ws.setColumnView(6, 15);
                Label title8 = new Label(7, 1, "风速(m/s)", this.getHeaderCellStyle());
                ws.addCell(title8);
                ws.setColumnView(7, 15);
                Label title9 = new Label(8, 1, "温度(℃)", this.getHeaderCellStyle());
                ws.addCell(title9);
                ws.setColumnView(8, 15);
                Label title10 = new Label(9, 1, "湿度(%RH)", this.getHeaderCellStyle());
                ws.addCell(title10);
                ws.setColumnView(9, 15);
                Label title11 = new Label(10, 1, "采集时间", this.getHeaderCellStyle());
                ws.addCell(title11);
                ws.setColumnView(10, 25);
            } catch (RowsExceededException var42) {
                var42.printStackTrace();
            } catch (WriteException var43) {
                var43.printStackTrace();
            }
        }

        try {
            wwb.write();
            wwb.close();
        } catch (IOException var40) {
            var40.printStackTrace();
        } catch (WriteException var41) {
            var41.printStackTrace();
        }

        return str + ".xls";
    }

    public WritableCellFormat getHeaderCellStyle() {
        WritableFont font = new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE);
        WritableCellFormat headerFormat = new WritableCellFormat(NumberFormats.TEXT);

        try {
            headerFormat.setFont(font);
            headerFormat.setAlignment(Alignment.CENTRE);
        } catch (WriteException var4) {
            System.out.println("表头单元格样式设置失败！");
        }

        return headerFormat;
    }
}
