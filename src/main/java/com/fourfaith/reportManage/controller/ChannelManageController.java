//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.reportManage.model.StEnterFactor;
import com.fourfaith.reportManage.model.StFactorName;
import com.fourfaith.reportManage.service.StEnterFactorService;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysUserFactorService;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.BeanUtils;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.FactorName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/channelManage"})
public class ChannelManageController {
    public String logContent = "";
    protected static final String indexJsp = "/page/channelManage/index";
    @Autowired
    private StEnterFactorService stEnterFactorService;
    @Autowired
    private StFactorNameService stFactorNameService;
    @Autowired
    private SysUserFactorService sysUserFactorService;

    public ChannelManageController() {
    }

    @InitBinder({"stFactorName"})
    public void initBinderUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("stFactorName.");
    }

    @InitBinder({"stEnterFactor"})
    public void initBinderAddr(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("stEnterFactor.");
    }

    @RequestMapping({"index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/channelManage/index");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        StFactorName factorName = this.stFactorNameService.getByEnterId(login_user.getEnterpriseid());
        if (factorName == null) {
            factorName = new StFactorName();
        }

        StEnterFactor enterFactor = this.stEnterFactorService.getByEnterId(login_user.getEnterpriseid());
        if (enterFactor == null) {
            enterFactor = new StEnterFactor();
        }

        List<String> filterList = new ArrayList(Arrays.asList("id", "enterpriseid", "voltage", "signalinten", "tm"));
        mav.addObject("factorName", BeanUtils.ConvertObjToMap(factorName, filterList));
        mav.addObject("factorNameMap", FactorName.getFactorNameMap(request));
        mav.addObject("factorNameId", factorName.getId());
        mav.addObject("enterFactorId", enterFactor.getId());
        mav.addObject("tm", enterFactor.getTm());
        mav.addObject("enterFactor", BeanUtils.ConvertObjToMap(enterFactor, filterList));
        return mav;
    }

    @RequestMapping({"logSave"})
    @ResponseBody
    public String logSave(HttpServletRequest request, StFactorName stFactorName, StEnterFactor stEnterFactor) {
        this.logContent = "";
        AjaxJson ajaxJson = new AjaxJson();
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        stFactorName.setId(CommonUtil.getRandomUUID());
        stFactorName.setTm(new Date());
        stFactorName.setEnterpriseid(login_user.getEnterpriseid());
        stEnterFactor.setId(CommonUtil.getRandomUUID());
        stEnterFactor.setEnterpriseid(login_user.getEnterpriseid());
        stEnterFactor.setTm(new Date());
        int result = this.stFactorNameService.insert(stFactorName);
        this.stEnterFactorService.insert(stEnterFactor);
        if (result > 0) {
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("添加成功");
            this.logContent = "通道管理配置成功-新增";
        } else {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加失败");
            this.logContent = "通道管理配置成功-新增";
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"logUpdate"})
    @ResponseBody
    public String logUpdate(HttpServletRequest request, StFactorName stFactorName, StEnterFactor stEnterFactor) {
        this.logContent = "";
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        AjaxJson ajaxJson = new AjaxJson();
        stFactorName.setTm(new Date());
        stEnterFactor.setTm(new Date());
        stFactorName.setEnterpriseid(login_user.getEnterpriseid());
        stEnterFactor.setEnterpriseid(login_user.getEnterpriseid());
        int result = this.stFactorNameService.updateByPrimaryKey(stFactorName);
        this.stEnterFactorService.updateByPrimaryKey(stEnterFactor);
        List<String> filterList = new ArrayList(Arrays.asList("id", "enterpriseid", "tm"));
        Map<String, Object> map = BeanUtils.ConvertObjToMap(stEnterFactor, filterList);
        Map<String, Object> params = new HashMap();
        params.put("enterpriseid", login_user.getEnterpriseid());
        List<String> deleteFactorList = new ArrayList();
        Iterator var12 = map.entrySet().iterator();

        while(var12.hasNext()) {
            Entry<String, Object> entry = (Entry)var12.next();
            if ((Integer)entry.getValue() != 1) {
                deleteFactorList.add((String)entry.getKey());
            }
        }

        params.put("factorList", deleteFactorList);
        if (deleteFactorList.size() > 0) {
            this.sysUserFactorService.delete(params);
        }

        if (result > 0) {
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("编辑成功");
            this.logContent = "通道管理配置成功-编辑";
        } else {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("编辑失败");
            this.logContent = "通道管理配置成功-编辑";
        }

        return JSONObject.toJSONString(ajaxJson);
    }
}
