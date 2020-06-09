package com.fourfaith.rongxin.controller;

import com.alibaba.fastjson.JSONObject;
import com.fourfaith.baseManager.controller.HomeController;
import com.fourfaith.baseManager.controller.MonitorController;
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
import com.fourfaith.sysManage.model.SysRoleArea;
import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.model.SysUserFactor;
import com.fourfaith.sysManage.service.SysAnnounceService;
import com.fourfaith.sysManage.service.SysRoleAreaService;
import com.fourfaith.sysManage.service.SysUserFactorService;
import com.fourfaith.utils.CommonUtil;
import com.fourfaith.utils.DateUtils;
import com.fourfaith.utils.FactorName;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping({"/alexHQLine"})
public class AlexHQLineController {

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request) {

        return null;
    }


}
