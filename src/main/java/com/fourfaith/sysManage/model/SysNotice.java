//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.model;

import java.util.Date;

public class SysNotice {
    private String id;
    private String title;
    private String href;
    private String time;
    private Integer type;
    private Date tm;
    private Date ordertm;

    public SysNotice() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getTm() {
        return this.tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public Date getOrdertm() {
        return this.ordertm;
    }

    public void setOrdertm(Date ordertm) {
        this.ordertm = ordertm;
    }
}
