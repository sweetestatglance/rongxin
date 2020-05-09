//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.baseManager.controller;

import com.fourfaith.sysManage.model.SysNotice;
import com.fourfaith.sysManage.service.SysNoticeService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/notice"})
public class NoticeController {
    protected static final String indexJsp = "/page/notice/index";
    @Autowired
    private SysNoticeService sysNoticeService;

    public NoticeController() {
    }

    @RequestMapping(
            value = {"/index"},
            method = {RequestMethod.GET}
    )
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/page/notice/index");
        Map<String, Object> params = new HashMap();
        params.put("type", 0);
        List<SysNotice> withoutList = this.sysNoticeService.getList(params);
        params.clear();
        params.put("type", 1);
        List<SysNotice> WaterQualityList = this.sysNoticeService.getList(params);
        mav.addObject("withoutList", withoutList);
        mav.addObject("WaterQualityList", WaterQualityList);
        return mav;
    }
}
