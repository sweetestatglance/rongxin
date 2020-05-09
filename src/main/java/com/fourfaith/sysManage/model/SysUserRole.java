

package com.fourfaith.sysManage.model;

import java.io.Serializable;

public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -5834455686155111168L;
    private String id;
    private String userid;
    private String roleid;

    public SysUserRole() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleid() {
        return this.roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }
}
