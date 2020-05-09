//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.model;

import java.io.Serializable;
import java.util.Date;

public class SysAnnounce implements Serializable {
    private static final long serialVersionUID = 5509975904402883365L;
    private String id;
    private String title;
    private String content;
    private String admin;
    private Date moditime;
    private Integer isread;

    public SysAnnounce() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAdmin() {
        return this.admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public Date getModitime() {
        return this.moditime;
    }

    public void setModitime(Date moditime) {
        this.moditime = moditime;
    }

    public Integer getIsread() {
        return this.isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }
}
