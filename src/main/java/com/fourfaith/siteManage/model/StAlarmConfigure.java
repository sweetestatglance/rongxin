//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.model;

import java.util.Date;

public class StAlarmConfigure {
    private String id;
    private String stcd;
    private String personId;
    private Double waterranges;
    private Double rainranges;
    private String content;
    private Integer isopen;
    private Date tm;
    private String stnm;
    private Integer sttp;

    public StAlarmConfigure() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getStcd() {
        return this.stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd == null ? null : stcd.trim();
    }

    public Double getWaterranges() {
        return this.waterranges;
    }

    public void setWaterranges(Double waterranges) {
        this.waterranges = waterranges;
    }

    public Double getRainranges() {
        return this.rainranges;
    }

    public void setRainranges(Double rainranges) {
        this.rainranges = rainranges;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getIsopen() {
        return this.isopen;
    }

    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }

    public Date getTm() {
        return this.tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public String getStnm() {
        return this.stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public Integer getSttp() {
        return this.sttp;
    }

    public void setSttp(Integer sttp) {
        this.sttp = sttp;
    }

    public String getPersonId() {
        return this.personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
