//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.model.StAlarmInfo;
import com.fourfaith.siteManage.service.StAddvcdDService;
import com.fourfaith.siteManage.service.StAlarmInfoService;
import com.fourfaith.sysManage.model.SysMenu;
import com.fourfaith.sysManage.model.SysRoleArea;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.FactorName;
import com.fourfaith.utils.JxlCellStyle;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jxl.Workbook;
import jxl.write.Label;
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
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/stAlarmInfo"})
public class StAlarmInfoController {
    protected static final String indexJsp = "/page/alarm/index";
    protected static final String listIndexJsp = "/page/alarm/list/index";
    protected static final String listJsp = "/page/alarm/list/list";
    protected static final String settingJsp = "/page/alarm/list/setting";
    @Autowired
    private StAddvcdDService stAddvcdDService;
    @Autowired
    private StAlarmInfoService stAlarmInfoService;
    @Autowired
    private SysRoleAreaService sysRoleAreaService;
    @Autowired
    private StFactorNameService stFactorNameService;
    @Autowired
    private SysMenuService sysMenuService;

    public StAlarmInfoController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request, String menuId, String menuCode, String menu) {
        ModelAndView mav = new ModelAndView("/page/alarm/index");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        new ArrayList();
        List<SysMenu> sysMenuList = this.sysMenuService.getListByRoleidAndMenuid(menuId, login_user.getSysrole().getId());
        mav.addObject("sysMenuList", sysMenuList);
        mav.addObject("menu", menu);
        return mav;
    }

    @RequestMapping(
            value = {"/listIndex"},
            method = {RequestMethod.GET}
    )
    public ModelAndView listIndex(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/alarm/list/index");
        mav.addObject("defaultTime", new Date());
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        List<String> filterFactor = new ArrayList(Arrays.asList("voltage", "signalinten", "td11"));
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), filterFactor, request);
        mav.addObject("factorNameMap", factorNameMap);
        return mav;
    }

    @RequestMapping(
            value = {"/setting"},
            method = {RequestMethod.GET}
    )
    public ModelAndView setting(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/alarm/list/setting");
        return mav;
    }

    @RequestMapping({"list"})
    public ModelAndView list(HttpServletRequest request, boolean isDevice) {
        ModelAndView mav = new ModelAndView("/page/alarm/list/list");
        String type = request.getParameter("type") == "" ? null : request.getParameter("type");
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
        } catch (UnsupportedEncodingException var20) {
            var20.printStackTrace();
        }

        params.put("stcd_query", stcd_query);
        params.put("type", type);
        String searchType = request.getParameter("searchType");
        String queryTime = request.getParameter("queryTime");
        Date beginTime = null;
        Date endTime = null;
        String pageNo;
        if (StringUtils.isNotBlank(searchType)) {
            Date date;
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
                beginTime = DateUtils.setMinTimeForYearDate(date);
                endTime = DateUtils.setMaxTimeForYearDate(date);
            }
        } else {
            pageNo = request.getParameter("query_beginTime");
            String query_endTime = request.getParameter("query_endTime");
            Date date;
            if (!StringUtils.isEmpty(pageNo)) {
                date = DateUtils.StringToDate(pageNo, "yyyy-MM-dd");
                beginTime = DateUtils.setMinTimeForDate(date);
            }

            if (!StringUtils.isEmpty(query_endTime)) {
                date = DateUtils.StringToDate(query_endTime, "yyyy-MM-dd");
                endTime = DateUtils.setMaxTimeForDate(date);
            }
        }

        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        pageNo = request.getParameter("pageNo");
        Integer count = this.stAlarmInfoService.getCount(params);
        PagingBean pagingBean = PageUtil.page(pageNo, "" + PagingBean.DEFAULT_PAGE_SIZE, count, PagingBean.DEFAULT_PAGE_SIZE);
        params.put("start", pagingBean.getStart());
        params.put("limit", pagingBean.getLimit());
        List<StAlarmInfo> alarmList = this.stAlarmInfoService.getAlarmList(params);
        mav.addObject("alarmList", alarmList);
        mav.addObject("pagingBean", pagingBean);
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        List<String> filterFactor = new ArrayList(Arrays.asList("voltage", "signalinten", "td11"));
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getAllFactorMap(login_user.getEnterpriseid(), filterFactor);
        mav.addObject("factorNameMap", factorNameMap);
        return mav;
    }

    @RequestMapping(
            value = {"/alarmExport"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String alarmExport(HttpServletRequest request, HttpServletResponse response, boolean isDevice) {
        String nodeIds = request.getParameter("nodeIds");
        String stcd_query = request.getParameter("stcd_query");
        String searchType = request.getParameter("searchType");
        String queryTime = request.getParameter("queryTime");
        String query_endTime = request.getParameter("query_endTime");
        String query_beginTime = request.getParameter("query_beginTime");
        Date beginTime = null;
        Date endTime = null;
        Date date;
        if (StringUtils.isBlank(query_beginTime)) {
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
                beginTime = DateUtils.setMinTimeForYearDate(date);
                endTime = DateUtils.setMaxTimeForYearDate(date);
            }
        } else {
            if (!StringUtils.isEmpty(query_beginTime)) {
                date = DateUtils.StringToDate(query_beginTime, "yyyy-MM-dd");
                beginTime = DateUtils.setMinTimeForDate(date);
            }

            if (!StringUtils.isEmpty(query_endTime)) {
                date = DateUtils.StringToDate(query_endTime, "yyyy-MM-dd");
                endTime = DateUtils.setMaxTimeForDate(date);
            }
        }

        String type = request.getParameter("type");
        String pathName = this.alarmWrite(request, nodeIds, stcd_query, beginTime, endTime, type, isDevice);
        return pathName;
    }

    @RequestMapping(
            value = {"/settled"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String settled(HttpServletRequest request) {
        String ids = request.getParameter("ids");
        AjaxJson ajaxJson = this.stAlarmInfoService.editSettled(ids, request);
        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping(
            value = {"/warnRecall"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public String warnRecall(HttpServletRequest request) {
        AjaxJson ajaxJson = new AjaxJson();
        RequestContext requestContext = new RequestContext(request);
        ajaxJson.setSuccess(false);
        ajaxJson.setMsg(requestContext.getMessage("operateFailed"));
        String ids = request.getParameter("ids");
        Map<String, Object> param = new HashMap();
        param.put("hasSolved", 0);
        param.put("id", ids);
        int result = this.stAlarmInfoService.updateHasSolved(param);
        if (result > 0) {
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg(requestContext.getMessage("operateSuccess"));
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    public String alarmWrite(HttpServletRequest request, String nodeIds, String stcd_query, Date beginTime, Date endTime, String type, boolean isDevice) {
        Map<String, Object> params = new HashMap();
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        RequestContext requestContext = new RequestContext(request);
        List<String> stcdList = null;
        if (StringUtils.isNotBlank(nodeIds)) {
            stcdList = new ArrayList(Arrays.asList(nodeIds.split(",")));
        } else {
            stcdList = new ArrayList(Arrays.asList(""));
        }

        if (isDevice) {
            params.put("stcdList", stcdList);
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
        } catch (UnsupportedEncodingException var46) {
            var46.printStackTrace();
        }

        if (StringUtils.isEmpty(type)) {
            type = null;
        }

        params.put("stcd_query", stcd_query);
        params.put("type", type);
        String basePath = PropertiesUtils.getPara("downFileDir");
        String savePath = request.getSession().getServletContext().getRealPath("/") + basePath + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        String sdate = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int)(random.nextDouble() * 90.0D) + 10;
        String str = sdate + rannum;
        str = str + PropertiesUtils.getPara("exportAlarmInfo");
        String fileName = savePath + str + ".xls";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        WritableWorkbook wwb = null;

        try {
            wwb = Workbook.createWorkbook(new File(fileName));
        } catch (IOException var45) {
            var45.printStackTrace();
        }

        if (wwb != null) {
            WritableSheet ws = wwb.createSheet(requestContext.getMessage("alarmRecord"), 0);

            Label labelC0;
            try {
                ws.mergeCells(0, 0, 11, 0);
                String titleView = requestContext.getMessage("alarmRecord");
                if (beginTime != null) {
                    String bTime = sdf.format(beginTime);
                    String eTime = sdf.format(endTime);
                    titleView = titleView + "(" + bTime + requestContext.getMessage("toZ") + eTime + ")";
                }

                Label headerTitle = new Label(0, 0, titleView, JxlCellStyle.getHeaderCellStyle());
                ws.addCell(headerTitle);
                ws.setColumnView(0, 10);
                Label title1 = new Label(0, 1, requestContext.getMessage("serialNumber"), JxlCellStyle.getHeaderCellStyle());
                ws.addCell(title1);
                ws.setColumnView(0, 10);
                Label title2 = new Label(1, 1, requestContext.getMessage("alarmTime"), JxlCellStyle.getHeaderCellStyle());
                ws.addCell(title2);
                ws.setColumnView(1, 25);
                Label title3 = new Label(2, 1, requestContext.getMessage("stationCode"), JxlCellStyle.getHeaderCellStyle());
                ws.addCell(title3);
                ws.setColumnView(2, 25);
                Label title4 = new Label(3, 1, requestContext.getMessage("stationName"), JxlCellStyle.getHeaderCellStyle());
                ws.addCell(title4);
                ws.setColumnView(3, 25);
                Label title5 = new Label(4, 1, requestContext.getMessage("siteType"), JxlCellStyle.getHeaderCellStyle());
                ws.addCell(title5);
                ws.setColumnView(4, 15);
                labelC0 = new Label(5, 1, requestContext.getMessage("alarmType"), JxlCellStyle.getHeaderCellStyle());
                ws.addCell(labelC0);
                ws.setColumnView(5, 25);
            } catch (RowsExceededException var43) {
                var43.printStackTrace();
            } catch (WriteException var44) {
                var44.printStackTrace();
            }

            List<StAlarmInfo> stAlarmList = this.stAlarmInfoService.getAlarmList(params);
            SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
            List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
            Map<String, FactorName> factorNameMap = this.stFactorNameService.getAllFactorMap(login_user.getEnterpriseid(), filterFactor);
            if (stAlarmList != null && stAlarmList.size() > 0) {
                for(int i = 0; i < stAlarmList.size(); ++i) {
                    for(int j = i; j <= i; ++j) {
                        int count = i + 1;
                        labelC0 = new Label(0, j + 2, String.valueOf(count));
                        Label labelC1;
                        if (((StAlarmInfo)stAlarmList.get(i)).getTm() != null) {
                            String data = sdf.format(((StAlarmInfo)stAlarmList.get(i)).getTm());
                            labelC1 = new Label(1, j + 2, data);
                        } else {
                            labelC1 = new Label(1, j + 2, "--");
                        }

                        Label labelC2 = new Label(2, j + 2, ((StAlarmInfo)stAlarmList.get(i)).getStcd());
                        Label labelC3 = new Label(3, j + 2, ((StAlarmInfo)stAlarmList.get(i)).getStnm());
                        Label labelC4 = null;
                        if (((StAlarmInfo)stAlarmList.get(i)).getSttp() == null) {
                            labelC4 = new Label(4, j + 2, "--");
                        } else if (((StAlarmInfo)stAlarmList.get(i)).getSttp() == 0) {
                            labelC4 = new Label(4, j + 2, requestContext.getMessage("sttp0"));
                        } else if (((StAlarmInfo)stAlarmList.get(i)).getSttp() == 1) {
                            labelC4 = new Label(4, j + 2, requestContext.getMessage("sttp1"));
                        } else if (((StAlarmInfo)stAlarmList.get(i)).getSttp() == 2) {
                            labelC4 = new Label(4, j + 2, requestContext.getMessage("sttp2"));
                        } else if (((StAlarmInfo)stAlarmList.get(i)).getSttp() == 3) {
                            labelC4 = new Label(4, j + 2, requestContext.getMessage("sttp3"));
                        } else if (((StAlarmInfo)stAlarmList.get(i)).getSttp() == 4) {
                            labelC4 = new Label(4, j + 2, requestContext.getMessage("sttp4"));
                        }

                        Label labelC5 = null;
                        Iterator var38 = factorNameMap.entrySet().iterator();

                        while(var38.hasNext()) {
                            Entry<String, FactorName> entry = (Entry)var38.next();
                            if (((StAlarmInfo)stAlarmList.get(i)).getType().equals(((String)entry.getKey()).toUpperCase())) {
                                labelC5 = new Label(5, j + 2, ((FactorName)entry.getValue()).getName() + "报警");
                            }
                        }

                        try {
                            ws.addCell(labelC0);
                            ws.addCell(labelC1);
                            ws.addCell(labelC2);
                            ws.addCell(labelC3);
                            ws.addCell(labelC4);
                            ws.addCell(labelC5);
                        } catch (RowsExceededException var41) {
                            var41.printStackTrace();
                        } catch (WriteException var42) {
                            var42.printStackTrace();
                        }
                    }
                }
            }
        }

        try {
            wwb.write();
            wwb.close();
        } catch (IOException var39) {
            var39.printStackTrace();
        } catch (WriteException var40) {
            var40.printStackTrace();
        }

        return str + ".xls";
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

    @RequestMapping({"/getTodayAlarmCount"})
    @ResponseBody
    public String getTodayAlarmCount(HttpServletRequest request) throws Exception {
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        AjaxJson ajaxJson = new AjaxJson();
        int count = 0;
        Map<String, Object> params = new HashMap();
        String bTime = DateUtils.getTimeByHour(1);
        Date beginTime = DateUtils.StringToDate(bTime, "yyyy-MM-dd HH:mm:ss");
        Date endTime = new Date();
        List<String> addvcdList = new ArrayList();
        if (login_user.getEnterManager()) {
            HashMap<String, Object> stparams = new HashMap();
            stparams.put("enterpriseid", login_user.getEnterpriseid());
            List<StAddvcdD> addvcdDList = this.stAddvcdDService.getList(stparams);
            if (addvcdDList != null) {
                Iterator var13 = addvcdDList.iterator();

                while(var13.hasNext()) {
                    StAddvcdD stAddvcdD = (StAddvcdD)var13.next();
                    addvcdList.add(stAddvcdD.getId());
                }
            }
        } else {
            List<SysRoleArea> arList = this.sysRoleAreaService.getListByRoleId(login_user.getSysrole().getId());
            if (arList != null) {
                Iterator var17 = arList.iterator();

                while(var17.hasNext()) {
                    SysRoleArea model = (SysRoleArea)var17.next();
                    addvcdList.add(model.getAddvcdid());
                }
            }
        }

        if (addvcdList.size() == 0) {
            addvcdList.add("");
        }

        if (addvcdList.size() == 1) {
            params.put("addvcd", addvcdList.get(0));
        } else {
            params.put("addvcdList", addvcdList);
        }

        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        count = this.stAlarmInfoService.getCount(params);
        ajaxJson.setObj(count);
        return JSONObject.toJSONString(ajaxJson);
    }
}
