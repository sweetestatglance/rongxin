//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.baseManager.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.reportManage.controller.StAllDetailsFactorController;
import com.fourfaith.reportManage.model.StDeviceFactor;
import com.fourfaith.reportManage.service.StAllDetailsFactorService;
import com.fourfaith.reportManage.service.StDeviceFactorService;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.model.StAlarmInfo;
import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.service.StAddvcdDService;
import com.fourfaith.siteManage.service.StAlarmInfoService;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.sysManage.model.SysAnnounce;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserFactor;
import com.fourfaith.sysManage.service.SysAnnounceService;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import com.fourfaith.sysManage.service.SysUserFactorService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.FactorName;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/monitor"})
public class MonitorController {
    protected static final String indexJsp = "/page/monitor/index";
    protected static final String mapJsp = "/page/monitor/map";
    protected static final String statistListJsp = "/page/monitor/statistList";
    @Autowired
    private StStbprpBService stStbprpBService;
    @Autowired
    private SysRoleAreaService sysRoleAreaService;
    @Autowired
    private StAddvcdDService stAddvcdDService;
    @Autowired
    private StDeviceFactorService stDeviceFactorService;
    @Autowired
    private StAllDetailsFactorService stAllDetailsFactorService;
    @Autowired
    private StAlarmInfoService stAlarmInfoService;
    @Autowired
    private SysAnnounceService sysAnnounceService;
    @Autowired
    private SysUserFactorService sysUserFactorService;
    @Autowired
    private StFactorNameService stFactorNameService;

