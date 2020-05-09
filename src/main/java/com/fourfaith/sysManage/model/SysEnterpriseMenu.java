//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.model;

import java.io.Serializable;

public class SysEnterpriseMenu implements Serializable {
    private static final long serialVersionUID = -7109497592935612161L;
    private String id;
    private String enterpriseid;
    private String menuid;

    public SysEnterpriseMenu() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterpriseid() {
        return this.enterpriseid;
    }

    public void setEnterpriseid(String enterpriseid) {
        this.enterpriseid = enterpriseid;
    }

    public String getMenuid() {
        return this.menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }
}
