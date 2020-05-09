//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.imgManage.model.StImgMonit;
import com.fourfaith.imgManage.service.StImgMonitService;
import com.fourfaith.reportManage.model.StDeviceFactor;
import com.fourfaith.reportManage.service.StAllDetailsFactorService;
import com.fourfaith.reportManage.service.StDeviceFactorService;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.siteManage.model.DevicePhoto;
import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.model.StStsmtaskB;
import com.fourfaith.siteManage.service.DevicePhotoService;
import com.fourfaith.siteManage.service.StAddvcdDService;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.siteManage.service.StStsmtaskBService;
import com.fourfaith.sysManage.model.SysRoleArea;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.EChart;
import com.fourfaith.utils.FactorName;
import com.fourfaith.utils.PageUtil;
import com.fourfaith.utils.PagingBean;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/reportDetail"})
public class ReportDetailController {
    protected static final String indexJsp = "/page/reportdetail/index";
    protected static final String monitorJsp = "/page/reportdetail/monitor";
    protected static final String monitorPictureJsp = "/page/reportdetail/monitorPicture";
    protected static final String pictureListJsp = "/page/reportdetail/pictureList";
    protected static final String monitorFactorJsp = "/page/reportdetail/monitorFactor";
    protected static final String stbprpbListJsp = "/page/reportdetail/stbprpbList";
    @Autowired
    private StStsmtaskBService stStsmtaskBService;
    @Autowired
    private StStbprpBService stStbprpBService;
    @Autowired
    private DevicePhotoService devicePhotoService;
    @Autowired
    private StDeviceFactorService stDeviceFactorService;
    @Autowired
    private StAllDetailsFactorService stAllDetailsFactorService;
    @Autowired
    private StImgMonitService stImgMonitService;
    @Autowired
    private StAddvcdDService stAddvcdDService;
    @Autowired
    private SysRoleAreaService sysRoleAreaService;
    @Autowired
    private StFactorNameService stFactorNameService;

    public ReportDetailController() {
    }

    @RequestMapping({"index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/reportdetail/index");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        new RequestContext(request);
        String deviceNo = request.getParameter("deviceNo");
        String initIndex = request.getParameter("initIndex");
        if (StringUtils.isBlank(initIndex)) {
            initIndex = "0";
        }

        List<String> factorlist = new ArrayList();
        List<String> factorFlaglist = new ArrayList();
        StStbprpB stbprpB = this.stStbprpBService.findById(deviceNo);
        StStsmtaskB smtask = this.stStsmtaskBService.findById(deviceNo);
        if (smtask != null) {
            List<String> filterFactor = new ArrayList(Arrays.asList("td11", ""));
            Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), filterFactor, request);
            FactorName factor;
            if (factorNameMap.containsKey("pn05") && 1 == smtask.getPn05()) {
                factor = (FactorName)factorNameMap.get("pn05");
                factorlist.add(factor.getName());
                factorFlaglist.add(factor.getFlag());
                smtask.setPn05(0);
            }

            FactorName[] var16;
            int var15 = (var16 = FactorName.values()).length;

