//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.baseManager.controller;

import com.fourfaith.sysManage.model.SysUser;
import com.fourfaith.sysManage.service.SysMenuService;
import com.fourfaith.utils.CommonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/sysManage"})
public class SysManagerController {
    protected static final String indexJsp = "/page/sysmanage/index";
    @Autowired
    private SysMenuService sysMenuService;

    public SysManagerController() {
    }

    @RequestMapping({"/index"})
    public ModelAndView index(HttpServletRequest request, String menuId, String menuCode, String menu) {
        ModelAndView mav = new ModelAndView("/page/sysmanage/index");
        SysUser login_user = (SysUser)CommonUtil.getLoginUserInfo(request);
        new ArrayList();
        List sysMenuList;
        if (login_user.getIssupermanager()) {
            List<String> menulist = new ArrayList();
            menulist.add("menuManage");
            menulist.add("enterpriseManage");
            Map<String, Object> params = new HashMap();
            params.put("menucode", menulist);
            params.put("enabledstate", 1);
            sysMenuList = this.sysMenuService.getMenuCodeList(params);
        } else {
            sysMenuList = this.sysMenuService.getListByRoleidAndMenuid(menuId, login_user.getSysrole().getId());
        }

        mav.addObject("sysMenuList", sysMenuList);
        mav.addObject("menu", menu);
        return mav;
    }
}
