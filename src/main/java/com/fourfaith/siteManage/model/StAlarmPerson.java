//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.model;

import java.util.Date;

public class StAlarmPerson {
    private String id;
    private String enterpriseid;
    private String name;
    private String phone;
    private Date tm;

    public StAlarmPerson() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEnterpriseid() {
        return this.enterpriseid;
    }

    public void setEnterpriseid(String enterpriseid) {
        this.enterpriseid = enterpriseid == null ? null : enterpriseid.trim();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getphone() {
        return this.phone;
    }

    public void setphone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getTm() {
        return this.tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }
}
