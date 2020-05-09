//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

public class SysUser implements Serializable {
    private static final long serialVersionUID = 2308406223025037873L;
    private String id;
    private String enterpriseid;
    private String organizationid;
    private String usercode;
    private String username;
    private String userpwd;
    private String userpwdsalt;
    private String usertel;
    private String useremail;
    private Integer enabledstate;
    private Date loginlasttime;
    private Date createtime;
    private Date updatetime;
    private String userremark;
    private String enterprisename;
    private String byorganizationname;
    private SysRole sysrole;
    private SysEnterprise sysenterprise;
    private boolean issupermanager;
    private Date loginTime;
    private String loginIp;
    private String loginArea;

    public SysUser() {
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

    public String getOrganizationid() {
        return this.organizationid;
    }

    public void setOrganizationid(String organizationid) {
        this.organizationid = organizationid;
    }

    public String getUsercode() {
        return this.usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return this.userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUserpwdsalt() {
        return this.userpwdsalt;
    }

    public void setUserpwdsalt(String userpwdsalt) {
        this.userpwdsalt = userpwdsalt;
    }

    public String getUsertel() {
        return this.usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    public String getUseremail() {
        return this.useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public Integer getEnabledstate() {
        return this.enabledstate;
    }

    public void setEnabledstate(Integer enabledstate) {
        this.enabledstate = enabledstate;
    }

    public Date getLoginlasttime() {
        return this.loginlasttime;
    }

    public void setLoginlasttime(Date loginlasttime) {
        this.loginlasttime = loginlasttime;
    }

    public Date getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getUserremark() {
        return this.userremark;
    }

    public void setUserremark(String userremark) {
        this.userremark = userremark;
    }

    public String getEnterprisename() {
        return this.enterprisename;
    }

    public void setEnterprisename(String enterprisename) {
        this.enterprisename = enterprisename;
    }

    public String getByorganizationname() {
        return this.byorganizationname;
    }

    public void setByorganizationname(String byorganizationname) {
        this.byorganizationname = byorganizationname;
    }

    public SysRole getSysrole() {
        return this.sysrole;
    }

    public void setSysrole(SysRole sysrole) {
        this.sysrole = sysrole;
    }

    public boolean getIssupermanager() {
        return this.issupermanager;
    }

    public void setIssupermanager(boolean issupermanager) {
        this.issupermanager = issupermanager;
    }

    public Date getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return this.loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginArea() {
        return this.loginArea;
    }

    public void setLoginArea(String loginArea) {
        this.loginArea = loginArea;
    }

    public boolean getEnterManager() {
        boolean flag = false;
        if (this.getSysrole() != null) {
            flag = this.getSysrole().getRolecode().equals(this.getSysenterprise().getEnterprisecode() + "_admin");
        }

        return flag;
    }

    public SysEnterprise getSysenterprise() {
        return this.sysenterprise;
    }

    public void setSysenterprise(SysEnterprise sysenterprise) {
        this.sysenterprise = sysenterprise;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "id='" + id + '\'' +
                ", enterpriseid='" + enterpriseid + '\'' +
                ", organizationid='" + organizationid + '\'' +
                ", usercode='" + usercode + '\'' +
                ", username='" + username + '\'' +
                ", userpwd='" + userpwd + '\'' +
                ", userpwdsalt='" + userpwdsalt + '\'' +
                ", usertel='" + usertel + '\'' +
                ", useremail='" + useremail + '\'' +
                ", enabledstate=" + enabledstate +
                ", loginlasttime=" + loginlasttime +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", userremark='" + userremark + '\'' +
                ", enterprisename='" + enterprisename + '\'' +
                ", byorganizationname='" + byorganizationname + '\'' +
                ", sysrole=" + sysrole +
                ", sysenterprise=" + sysenterprise +
                ", issupermanager=" + issupermanager +
                ", loginTime=" + loginTime +
                ", loginIp='" + loginIp + '\'' +
                ", loginArea='" + loginArea + '\'' +
                '}';
    }
}
