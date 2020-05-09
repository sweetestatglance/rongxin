//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserFactor;
import com.fourfaith.sysManage.service.SysUserFactorService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.FactorName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/sysUserFactor"})
public class SysUserFactorController {
    protected static final String columnDefJsp = "/page/factorname/columnDef";
    @Autowired
    private SysUserFactorService sysUserFactorService;
    @Autowired
    private StFactorNameService stFactorNameService;

    public SysUserFactorController() {
    }

    @RequestMapping({"/optionPage"})
    public ModelAndView optionPage(HttpServletRequest request, String groupId) {
        ModelAndView mav = new ModelAndView("/page/factorname/columnDef");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        Map<String, Object> sfparams = new HashMap();
        sfparams.put("userid", login_user.getId());
        sfparams.put("enterpriseid", login_user.getEnterpriseid());
        SysUserFactor pjFactor = new SysUserFactor();
        pjFactor.setFactor("pJ");
        List<SysUserFactor> sysUserFactorList = this.sysUserFactorService.getList(sfparams);
        sysUserFactorList.add(pjFactor);
        sysUserFactorList = SysUserFactor.setDefault(login_user.getId(), sysUserFactorList);
        Map<String, FactorName> viewColumn = new LinkedHashMap();
        List<String> filterFactor = new ArrayList(Arrays.asList("", ""));
        Map<String, FactorName> noViewColumn = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), filterFactor, request);
        Iterator var12 = sysUserFactorList.iterator();

        while(var12.hasNext()) {
            SysUserFactor sysUserFactor = (SysUserFactor)var12.next();
            if (noViewColumn.containsKey(sysUserFactor.getFactor())) {
                viewColumn.put(sysUserFactor.getFactor(), (FactorName)noViewColumn.get(sysUserFactor.getFactor()));
                noViewColumn.remove(sysUserFactor.getFactor());
            }
        }

        mav.addObject("noViewColumn", noViewColumn);
        mav.addObject("viewColumn", viewColumn);
        return mav;
    }

    @RequestMapping({"/columnViewSave"})
    @ResponseBody
    public String columnViewSave(HttpServletRequest request, String leftAry, String rightAry) {
        AjaxJson ajax = new AjaxJson();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        List<String> filterFactor = new ArrayList(Arrays.asList("winddirection", "windspeed"));
        Map<String, FactorName> tmpMap = this.stFactorNameService.getFactorMap(login_user.getEnterpriseid(), filterFactor, request);
        Map<String, String> map = new LinkedHashMap();
        if (StringUtils.isNotBlank(rightAry)) {
            String[] factorArray = rightAry.split(",");
            this.sysUserFactorService.deleteByUserId(login_user.getId());

            for(int i = 0; i < factorArray.length; ++i) {
                SysUserFactor model = new SysUserFactor();
                model.setId(CommonUtil.getRandomUUID());
                model.setFactor(factorArray[i]);
                model.setEnterpriseid(login_user.getEnterpriseid());
                model.setOrdernum(i + 1);
                model.setUserid(login_user.getId());
                model.setTm(new Date());
                this.sysUserFactorService.insert(model);
                map.put(factorArray[i], ((FactorName)tmpMap.get(factorArray[i])).getName());
            }
        }

        Map<String, Object> attributes = new HashMap();
        attributes.put("factorMap", map);
        attributes.put("factorUnitMap", FactorName.getFactorUnitMap());
        ajax.setAttributes(attributes);
        return JSONObject.toJSONString(ajax);
    }
}
