//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysUserFactor {
    private String id;
    private String enterpriseid;
    private String userid;
    private String factor;
    private Integer ordernum;
    private Date tm;

    public SysUserFactor() {
    }

    public SysUserFactor(String userid, String factor) {
        this.userid = userid;
        this.factor = factor;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getFactor() {
        return this.factor;
    }

    public void setFactor(String factor) {
        this.factor = factor == null ? null : factor.trim();
    }

    public Integer getOrdernum() {
        return this.ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public static List<SysUserFactor> setDefault(String userid, List<SysUserFactor> sysUserFactorList) {
        if (sysUserFactorList == null || ((List)sysUserFactorList).size() == 0) {
            sysUserFactorList = new ArrayList();
            ((List)sysUserFactorList).add(new SysUserFactor(userid, "voltage"));
            ((List)sysUserFactorList).add(new SysUserFactor(userid, "signalinten"));
        }

        return (List)sysUserFactorList;
    }

    public Date getTm() {
        return this.tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public String getEnterpriseid() {
        return this.enterpriseid;
    }

    public void setEnterpriseid(String enterpriseid) {
        this.enterpriseid = enterpriseid;
    }
}
