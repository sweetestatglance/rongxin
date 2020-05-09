//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.model;

import java.io.Serializable;
import java.util.Date;

public class SysUserAnnounce implements Serializable {
    private String id;
    private String noticeid;
    private String userid;
    private Integer isread;
    private Date moditime;

    public SysUserAnnounce() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoticeid() {
        return this.noticeid;
    }

    public void setNoticeid(String noticeid) {
        this.noticeid = noticeid;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getIsread() {
        return this.isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public Date getModitime() {
        return this.moditime;
    }

    public void setModitime(Date moditime) {
        this.moditime = moditime;
    }
}
