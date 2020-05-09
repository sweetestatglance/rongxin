//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.reportManage.model.StDeviceFactor;
import com.fourfaith.reportManage.service.StAllDetailsFactorService;
import com.fourfaith.reportManage.service.StDeviceFactorService;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.siteManage.model.StAddvcdD;
import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.service.StAddvcdDService;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.FactorName;
import java.lang.reflect.Method;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/stDeviceFactor"})
public class StDeviceFactorController {
    @Autowired
    private StStbprpBService stStbprpBService;
    @Autowired
    private StDeviceFactorService stDeviceFactorService;
    @Autowired
    private StAllDetailsFactorService stAllDetailsFactorService;
    @Autowired
    private StAddvcdDService stAddvcdDService;
    @Autowired
    private StFactorNameService stFactorNameService;

    public StDeviceFactorController() {
    }

    @RequestMapping(
            value = {"/getDetail"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public String getDetail(HttpServletRequest request, String stcd) {
        AjaxJson result = new AjaxJson();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        String query_beginTime = request.getParameter("query_beginTime");
        String query_beginTime_hour = request.getParameter("query_beginTime_hour");
        String query_endTime = request.getParameter("query_endTime");
        String query_endTime_hour = request.getParameter("query_endTime_hour");
        Map<String, Object> params = new HashMap();
        Date date;
        if (!StringUtils.isEmpty(query_beginTime) && !StringUtils.isEmpty(query_beginTime_hour)) {
            date = DateUtils.StringToDate(query_beginTime, "yyyy-MM-dd");
            date = DateUtils.set(date, 11, Integer.parseInt(query_beginTime_hour));
            params.put("beginTime", date);
        }

        if (!StringUtils.isEmpty(query_endTime) && !StringUtils.isEmpty(query_endTime_hour)) {
            date = DateUtils.StringToDate(query_endTime, "yyyy-MM-dd");
            date = DateUtils.set(date, 11, Integer.parseInt(query_endTime_hour));
            date = DateUtils.set(date, 12, 59);
            params.put("endTime", date);
        }

        try {
            params.put("stcd", stcd);
            StDeviceFactor stDeviceFactor;
            if (!StringUtils.isEmpty(query_beginTime) && !StringUtils.isEmpty(query_beginTime_hour) && !StringUtils.isEmpty(query_endTime) && !StringUtils.isEmpty(query_endTime_hour)) {
                StStbprpB stStbprpB = this.stStbprpBService.findById(stcd);
                stDeviceFactor = this.stAllDetailsFactorService.getReportStatic(params);
                if (stDeviceFactor == null) {
                    stDeviceFactor = new StDeviceFactor();
                }

                stDeviceFactor.setStcd(stStbprpB.getStcd());
                stDeviceFactor.setStnm(stStbprpB.getStnm());
                stDeviceFactor.setDsfl(stStbprpB.getDsfl());
                stDeviceFactor.setSttp(stStbprpB.getSttp());
                stDeviceFactor.setIscamera(stStbprpB.getIscamera());
                stDeviceFactor.setLastonline(stStbprpB.getLastonline());
                stDeviceFactor.setLgtd(stStbprpB.getLgtd());
                stDeviceFactor.setLttd(stStbprpB.getLttd());
                stDeviceFactor.setStlc(stStbprpB.getStlc());
                result.setObj(stDeviceFactor);
            } else {
                List<StDeviceFactor> list = this.stDeviceFactorService.getList(params);
                stDeviceFactor = (StDeviceFactor)list.get(0);
                StAddvcdD stAddvcdD = this.stAddvcdDService.findById(stDeviceFactor.getFaddvcd());
                if (stAddvcdD != null && !"".equals(stAddvcdD)) {
                    if ("0".equals(stAddvcdD.getFaddvcd())) {
                        stDeviceFactor.setAddvnm2(stDeviceFactor.getAddvnm1());
                    } else {
                        stDeviceFactor.setAddvnm2(stAddvcdD.getAddvnm());
                    }
                }

                List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
                Map<String, FactorName> factorNameMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), filterFactor, request);
                Map<String, Object> map = new HashMap();
                if (factorNameMap != null && !"".equals(factorNameMap)) {
                    map = this.getFactorMap((StDeviceFactor)list.get(0), factorNameMap);
                }

                result.setObj(list.get(0));
                result.setAttributes((Map)map);
            }
        } catch (Exception var16) {
            result.setSuccess(false);
            var16.printStackTrace();
        }

        return JSONObject.toJSONString(result);
    }

    private Map<String, Object> getFactorMap(StDeviceFactor stDeviceFactor, Map<String, FactorName> factorNameMap) throws Exception {
        Map<String, Object> map = new HashMap();
        Iterator var5 = factorNameMap.keySet().iterator();

        while(var5.hasNext()) {
            String key = (String)var5.next();
            System.out.println(((FactorName)factorNameMap.get(key)).getName());
            if (this.getValue(stDeviceFactor, key) != null) {
                map.put(((FactorName)factorNameMap.get(key)).getName(), this.getValue(stDeviceFactor, key));
            }
        }

        return map;
    }

    public Object getValue(Object dto, String name) throws Exception {
        Method[] m = dto.getClass().getMethods();

        for(int i = 0; i < m.length; ++i) {
            if (("get" + name).toLowerCase().equals(m[i].getName().toLowerCase())) {
                return m[i].invoke(dto);
            }
        }

        return null;
    }
}
