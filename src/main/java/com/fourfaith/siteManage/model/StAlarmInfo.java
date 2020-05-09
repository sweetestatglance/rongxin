//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.model;

import java.io.Serializable;
import java.util.Date;

public class StAlarmInfo implements Serializable {
    private static final long serialVersionUID = 3889519632241248282L;
    private String id;
    private String stcd;
    private String addvcd;
    private String type;
    private String factorvalue;
    private Boolean hassolved;
    private String solveperson;
    private Date solvetime;
    private Date tm;
    private String stnm;
    private Integer sttp;
    private String time;
    private Integer num;

    public StAlarmInfo() {
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFactorvalue() {
        return this.factorvalue;
    }

    public void setFactorvalue(String factorvalue) {
        this.factorvalue = factorvalue == null ? null : factorvalue.trim();
    }

    public Date getTm() {
        return this.tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public String getAddvcd() {
        return this.addvcd;
    }

    public void setAddvcd(String addvcd) {
        this.addvcd = addvcd;
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

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Boolean getHassolved() {
        return this.hassolved;
    }

    public void setHassolved(Boolean hassolved) {
        this.hassolved = hassolved;
    }

    public String getSolveperson() {
        return this.solveperson;
    }

    public void setSolveperson(String solveperson) {
        this.solveperson = solveperson;
    }

    public Date getSolvetime() {
        return this.solvetime;
    }

    public void setSolvetime(Date solvetime) {
        this.solvetime = solvetime;
    }
}
