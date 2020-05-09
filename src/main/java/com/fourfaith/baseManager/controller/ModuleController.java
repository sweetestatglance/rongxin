
package com.fourfaith.baseManager.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.siteManage.model.StStbprpB;
import com.fourfaith.siteManage.service.StStbprpBService;
import com.fourfaith.sysManage.model.SysRoleArea;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.HightChart;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/module"})
public class ModuleController {
    protected static final String indexJsp = "/page/module/index";
    protected static final String reportContentJsp = "/page/module/siteReport_content";
    @Autowired
    SysRoleAreaService sysRoleAreaService;
    @Autowired
    StStbprpBService stStbprpBService;

    public ModuleController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request, String stcd, String topNodeId) {
        ModelAndView mav = new ModelAndView("/page/module/index");
        mav.addObject("stcd", stcd);
        mav.addObject("topNodeId", topNodeId);
        return mav;
    }

    @RequestMapping(
            value = {"/getDeviceByType"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public String getDeviceByType(HttpServletRequest request, String type) {
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> hm = new HashMap();
        Map<String, Object> params = new HashMap();
        params.put(type, 1);
        List<SysRoleArea> arList = this.sysRoleAreaService.getListByRoleId(login_user.getSysrole().getId());
        List<String> addvcdList = new ArrayList();
        if (arList != null) {
            Iterator var9 = arList.iterator();

            while(var9.hasNext()) {
                SysRoleArea model = (SysRoleArea)var9.next();
                addvcdList.add(model.getAddvcdid());
            }
        } else {
            addvcdList.add("");
        }

        params.put("addvcdList", addvcdList);
        List<StStbprpB> stbprpBList = this.stStbprpBService.getListByType(params);
        hm.put("stbprpBList", stbprpBList);
        return JSONObject.toJSONString(hm);
    }

    @RequestMapping(
            value = {"/reportForm"},
            method = {RequestMethod.POST}
    )
    public ModelAndView reportForm(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/module/siteReport_content");
        String deviceNoStr = request.getParameter("deviceNos");
        String deviceNameStr = request.getParameter("deviceNames");
        String reportType = request.getParameter("reportType");
        String searchType = request.getParameter("searchType");
        String[] deviceAry = deviceNoStr.split(",");
        String[] deviceNameAry = deviceNameStr.split(",");
        String beginTimeStr = request.getParameter("beginTime");
        Double warningValue = null;
        String unit = "";
        Date beginTime = null;
        Date endTime = null;
        new HightChart();
        new ArrayList();
        new LinkedHashMap();

        for(int h = 0; h < deviceAry.length; ++h) {
            String deviceNo = deviceAry[h];
            String var10000 = deviceNameAry[h];
            new HashMap();
            new ArrayList();
            new LinkedHashMap();
            Map<String, Object> detailParams = new HashMap();
            detailParams.put("beginTime", beginTime);
            detailParams.put("endTime", endTime);
            detailParams.put("stcd", deviceNo);
            detailParams.put("specialCon", "MOD(MINUTE(TM),5)=0");
            detailParams.put("orderByCon", "TM");
            new HashMap();
        }

        return mav;
    }
}
