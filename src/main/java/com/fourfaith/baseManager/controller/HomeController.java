
package com.fourfaith.baseManager.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.model.StAlarmInfo;
import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.service.StAddvcdDService;
import com.fourfaith.siteManage.service.StAlarmInfoService;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.sysManage.model.SysAnnounce;
import com.fourfaith.sysManage.model.SysRoleArea;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysAnnounceService;
import com.fourfaith.sysManage.service.SysNoticeService;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.EChart;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

@Controller
@RequestMapping({"/home"})
public class HomeController {
    protected static final String indexJsp = "/page/home/index";
    @Autowired
    private SysNoticeService sysNoticeService;
    @Autowired
    private StStbprpBService stStbprpBService;
    @Autowired
    private SysAnnounceService sysAnnounceService;
    @Autowired
    private StAddvcdDService stAddvcdDService;
    @Autowired
    private SysRoleAreaService sysRoleAreaService;
    @Autowired
    private StAlarmInfoService stAlarmInfoService;

    public HomeController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/home/index");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        mav.addObject("login_user", login_user);
        Date date = new Date();
        SimpleDateFormat sp = new SimpleDateFormat("yyyy年MM月dd日  EEEE HH:mm:ss", Locale.CHINA);
        String currTime = sp.format(date);
        mav.addObject("currTime", currTime);
        mav.addObject("currTimeLong", date.getTime());
        List<String> addvcdList = new ArrayList();
        String roleId = login_user.getSysrole().getId();
        List addvcdD;
        Iterator var12;
        if (login_user.getEnterManager()) {
            new ArrayList();
            HashMap<String, Object> params = new HashMap();
            params.put("enterpriseid", login_user.getEnterpriseid());
            addvcdD = this.stAddvcdDService.getList(params);
            if (addvcdD != null && addvcdD.size() > 0) {
                var12 = addvcdD.iterator();

                while(var12.hasNext()) {
                    StAddvcdD ad = (StAddvcdD)var12.next();
                    addvcdList.add(ad.getId());
                }
            }
        } else {
            addvcdD = this.sysRoleAreaService.getListByRoleId(roleId);
            List<String> idList = new ArrayList();
            if (addvcdD != null && addvcdD.size() > 0) {
                var12 = addvcdD.iterator();

                while(var12.hasNext()) {
                    SysRoleArea roleArea = (SysRoleArea)var12.next();
                    idList.add(roleArea.getAddvcdid());
                }
            } else {
                idList.add("");
            }

            addvcdList = idList;
        }

        Map<String, Object> param = new HashMap();
        if (addvcdList != null && addvcdList.size() > 0) {
            param.put("addvcdDId", addvcdList);
        } else {
            param.put("addvcd", addvcdList);
        }

        int stcdCount = this.stStbprpBService.getCount(param);
        param.put("dsfl", 1);
        int online = this.stStbprpBService.getCount(param);
        int count = 0;
        Map<String, Object> noticeParam = new HashMap();
        noticeParam.put("userid", login_user.getId());
        noticeParam.put("isread", 0);
        List<SysAnnounce> noticeList = this.sysAnnounceService.getListByRead(noticeParam);
        if (noticeList != null && noticeList.size() > 0) {
            count = noticeList.size();
        }

        double onlineRate = 0.0D;
        DecimalFormat df = new DecimalFormat("###.00");
        if (stcdCount != 0) {
            onlineRate = Double.parseDouble(df.format((double)online / (double)stcdCount * 100.0D));
        }

