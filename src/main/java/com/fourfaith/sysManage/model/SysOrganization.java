//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.sysManage.model;

import java.io.Serializable;
import java.util.Date;

public class SysOrganization implements Serializable {
    private static final long serialVersionUID = 1784798444945012013L;
    private String id;
    private String pid;
    private String enterpriseid;
    private String organcode;
    private String organname;
    private Integer enabledstate;
    private Date createtime;
    private Date updatetime;
    private String organremark;

    public SysOrganization() {
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

    public String getEnterpriseid() {
        return this.enterpriseid;
    }

    public void setEnterpriseid(String enterpriseid) {
        this.enterpriseid = enterpriseid;
    }

    public String getOrgancode() {
        return this.organcode;
    }

    public void setOrgancode(String organcode) {
        this.organcode = organcode;
    }

    public String getOrganname() {
        return this.organname;
    }

    public void setOrganname(String organname) {
        this.organname = organname;
    }

    public Integer getEnabledstate() {
        return this.enabledstate;
    }

    public void setEnabledstate(Integer enabledstate) {
        this.enabledstate = enabledstate;
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

    public String getOrganremark() {
        return this.organremark;
    }

    public void setOrganremark(String organremark) {
        this.organremark = organremark;
    }
}
