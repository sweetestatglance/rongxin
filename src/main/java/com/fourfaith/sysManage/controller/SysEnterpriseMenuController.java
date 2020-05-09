//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.controller;

import com.fourfaith.sysManage.service.SysEnterpriseMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/sysEnterpriseMenu"})
public class SysEnterpriseMenuController {
    @Autowired
    private SysEnterpriseMenuService sysEnterpriseMenuService;

    public SysEnterpriseMenuController() {
    }
}