        String time = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        Map<String, Object> alarmParams = new HashMap();
        alarmParams.put("addvcdList", addvcdList);
        alarmParams.put("beginTime", time + " 00:00:00");
        alarmParams.put("endTime", time + " 23:59:59");
        int todayAlarmCount = this.stAlarmInfoService.getTodayCount(alarmParams);
        mav.addObject("onlineNum", online);
        mav.addObject("announceNum", count);
        mav.addObject("onlineRate", onlineRate);
        mav.addObject("stcdCount", stcdCount);
        mav.addObject("todayAlarmCount", todayAlarmCount);
        return mav;
    }

    @RequestMapping(
            value = {"/getDsflData"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public String getDsflData(HttpServletRequest request) {
        EChart echart = new EChart();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        List<String> addvcdList = new ArrayList();
        String roleId = login_user.getSysrole().getId();
        List addvcdD;
        Iterator var9;
        if (login_user.getEnterManager()) {
            new ArrayList();
            HashMap<String, Object> params = new HashMap();
            params.put("enterpriseid", login_user.getEnterpriseid());
            addvcdD = this.stAddvcdDService.getList(params);
            if (addvcdD != null && addvcdD.size() > 0) {
                var9 = addvcdD.iterator();

                while(var9.hasNext()) {
                    StAddvcdD ad = (StAddvcdD)var9.next();
                    addvcdList.add(ad.getId());
                }
            }
        } else {
            addvcdD = this.sysRoleAreaService.getListByRoleId(roleId);
            List<String> idList = new ArrayList();
            if (addvcdD != null && addvcdD.size() > 0) {
                var9 = addvcdD.iterator();

                while(var9.hasNext()) {
                    SysRoleArea roleArea = (SysRoleArea)var9.next();
                    idList.add(roleArea.getAddvcdid());
                }
            } else {
                idList.add("");
            }

            addvcdList = idList;
        }

        Map<String, Object> param = new HashMap();
        param.put("addvcdDId", addvcdList);
        int online = 0;
        int offline = 0;
        int dormant = 0;
        int upgraded = 0;
        List<StStbprpB> list = this.stStbprpBService.getList(param);
        if (list != null && list.size() > 0) {
            Iterator var13 = list.iterator();

            while(var13.hasNext()) {
                StStbprpB st = (StStbprpB)var13.next();
                if (st.getDsfl() == 0) {
                    ++offline;
                } else if (st.getDsfl() == 1) {
                    ++online;
                } else if (st.getDsfl() == 2) {
                    ++dormant;
                } else if (st.getDsfl() == 3) {
                    ++upgraded;
                }
            }
        }

        RequestContext requestContext = new RequestContext(request);
        Map<String, Object> data = new HashMap();
        data.put(requestContext.getMessage("equipmentOnline"), online);
        data.put(requestContext.getMessage("deviceOffice"), offline);
        data.put(requestContext.getMessage("deviceUpgrade"), upgraded);
        echart.setDataMap(data);
        return JSONObject.toJSONString(echart);
    }

    @RequestMapping(
            value = {"/getAlarmData"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public String getAlarmData(HttpServletRequest request) {
        EChart echart = new EChart();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tm = new SimpleDateFormat("MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(5, -6);
        Date monday = c.getTime();
        String preMonday = sdf.format(monday);
        String nowDay = sdf.format(new Date());
        List<String> addvcdList = new ArrayList();
        String roleId = login_user.getSysrole().getId();
        List addvcdD;
        Iterator var15;
        if (login_user.getEnterManager()) {
            new ArrayList();
            HashMap<String, Object> params = new HashMap();
            params.put("enterpriseid", login_user.getEnterpriseid());
            addvcdD = this.stAddvcdDService.getList(params);
            if (addvcdD != null && addvcdD.size() > 0) {
                var15 = addvcdD.iterator();

                while(var15.hasNext()) {
                    StAddvcdD ad = (StAddvcdD)var15.next();
                    addvcdList.add(ad.getId());
                }
            }
        } else {
            addvcdD = this.sysRoleAreaService.getListByRoleId(roleId);
            List<String> idList = new ArrayList();
            if (addvcdD != null && addvcdD.size() > 0) {
                var15 = addvcdD.iterator();

                while(var15.hasNext()) {
                    SysRoleArea roleArea = (SysRoleArea)var15.next();
                    idList.add(roleArea.getAddvcdid());
                }
            } else {
                idList.add("");
            }

            addvcdList = idList;
        }

        Map<String, Object> alarmParams = new HashMap();
        alarmParams.put("addvcdList", addvcdList);
        alarmParams.put("beginTime", preMonday + " 00:00:00");
        alarmParams.put("endTime", nowDay + " 23:59:59");
        int maxNum = 0;
        Map<String, Object> data = new LinkedHashMap();
        List<StAlarmInfo> alarmList = this.stAlarmInfoService.getSevenALarmList(alarmParams);
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -7);
        System.out.println(sdf.format(calendar.getTime()));

        for(int i = 0; i < 7; ++i) {
            calendar.add(5, 1);
            String time = sdf.format(calendar.getTime());
            if (alarmList != null && alarmList.size() > 0) {
                for(int j = 0; j < alarmList.size(); ++j) {
                    if (((StAlarmInfo)alarmList.get(j)).getTime().equals(time)) {
                        data.put(tm.format(calendar.getTime()), ((StAlarmInfo)alarmList.get(j)).getNum());
                        break;
                    }

                    data.put(tm.format(calendar.getTime()), 0);
                    if (((StAlarmInfo)alarmList.get(j)).getNum() > maxNum) {
                        maxNum = ((StAlarmInfo)alarmList.get(j)).getNum();
                    }
                }
            } else {
                data.put(tm.format(calendar.getTime()), 0);
            }
        }

        echart.setDataMap(data);
        echart.setGridLeft(maxNum);
        return JSONObject.toJSONString(echart);
    }

    public static void main(String[] args) {
        int online = 14;
        int stcdCount = 28;
        double onlineRate = 0.0D;
        DecimalFormat df = new DecimalFormat("###.00");
        if (stcdCount != 0) {
            onlineRate = Double.parseDouble(df.format((double)online / (double)stcdCount * 100.0D));
        }

        System.out.println(onlineRate);
    }
}
