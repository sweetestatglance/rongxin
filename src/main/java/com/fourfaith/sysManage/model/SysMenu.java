

package com.fourfaith.sysManage.model;

import java.io.Serializable;
import java.util.Date;

public class SysMenu implements Serializable {
    private static final long serialVersionUID = 5115554480833173036L;
    private String id;
    private String pid;
    private String menucode;
    private String menuname;
    private Integer menulevel;
    private String menuurl;
    private String menuicon;
    private Integer menuorder;
    private Integer enabledstate;
    private Date menucreatetime;
    private Date menuupdatetime;
    private String menuremark;

    public SysMenu() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return this.pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getMenuname() {
        return this.menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public Integer getMenulevel() {
        return this.menulevel;
    }

    public void setMenulevel(Integer menulevel) {
        this.menulevel = menulevel;
    }

    public String getMenuurl() {
        return this.menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl;
    }

    public String getMenuicon() {
        return this.menuicon;
    }

    public void setMenuicon(String menuicon) {
        this.menuicon = menuicon;
    }

    public Integer getMenuorder() {
        return this.menuorder;
    }

    public void setMenuorder(Integer menuorder) {
        this.menuorder = menuorder;
    }

    public Integer getEnabledstate() {
        return this.enabledstate;
    }

    public void setEnabledstate(Integer enabledstate) {
        this.enabledstate = enabledstate;
    }

    public Date getMenucreatetime() {
        return this.menucreatetime;
    }

    public void setMenucreatetime(Date menucreatetime) {
        this.menucreatetime = menucreatetime;
    }

    public Date getMenuupdatetime() {
        return this.menuupdatetime;
    }

    public void setMenuupdatetime(Date menuupdatetime) {
        this.menuupdatetime = menuupdatetime;
    }

    public String getMenuremark() {
        return this.menuremark;
    }

    public void setMenuremark(String menuremark) {
        this.menuremark = menuremark;
    }

    public String getMenucode() {
        return this.menucode;
    }

    public void setMenucode(String menucode) {
        this.menucode = menucode;
    }
}
