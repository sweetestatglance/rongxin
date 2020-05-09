//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.model;

import java.io.Serializable;

public class SysRoleArea implements Serializable {
    private static final long serialVersionUID = 7863201819556397995L;
    private String id;
    private String roleid;
    private String addvcdid;

    public SysRoleArea() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleid() {
        return this.roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getAddvcdid() {
        return this.addvcdid;
    }

    public void setAddvcdid(String addvcdid) {
        this.addvcdid = addvcdid;
    }
}
