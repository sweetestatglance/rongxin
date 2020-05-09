//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.model;

import java.io.Serializable;
import java.util.Date;

public class StModel implements Serializable {
    private String id;
    private String enterpriseid;
    private String name;
    private String pmid;
    private String remark;
    private Date moditime;

    public StModel() {
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPmid() {
        return this.pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getModitime() {
        return this.moditime;
    }

    public void setModitime(Date moditime) {
        this.moditime = moditime;
    }
}
