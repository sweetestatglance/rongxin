

package com.fourfaith.sysManage.model;

import java.io.Serializable;
import java.util.Date;

public class SysEnterprise implements Serializable {
    private static final long serialVersionUID = 5890663592373648804L;
    private String id;
    private String enterprisecode;
    private String enterprisename;
    private Integer enterprisetype;
    private Date enterpriseexpiredtime;
    private Integer enterprisedevicenum;
    private Integer enterprisesmsnum;
    private Integer enabledstate;
    private String enterpriselinkman;
    private String enterprisetel;
    private String enterprisefax;
    private Date createtime;
    private Date updatetime;
    private String enterpriseremark;

    public SysEnterprise() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnterprisecode() {
        return this.enterprisecode;
    }

    public void setEnterprisecode(String enterprisecode) {
        this.enterprisecode = enterprisecode;
    }

    public String getEnterprisename() {
        return this.enterprisename;
    }

    public void setEnterprisename(String enterprisename) {
        this.enterprisename = enterprisename;
    }

    public Integer getEnterprisetype() {
        return this.enterprisetype;
    }

    public void setEnterprisetype(Integer enterprisetype) {
        this.enterprisetype = enterprisetype;
    }

    public Date getEnterpriseexpiredtime() {
        return this.enterpriseexpiredtime;
    }

    public void setEnterpriseexpiredtime(Date enterpriseexpiredtime) {
        this.enterpriseexpiredtime = enterpriseexpiredtime;
    }

    public Integer getEnterprisedevicenum() {
        return this.enterprisedevicenum;
    }

    public void setEnterprisedevicenum(Integer enterprisedevicenum) {
        this.enterprisedevicenum = enterprisedevicenum;
    }

    public Integer getEnterprisesmsnum() {
        return this.enterprisesmsnum;
    }

    public void setEnterprisesmsnum(Integer enterprisesmsnum) {
        this.enterprisesmsnum = enterprisesmsnum;
    }

    public Integer getEnabledstate() {
        return this.enabledstate;
    }

    public void setEnabledstate(Integer enabledstate) {
        this.enabledstate = enabledstate;
    }

    public String getEnterpriselinkman() {
        return this.enterpriselinkman;
    }

    public void setEnterpriselinkman(String enterpriselinkman) {
        this.enterpriselinkman = enterpriselinkman;
    }

    public String getEnterprisetel() {
        return this.enterprisetel;
    }

    public void setEnterprisetel(String enterprisetel) {
        this.enterprisetel = enterprisetel;
    }

    public String getEnterprisefax() {
        return this.enterprisefax;
    }

    public void setEnterprisefax(String enterprisefax) {
        this.enterprisefax = enterprisefax;
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

    public String getEnterpriseremark() {
        return this.enterpriseremark;
    }

    public void setEnterpriseremark(String enterpriseremark) {
        this.enterpriseremark = enterpriseremark;
    }
}
