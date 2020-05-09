//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.controller;

import com.fourfaith.sysManage.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/sysUserRole"})
public class SysUserRoleController {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    public SysUserRoleController() {
    }
}
