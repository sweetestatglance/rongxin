//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.reportManage.model.StFactorName;
import com.fourfaith.reportManage.service.StFactorNameService;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.utils.AjaxJson;
import com.fourfaith.utils.CommonUtil;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/stFactorName"})
public class StFactorNameController {
    public String logContent = "";
    protected static final String indexJsp = "/page/factorname/index";
    protected static final String listJsp = "/page/factorname/list";
    @Autowired
    private StFactorNameService stFactorNameService;

    public StFactorNameController() {
    }

    @RequestMapping({"index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/factorname/index");
        return mav;
    }

    @RequestMapping({"list"})
    public ModelAndView list(HttpServletRequest request) {
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        ModelAndView mav = new ModelAndView("/page/factorname/list");
        new StFactorName();
        StFactorName factorName = this.stFactorNameService.getByEnterId(login_user.getEnterpriseid());
        mav.addObject("factorName", factorName);
        return mav;
    }

    @RequestMapping({"logSave"})
    @ResponseBody
    public String logSave(HttpServletRequest request, StFactorName stFactorName) {
        this.logContent = "";
        AjaxJson ajaxJson = new AjaxJson();
        stFactorName.setId(CommonUtil.getRandomUUID());
        stFactorName.setTm(new Date());
        int result = this.stFactorNameService.insert(stFactorName);
        if (result > 0) {
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("添加成功");
            this.logContent = "配置通道名称成功-新增";
        } else {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("添加失败");
            this.logContent = "配置通道名称失败-新增";
        }

        return JSONObject.toJSONString(ajaxJson);
    }

    @RequestMapping({"logUpdate"})
    @ResponseBody
    public String logUpdate(HttpServletRequest request, StFactorName stFactorName) {
        this.logContent = "";
        AjaxJson ajaxJson = new AjaxJson();
        stFactorName.setTm(new Date());
        int result = this.stFactorNameService.updateByPrimaryKey(stFactorName);
        if (result > 0) {
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("编辑成功");
            this.logContent = "配置通道名称成功-编辑";
        } else {
            ajaxJson.setSuccess(false);
            ajaxJson.setMsg("编辑失败");
            this.logContent = "配置通道名称失败-编辑";
        }

        return JSONObject.toJSONString(ajaxJson);
    }
}