            for(int var14 = 0; var14 < var15; ++var14) {
                factor = var16[var14];
                boolean var17 = false;

                try {
                    Field field = StStsmtaskB.class.getDeclaredField(factor.getFlag());
                    field.setAccessible(true);
                    int f = (Integer)field.get(smtask);
                    if (f == 1) {
                        factorlist.add(((FactorName)factorNameMap.get(factor.getFlag())).getName());
                        factorFlaglist.add(factor.getFlag());
                    }
                } catch (Exception var19) {
                    var19.printStackTrace();
                }
            }
        }

        mav.addObject("factorlist", factorlist);
        mav.addObject("factorFlaglist", factorFlaglist);
        mav.addObject("deviceNo", deviceNo);
        mav.addObject("stbprpB", stbprpB);
        mav.addObject("initIndex", initIndex);
        return mav;
    }

    @RequestMapping(
            value = {"/monitorPage"},
            method = {RequestMethod.POST}
    )
    public ModelAndView monitorPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/reportdetail/monitor");
        String stcd = request.getParameter("stcd");
        Map<String, Object> params = new HashMap();
        params.put("stcd", stcd);
        StImgMonit image = null;
        List<StImgMonit> imgList = this.stImgMonitService.getImgList(params);
        if (imgList != null && imgList.size() > 0) {
            image = (StImgMonit)imgList.get(0);
        }

        mav.addObject("stImgMonit", image);
        mav.addObject("stcd", stcd);
        StDeviceFactor stDeviceFactor = this.stDeviceFactorService.findByStcd(stcd);
        mav.addObject("lastest", stDeviceFactor);
        StStsmtaskB stsmtaskB = this.stStsmtaskBService.selectByPrimaryKey(stcd);
        mav.addObject("stsmtaskB", stsmtaskB);
        return mav;
    }

    @RequestMapping(
            value = {"/monitorPicturePage"},
            method = {RequestMethod.POST}
    )
    public ModelAndView monitorPicturePage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/reportdetail/monitorPicture");
        String stcd = request.getParameter("stcd");
        String channelNum = request.getParameter("channelNum") == null ? "1" : request.getParameter("channelNum");
        StStbprpB stbprpb = this.stStbprpBService.findById(stcd);
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if (beginTime == null) {
            beginTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
        }

        if (endTime == null) {
            endTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59");
        }

        String[] str = new String[4];
        if (stbprpb.getIscamera() == 1 && stbprpb.getCameratype() == 1) {
            str[0] = "1";
            str[1] = "2";
            str[2] = "3";
            str[3] = "4";
        } else if (stbprpb.getIscamera() == 1 && stbprpb.getCameratype() == 2) {
            String channel = stbprpb.getVideochannel();
            if (org.apache.commons.lang.StringUtils.isNotBlank(channel)) {
                str = channel.split(",");
            }
        }

        mav.addObject("str", str);
        mav.addObject("stbprpb", stbprpb);
        mav.addObject("channelNum", channelNum);
        mav.addObject("beginTime", beginTime);
        mav.addObject("endTime", endTime);
        mav.addObject("stcd", stcd);
        return mav;
    }

    @RequestMapping(
            value = {"/monitorPictureList"},
            method = {RequestMethod.POST}
    )
    public ModelAndView monitorPictureList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/reportdetail/pictureList");
        String stcd = request.getParameter("stcd");
        String channelNum = request.getParameter("channelNum") == null ? "1" : request.getParameter("channelNum");
        StStbprpB stbprpb = this.stStbprpBService.findById(stcd);
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if (beginTime == null) {
            beginTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
        }

        if (endTime == null) {
            endTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59");
        }

        int num = 0;
        if (stbprpb.getIscamera() == 1 && stbprpb.getCameratype() == 1) {
            num = 4;
        } else if (stbprpb.getIscamera() == 1 && stbprpb.getCameratype() == 2) {
            String channel = stbprpb.getVideochannel();
            if (org.apache.commons.lang.StringUtils.isNotBlank(channel)) {
                String[] str = channel.split(",");
                num = str.length;
            }
        }

        List<DevicePhoto> leftphotoList = null;
        List<DevicePhoto> photoList = null;
        PagingBean pagingBean = null;
        if (num != 0) {
            Map<String, Object> params = new HashMap();
            params.put("stcd", stcd);
            params.put("channelid", channelNum);
            params.put("photostatus", 2);
            params.put("beginTime", beginTime);
            params.put("endTime", endTime);
            String pageNo = request.getParameter("pageNo");
            int count = this.devicePhotoService.getCount(params);
            pagingBean = PageUtil.page(pageNo, "18", count, PagingBean.DEFAULT_PAGE_SIZE);
            params.put("start", pagingBean.getStart());
            params.put("limit", pagingBean.getLimit());
            photoList = this.devicePhotoService.getList(params);
            params.put("start", (Object)null);
            params.put("limit", (Object)null);
            leftphotoList = this.devicePhotoService.getList(params);
        }

        mav.addObject("num", num);
        mav.addObject("stbprpb", stbprpb);
        mav.addObject("channelNum", channelNum);
        mav.addObject("photoList", photoList);
        mav.addObject("leftphotoList", leftphotoList);
        mav.addObject("pagingBean", pagingBean);
        return mav;
    }

    @RequestMapping(
            value = {"/stbprpbListPage"},
            method = {RequestMethod.POST}
    )
    public ModelAndView stbprpbListPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/reportdetail/stbprpbList");
        String stcd = request.getParameter("stcd");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        List<String> addvcdList = new ArrayList();
        String roleId = login_user.getSysrole().getId();
        List addvcdD;
        Iterator var10;
        if (login_user.getEnterManager()) {
            new ArrayList();
            HashMap<String, Object> params = new HashMap();
            params.put("enterpriseid", login_user.getEnterpriseid());
            addvcdD = this.stAddvcdDService.getList(params);
            if (addvcdD != null && addvcdD.size() > 0) {
                var10 = addvcdD.iterator();

                while(var10.hasNext()) {
                    StAddvcdD ad = (StAddvcdD)var10.next();
                    addvcdList.add(ad.getId());
                }
            }
        } else {
            addvcdD = this.sysRoleAreaService.getListByRoleId(roleId);
            List<String> idList = new ArrayList();
            if (addvcdD != null && addvcdD.size() > 0) {
                var10 = addvcdD.iterator();

                while(var10.hasNext()) {
                    SysRoleArea roleArea = (SysRoleArea)var10.next();
                    idList.add(roleArea.getAddvcdid());
                }
            } else {
                idList.add("");
            }

            addvcdList = idList;
        }

        Map<String, Object> map = new HashMap();
        map.put("addvcdDId", addvcdList);
        List<StStbprpB> stbprpbList = this.stStbprpBService.getList(map);
        mav.addObject("stbprpbList", stbprpbList);
        mav.addObject("stcd", stcd);
        return mav;
    }

    @RequestMapping(
            value = {"/monitorFactorPage"},
            method = {RequestMethod.POST}
    )
    public ModelAndView monitorFactorPage(HttpServletRequest request, String stcd) {
        ModelAndView mav = new ModelAndView("/page/reportdetail/monitorFactor");
        StStbprpB stbprpb = this.stStbprpBService.findById(stcd);
        mav.addObject("stbprpb", stbprpb);
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> params = new HashMap();
        params.put("stcd", stcd);
        StStsmtaskB smtask = this.stStsmtaskBService.findById(stcd);
        StImgMonit image = null;
        List<StImgMonit> imgList = this.stImgMonitService.getImgList(params);
        if (imgList != null && imgList.size() > 0) {
            image = (StImgMonit)imgList.get(0);
        }

        mav.addObject("stImgMonit", image);
        StDeviceFactor stDeviceFactor = this.stDeviceFactorService.findByStcd(stcd);
        Date date = new Date();
        params.put("beginTime", DateUtils.setMinTimeForDate(date));
        params.put("endTime", DateUtils.setMaxTimeForDate(date));
        params.put("tableName", StAllDetailsFactorController.getTableName(date));
        StDeviceFactor today = this.stAllDetailsFactorService.getReportStatic(params);
        Date yesterday = DateUtils.add(date, 5, -1);
        params.put("tableName", StAllDetailsFactorController.getTableName(yesterday));
        params.put("beginTime", DateUtils.setMinTimeForDate(yesterday));
        params.put("endTime", DateUtils.setMaxTimeForDate(yesterday));
        StDeviceFactor sevenDay = this.stAllDetailsFactorService.getReportStatic(params);
        params.put("tableName", StAllDetailsFactorController.getTableName(date));
        params.put("beginTime", DateUtils.setMinTimeForMonthDate(date));
        params.put("endTime", DateUtils.setMaxTimeForMonthDate(date));
        StDeviceFactor thirtyDay = this.stAllDetailsFactorService.getReportStatic(params);
        List<String> factorFlaglist = this.stStsmtaskBService.getFactorFlagList(smtask, login_user.getEnterpriseid());
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), new ArrayList(), request);
        mav.addObject("factorMap", factorNameMap);
        mav.addObject("factorFlaglist", factorFlaglist);
        mav.addObject("stcd", stcd);
        mav.addObject("lastest", stDeviceFactor);
        mav.addObject("today", today);
        mav.addObject("sevenDay", sevenDay);
        mav.addObject("thirtyDay", thirtyDay);
        mav.addObject("smtask", smtask);
        return mav;
    }

    @RequestMapping(
            value = {"/getChartsData"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public String getChartsData(HttpServletRequest request, String flag, String stcd, Integer type) {
        EChart echart = new EChart();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        RequestContext requestContext = new RequestContext(request);
        Map<String, Object> params = new HashMap();
        params.put("stcd", stcd);
        Date date = new Date();
        Date beginTime = null;
        Date endTime = DateUtils.setMaxTimeForDate(date);
        String pattern = "";
        if (type == 2) {
            params.put("groupByCon", "DATE_FORMAT(TM, '%Y-%m-%d %H')");
            beginTime = DateUtils.setMinTimeForDate(date);
            pattern = "H时";
            params.put("tableName", StAllDetailsFactorController.getTableName(date));
        } else if (type == 3) {
            params.put("groupByCon", "DATE_FORMAT(TM, '%Y-%m-%d %H')");
            pattern = "H" + requestContext.getMessage("hour");
            Date yesterday = DateUtils.add(date, 5, -1);
            beginTime = DateUtils.setMinTimeForDate(yesterday);
            endTime = DateUtils.setMaxTimeForDate(yesterday);
            params.put("tableName", StAllDetailsFactorController.getTableName(yesterday));
        } else if (type == 4) {
            params.put("groupByCon", "DAYOFMONTH (TM)");
            pattern = "d" + requestContext.getMessage("day");
            beginTime = DateUtils.setMinTimeForMonthDate(date);
            endTime = DateUtils.setMaxTimeForMonthDate(date);
            params.put("tableName", StAllDetailsFactorController.getTableName(date));
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
        List<String> categories = this.getCategories(beginTime, endTime, type, requestContext);
        params.put("tableName", StAllDetailsFactorController.getTableName(beginTime));
        Object detailList = new ArrayList();

        try {
            detailList = this.stAllDetailsFactorService.getReportStaticList(params);
        } catch (Exception var28) {
            var28.printStackTrace();
        }

        SimpleDateFormat df = new SimpleDateFormat(pattern);

        for(int j = 0; j < categories.size(); ++j) {
            String categor = (String)categories.get(j);
            StDeviceFactor model = new StDeviceFactor();
            StDeviceFactor stDeviceFactor;
            if (detailList != null && ((List)detailList).size() > 0) {
                Iterator var24 = ((List)detailList).iterator();

                while(var24.hasNext()) {
                    stDeviceFactor = (StDeviceFactor)var24.next();
                    if (stDeviceFactor.getTm() != null && df.format(stDeviceFactor.getTm()).equals(categor)) {
                        model = stDeviceFactor;
                        ((List)detailList).remove(stDeviceFactor);
                        break;
                    }
                }
            }

            stDeviceFactor = null;

            try {
                Field field = StDeviceFactor.class.getDeclaredField(flag);
                field.setAccessible(true);
                BigDecimal value = (BigDecimal)field.get(model);
                if (fData != null) {
                    fData.add(value);
                }
            } catch (Exception var27) {
                var27.printStackTrace();
            }
        }

        List<Map<String, Object>> series = new ArrayList();
        List<Map<String, Object>> yAxis = new ArrayList();
        int index = 0;
        String[] colorAry = new String[]{"#2ec7c9", "#b6a2de", "#5ab1ef", "#ffb980", "#d87a80", "#8d98b3", "#e5cf0d", "#97b552", "#95706d", "#dc69aa"};
        if (fMap != null) {
            fMap.put("data", fData);
            series.add(fMap);
            Map<String, Object> yAxi = new HashMap();
            yAxi.put("name", ((FactorName)factorNameMap.get(flag)).getName());
            yAxi.put("nameGap", "10");
            yAxi.put("position", index % 2 == 0 ? "left" : "right");
            yAxi.put("offset", index / 2 * 70);
            Map<String, Object> axisLine = new HashMap();
            Map<String, Object> lineStyle = new HashMap();
            lineStyle.put("color", colorAry[index]);
            axisLine.put("lineStyle", lineStyle);
            yAxi.put("axisLine", axisLine);
            yAxis.add(yAxi);
            ++index;
        }

        --index;
        if (index > 1) {
            int left = 20 + index / 2 * 45;
            int right = 45 + (index / 2 - 1) * 45;
            echart.setGridLeft(left);
            echart.setGridRight(right);
        }

        echart.setyAxis(yAxis);
        echart.setLegendDataList(LegendDataList);
        echart.setxAxiDataList(categories);
        echart.setSeries(series);
        return JSONObject.toJSONString(echart);
    }

    private List<String> getCategories(Date beginTime, Date endTime, Integer type, RequestContext requestContext) {
        List<String> categories = new ArrayList();
        Date tmp;
        if (type != 2 && type != 3) {
            if (type == 4) {
                for(tmp = beginTime; !tmp.after(endTime); tmp = DateUtils.add(tmp, 5, 1)) {
                    categories.add(DateUtils.DateToCalendar(tmp).get(5) + requestContext.getMessage("day"));
                }
            }
        } else {
            for(tmp = beginTime; !tmp.after(endTime); tmp = DateUtils.add(tmp, 11, 1)) {
                categories.add(DateUtils.DateToCalendar(tmp).get(11) + requestContext.getMessage("hour"));
            }
        }

        return categories;
    }
}
