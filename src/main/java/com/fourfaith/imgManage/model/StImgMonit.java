//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.imgManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StImgMonit implements Serializable {
    private static final long serialVersionUID = 8304932182114217623L;
    private String id;
    private String stcd;
    private String addvcd;
    private String imgurl;
    private Date tm;
    private Integer iscamera;
    private String stnm;
    private Integer sttp;
    private BigDecimal water;
    private BigDecimal rain;
    private BigDecimal flow;
    private BigDecimal voltage;
    private BigDecimal ph;
    private BigDecimal turbidimeter;
    private BigDecimal oxygen;
    private BigDecimal surfactants;
    private BigDecimal temperature;

    public StImgMonit() {
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

    public String getAddvcd() {
        return this.addvcd;
    }

    public void setAddvcd(String addvcd) {
        this.addvcd = addvcd == null ? null : addvcd.trim();
    }

    public String getImgurl() {
        return this.imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Date getTm() {
        return this.tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public Integer getIscamera() {
        return this.iscamera;
    }

    public void setIscamera(Integer iscamera) {
        this.iscamera = iscamera;
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

    public BigDecimal getWater() {
        return this.water;
    }

    public void setWater(BigDecimal water) {
        this.water = water;
    }

    public BigDecimal getRain() {
        return this.rain;
    }

    public void setRain(BigDecimal rain) {
        this.rain = rain;
    }

    public BigDecimal getFlow() {
        return this.flow;
    }

    public void setFlow(BigDecimal flow) {
        this.flow = flow;
    }

    public BigDecimal getVoltage() {
        return this.voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }

    public BigDecimal getPh() {
        return this.ph;
    }

    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }

    public BigDecimal getTurbidimeter() {
        return this.turbidimeter;
    }

    public void setTurbidimeter(BigDecimal turbidimeter) {
        this.turbidimeter = turbidimeter;
    }

    public BigDecimal getOxygen() {
        return this.oxygen;
    }

    public void setOxygen(BigDecimal oxygen) {
        this.oxygen = oxygen;
    }

    public BigDecimal getSurfactants() {
        return this.surfactants;
    }

    public void setSurfactants(BigDecimal surfactants) {
        this.surfactants = surfactants;
    }

    public BigDecimal getTemperature() {
        return this.temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }
}