    public MonitorController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/monitor/index");
        return mav;
    }

    @RequestMapping(
            value = {"/mapInfo"},
            method = {RequestMethod.POST}
    )
    public ModelAndView mapInfo(HttpServletRequest request, boolean isDevice) throws UnsupportedEncodingException {
        ModelAndView mav = new ModelAndView("/page/monitor/map");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        String selectNum = request.getParameter("selectNum");
        if (selectNum == null) {
            selectNum = "10";
        }

        String nodeIds = request.getParameter("nodeIds");
        String rainRange = request.getParameter("rainRange");
        String query_beginTime = request.getParameter("query_beginTime");
        String query_beginTime_hour = request.getParameter("query_beginTime_hour");
        String query_endTime = request.getParameter("query_endTime");
        String query_endTime_hour = request.getParameter("query_endTime_hour");
        String showOnLineDevice = request.getParameter("showOnLineDevice");
        String query_stcdName = request.getParameter("query_stcdName");
        if (StringUtils.isNotBlank(query_stcdName)) {
            query_stcdName = URLDecoder.decode(query_stcdName, "UTF-8");
        }

        String sttp = request.getParameter("sttp");
        Double maxPj = null;
        Map<String, Object> stParams = new HashMap();
        stParams.put("query_stcdName", query_stcdName);
        List<String> aIds = null;
        if (StringUtils.isNotBlank(sttp)) {
            stParams.put("sttp", sttp);
        }

        if (StringUtils.isNotBlank(nodeIds)) {
            aIds = new ArrayList(Arrays.asList(nodeIds.split(",")));
        } else {
            aIds = new ArrayList(Arrays.asList(""));
        }

        if (isDevice) {
            stParams.put("stcdList", aIds);
        } else {
            stParams.put("addvcdList", aIds);
        }

        Date beginTime = null;
        Date endTime = null;
        Date date;
        if (!StringUtils.isEmpty(query_beginTime) && !StringUtils.isEmpty(query_beginTime_hour)) {
            date = DateUtils.StringToDate(query_beginTime, "yyyy-MM-dd");
            date = DateUtils.set(date, 11, Integer.parseInt(query_beginTime_hour));
            beginTime = date;
            stParams.put("beginTime", date);
        }

        if (!StringUtils.isEmpty(query_endTime) && !StringUtils.isEmpty(query_endTime_hour)) {
            date = DateUtils.StringToDate(query_endTime, "yyyy-MM-dd");
            date = DateUtils.set(date, 11, Integer.parseInt(query_endTime_hour));
            date = DateUtils.set(date, 12, 59);
            date = DateUtils.set(date, 13, 59);
            endTime = date;
            stParams.put("endTime", date);
        }

        List<String> rainRangeList = new ArrayList();
        if (!StringUtils.isEmpty(rainRange)) {
            String[] rainRangeArray = rainRange.split(",");
            rainRangeList = Arrays.asList(rainRangeArray);
        }

        List<StDeviceFactor> devList = new ArrayList();
        HashMap lrmParam;
        if (!StringUtils.isEmpty(query_beginTime) && !StringUtils.isEmpty(query_beginTime_hour) && !StringUtils.isEmpty(query_endTime) && !StringUtils.isEmpty(query_endTime_hour)) {
            stParams.put("tableName", StAllDetailsFactorController.getTableName(beginTime));
            maxPj = this.stAllDetailsFactorService.getMaxPj(stParams);
            List<StStbprpB> sbList = this.stStbprpBService.getList(stParams);
            if (sbList != null && sbList.size() > 0) {
                lrmParam = new HashMap();
                lrmParam.put("beginTime", beginTime);
                lrmParam.put("endTime", endTime);
                lrmParam.put("tableName", StAllDetailsFactorController.getTableName(beginTime));
                lrmParam.put("groupByCon", "STCD");
                List<StDeviceFactor> sdfList = this.stAllDetailsFactorService.getReportStaticList(lrmParam);
                Iterator var26 = sbList.iterator();

                label160:
                while(true) {
                    while(true) {
                        if (!var26.hasNext()) {
                            break label160;
                        }

                        StStbprpB stStbprpB = (StStbprpB)var26.next();
                        Iterator iterator = sdfList.iterator();

                        while(iterator.hasNext()) {
                            StDeviceFactor stDeviceFactor = (StDeviceFactor)iterator.next();
                            if (stDeviceFactor.getStcd().equals(stStbprpB.getStcd())) {
                                stDeviceFactor.setStcd(stStbprpB.getStcd());
                                stDeviceFactor.setFaddvcd(stStbprpB.getFaddvcd());
                                stDeviceFactor.setAddvnm1(stStbprpB.getAddvnm1());
                                stDeviceFactor.setStnm(stStbprpB.getStnm());
                                stDeviceFactor.setDsfl(stStbprpB.getDsfl());
                                stDeviceFactor.setSttp(stStbprpB.getSttp());
                                stDeviceFactor.setIscamera(stStbprpB.getIscamera());
                                stDeviceFactor.setLastonline(stStbprpB.getLastonline());
                                stDeviceFactor.setLgtd(stStbprpB.getLgtd());
                                stDeviceFactor.setLttd(stStbprpB.getLttd());
                                stDeviceFactor.setStlc(stStbprpB.getStlc());
                                stDeviceFactor.setCameratype(stStbprpB.getCameratype());
                                stDeviceFactor.setCommode(stStbprpB.getCommode());
                                ((List)devList).add(stDeviceFactor);
                                iterator.remove();
                                break;
                            }

                            if (!iterator.hasNext()) {
                                StDeviceFactor sdf = new StDeviceFactor();
                                sdf.setStcd(stStbprpB.getStcd());
                                sdf.setFaddvcd(stStbprpB.getFaddvcd());
                                sdf.setAddvnm1(stStbprpB.getAddvnm1());
                                sdf.setStnm(stStbprpB.getStnm());
                                sdf.setDsfl(stStbprpB.getDsfl());
                                sdf.setSttp(stStbprpB.getSttp());
                                sdf.setIscamera(stStbprpB.getIscamera());
                                sdf.setLastonline(stStbprpB.getLastonline());
                                sdf.setLgtd(stStbprpB.getLgtd());
                                sdf.setLttd(stStbprpB.getLttd());
                                sdf.setStlc(stStbprpB.getStlc());
                                sdf.setCommode(stStbprpB.getCommode());
                                sdf.setCameratype(stStbprpB.getCameratype());
                                ((List)devList).add(sdf);
                            }
                        }
                    }
                }
            }

            mav.addObject("isSum", true);
        } else {
            devList = this.stDeviceFactorService.getList(stParams);
            maxPj = this.stDeviceFactorService.getMaxPj(stParams);
            mav.addObject("isSum", false);
        }

        List<String> alarmStcdList = new ArrayList(Arrays.asList(""));
        StDeviceFactor var45;
        if (rainRangeList != null && ((List)rainRangeList).size() > 0) {
            for(Iterator iterator = ((List)devList).iterator(); iterator.hasNext(); var45 = (StDeviceFactor)iterator.next()) {
            }
        } else if (devList != null && ((List)devList).size() > 0) {
            Iterator var44 = ((List)devList).iterator();

            StDeviceFactor stDeviceFactor;
            while(var44.hasNext()) {
                stDeviceFactor = (StDeviceFactor)var44.next();
                StAddvcdD stAddvcdD = this.stAddvcdDService.findById(stDeviceFactor.getFaddvcd());
                if (stAddvcdD != null && !"".equals(stAddvcdD)) {
                    if ("0".equals(stAddvcdD.getFaddvcd())) {
                        stDeviceFactor.setAddvnm2(stDeviceFactor.getAddvnm1());
                    } else {
                        stDeviceFactor.setAddvnm2(stAddvcdD.getAddvnm());
                    }
                }
            }

            alarmStcdList.clear();
            var44 = ((List)devList).iterator();

            while(var44.hasNext()) {
                stDeviceFactor = (StDeviceFactor)var44.next();
                alarmStcdList.add(stDeviceFactor.getStcd());
            }
        }

        lrmParam = new HashMap();
        String bTime = DateUtils.getTimeByHour(1);
        Date lrmBeginTime = DateUtils.StringToDate(bTime, "yyyy-MM-dd HH:mm:ss");
        Date lrmEndTime = new Date();
        lrmParam.put("stcdList", alarmStcdList);
        lrmParam.put("beginTime", lrmBeginTime);
        lrmParam.put("endTime", lrmEndTime);
        List<StAlarmInfo> alarmList = this.stAlarmInfoService.getAlarmList(lrmParam);
        String alarmStcd = "";
        if (alarmList != null && alarmList.size() > 0) {
            StAlarmInfo stAlarmInfo;
            for(Iterator var30 = alarmList.iterator(); var30.hasNext(); alarmStcd = alarmStcd + stAlarmInfo.getStcd() + ",") {
                stAlarmInfo = (StAlarmInfo)var30.next();
            }

            alarmStcd = alarmStcd.substring(0, alarmStcd.length() - 1);
        }

        Map<String, Object> noticeParam = new HashMap();
        noticeParam.put("userid", login_user.getId());
        noticeParam.put("isread", 0);
        List<SysAnnounce> noticeList = this.sysAnnounceService.getListByRead(noticeParam);
        if (noticeList != null && noticeList.size() > 0) {
            Iterator var32 = noticeList.iterator();

            while(var32.hasNext()) {
                SysAnnounce model = (SysAnnounce)var32.next();
                if (model.getContent().length() > 40) {
                    model.setContent(model.getContent().substring(0, 40) + "...");
                }

                if (model.getContent().contains("\r\n")) {
                    model.setContent(model.getContent().replaceAll("\\r\\n", ""));
                }
            }
        }

        mav.addObject("devListJson", JSONObject.toJSONString(devList));
        mav.addObject("alarmStcd", alarmStcd);
        mav.addObject("alarmListJson", JSONObject.toJSONString(alarmList));
        mav.addObject("noticeListJson", JSONObject.toJSONString(noticeList));
        mav.addObject("query_beginTime", query_beginTime);
        mav.addObject("query_beginTime_hour", query_beginTime_hour);
        mav.addObject("query_endTime_hour", query_endTime_hour);
        mav.addObject("query_endTime", query_endTime);
        mav.addObject("query_stcdName", query_stcdName);
        mav.addObject("sttp", sttp);
        mav.addObject("rainRange", rainRange);
        mav.addObject("maxPj", maxPj);
        Map<String, Object> sfparams = new HashMap();
        sfparams.put("userid", login_user.getId());
        List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), filterFactor, request);
        List<SysUserFactor> viewSysUserFactor = new ArrayList();
        List<SysUserFactor> sysUserFactorList = this.sysUserFactorService.getList(sfparams);
        sysUserFactorList = SysUserFactor.setDefault(login_user.getId(), sysUserFactorList);
        if (sysUserFactorList != null) {
            SysUserFactor modelFactor = new SysUserFactor();
            modelFactor.setFactor("pJ");
            viewSysUserFactor.add(modelFactor);

            for(int i = 0; i < sysUserFactorList.size(); ++i) {
                SysUserFactor model = (SysUserFactor)sysUserFactorList.get(i);
                if (factorNameMap.containsKey(model.getFactor())) {
                    viewSysUserFactor.add(model);
                }
            }
        }

        List<String> viewHead = this.getViewHeadList(request, sysUserFactorList);
        mav.addObject("viewHead", viewHead);
        mav.addObject("sysUserFactorList", viewSysUserFactor);
        mav.addObject("selectNum", selectNum);
        return mav;
    }

    public List<String> getViewHeadList(HttpServletRequest request, List<SysUserFactor> sysUserFactorList) {
        List<String> viewHead = new ArrayList();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
        Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), filterFactor, request);
        Map<String, String> factorNameUnitMap = FactorName.getFactorUnitMap();
        if (sysUserFactorList != null) {
            viewHead.add("日累计降水量");

            for(int i = 0; i < sysUserFactorList.size(); ++i) {
                SysUserFactor model = (SysUserFactor)sysUserFactorList.get(i);
                if (factorNameMap.containsKey(model.getFactor())) {
                    viewHead.add(((FactorName)factorNameMap.get(model.getFactor())).getName());
                }
            }
        }

        return viewHead;
    }

    @RequestMapping(
            value = {"/staticList"},
            method = {RequestMethod.GET}
    )
    public ModelAndView staticList(HttpServletRequest request, String addvcdId) {
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        ModelAndView mav = new ModelAndView("/page/monitor/statistList");
        return mav;
    }
}
