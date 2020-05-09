//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.model;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable {
    private static final long serialVersionUID = -6572167629347512063L;
    private String id;
    private String enterpriseid;
    private String userid;
    private String logcontent;
    private Date logintime;
    private String loginip;
    private String loginarea;
    private Date logtime;
    private String username;

    public SysLog() {
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

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLogcontent() {
        return this.logcontent;
    }

    public void setLogcontent(String logcontent) {
        this.logcontent = logcontent;
    }

    public Date getLogintime() {
        return this.logintime;
    }

    public void setLogintime(Date logintime) {
        this.logintime = logintime;
    }

    public String getLoginip() {
        return this.loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public String getLoginarea() {
        return this.loginarea;
    }

    public void setLoginarea(String loginarea) {
        this.loginarea = loginarea;
    }

    public Date getLogtime() {
        return this.logtime;
    }

    public void setLogtime(Date logtime) {
        this.logtime = logtime;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
