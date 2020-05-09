

package com.fourfaith.sysManage.model;

import java.io.Serializable;
import java.util.Date;

public class SysRole implements Serializable {
    private static final long serialVersionUID = -2626148320395973817L;
    private String id;
    private String enterpriseid;
    private String rolecode;
    private String rolename;
    private Integer enabledstate;
    private Date createtime;
    private Date updatetime;
    private String roleremark;

    public SysRole() {
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

    public String getRolecode() {
        return this.rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public String getRolename() {
        return this.rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
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

    public String getRoleremark() {
        return this.roleremark;
    }

    public void setRoleremark(String roleremark) {
        this.roleremark = roleremark;
    }
}
