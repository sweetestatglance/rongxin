//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.controller;

import com.fourfaith.sysManage.service.SysRoleAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/sysRoleArea"})
public class SysRoleAreaController {
    @Autowired
    private SysRoleAreaService sysRoleAreaService;

    public SysRoleAreaController() {
    }
}
