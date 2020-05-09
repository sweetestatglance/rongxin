//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.model;

import java.math.BigDecimal;
import java.util.Date;

public class MonthAllDetails {
    private String id;
    private String stcd;
    private String stnm;
    private String factor;
    private String factorname;
    private Integer year;
    private Integer month;
    private Integer day;
    private BigDecimal newFactorValue;
    private Date newDate;
    private BigDecimal maxFactorValue;
    private Date maxDate;
    private BigDecimal minFactorValue;
    private Date minDate;
    private BigDecimal avgFactorValue;
    private Date avgDate;
    private BigDecimal sumFactorValue;
    private String sumDate;
    private Date tm;
    private BigDecimal newPh;
    private BigDecimal minPh;
    private BigDecimal maxPh;
    private BigDecimal newTurbidimeter;
    private BigDecimal minTurbidimeter;
    private BigDecimal maxTurbidimeter;
    private BigDecimal newOxygen;
    private BigDecimal minOxygen;
    private BigDecimal maxOxygen;
    private BigDecimal newSurfactants;
    private BigDecimal minSurfactants;
    private BigDecimal maxSurfactants;
    private BigDecimal newTemperature;
    private BigDecimal minTemperature;
    private BigDecimal maxTemperature;

    public MonthAllDetails() {
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

    public String getFactor() {
        return this.factor;
    }

    public void setFactor(String factor) {
        this.factor = factor == null ? null : factor.trim();
    }

    public String getFactorname() {
        return this.factorname;
    }

    public void setFactorname(String factorname) {
        this.factorname = factorname == null ? null : factorname.trim();
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return this.month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getNewFactorValue() {
        return this.newFactorValue;
    }

    public void setNewFactorValue(BigDecimal newFactorValue) {
        this.newFactorValue = newFactorValue;
    }

    public Date getNewDate() {
        return this.newDate;
    }

    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

    public BigDecimal getMaxFactorValue() {
        return this.maxFactorValue;
    }

    public void setMaxFactorValue(BigDecimal maxFactorValue) {
        this.maxFactorValue = maxFactorValue;
    }

    public Date getMaxDate() {
        return this.maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public BigDecimal getMinFactorValue() {
        return this.minFactorValue;
    }

    public void setMinFactorValue(BigDecimal minFactorValue) {
        this.minFactorValue = minFactorValue;
    }

    public Date getMinDate() {
        return this.minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public BigDecimal getAvgFactorValue() {
        return this.avgFactorValue;
    }

    public void setAvgFactorValue(BigDecimal avgFactorValue) {
        this.avgFactorValue = avgFactorValue;
    }

    public Date getAvgDate() {
        return this.avgDate;
    }

    public void setAvgDate(Date avgDate) {
        this.avgDate = avgDate;
    }

    public BigDecimal getSumFactorValue() {
        return this.sumFactorValue;
    }

    public void setSumFactorValue(BigDecimal sumFactorValue) {
        this.sumFactorValue = sumFactorValue;
    }

    public String getSumDate() {
        return this.sumDate;
    }

    public void setSumDate(String sumDate) {
        this.sumDate = sumDate;
    }

    public Date getTm() {
        return this.tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public BigDecimal getNewPh() {
        return this.newPh;
    }

    public void setNewPh(BigDecimal newPh) {
        this.newPh = newPh;
    }

    public BigDecimal getMinPh() {
        return this.minPh;
    }

    public void setMinPh(BigDecimal minPh) {
        this.minPh = minPh;
    }

    public BigDecimal getMaxPh() {
        return this.maxPh;
    }

    public void setMaxPh(BigDecimal maxPh) {
        this.maxPh = maxPh;
    }

    public BigDecimal getNewTurbidimeter() {
        return this.newTurbidimeter;
    }

    public void setNewTurbidimeter(BigDecimal newTurbidimeter) {
        this.newTurbidimeter = newTurbidimeter;
    }

    public BigDecimal getMinTurbidimeter() {
        return this.minTurbidimeter;
    }

    public void setMinTurbidimeter(BigDecimal minTurbidimeter) {
        this.minTurbidimeter = minTurbidimeter;
    }

    public BigDecimal getMaxTurbidimeter() {
        return this.maxTurbidimeter;
    }

    public void setMaxTurbidimeter(BigDecimal maxTurbidimeter) {
        this.maxTurbidimeter = maxTurbidimeter;
    }

    public BigDecimal getNewOxygen() {
        return this.newOxygen;
    }

    public void setNewOxygen(BigDecimal newOxygen) {
        this.newOxygen = newOxygen;
    }

    public BigDecimal getMinOxygen() {
        return this.minOxygen;
    }

    public void setMinOxygen(BigDecimal minOxygen) {
        this.minOxygen = minOxygen;
    }

    public BigDecimal getMaxOxygen() {
        return this.maxOxygen;
    }

    public void setMaxOxygen(BigDecimal maxOxygen) {
        this.maxOxygen = maxOxygen;
    }

    public BigDecimal getNewSurfactants() {
        return this.newSurfactants;
    }

    public void setNewSurfactants(BigDecimal newSurfactants) {
        this.newSurfactants = newSurfactants;
    }

    public BigDecimal getMinSurfactants() {
        return this.minSurfactants;
    }

    public void setMinSurfactants(BigDecimal minSurfactants) {
        this.minSurfactants = minSurfactants;
    }

    public BigDecimal getMaxSurfactants() {
        return this.maxSurfactants;
    }

    public void setMaxSurfactants(BigDecimal maxSurfactants) {
        this.maxSurfactants = maxSurfactants;
    }

    public BigDecimal getNewTemperature() {
        return this.newTemperature;
    }

    public void setNewTemperature(BigDecimal newTemperature) {
        this.newTemperature = newTemperature;
    }

    public BigDecimal getMinTemperature() {
        return this.minTemperature;
    }

    public void setMinTemperature(BigDecimal minTemperature) {
        this.minTemperature = minTemperature;
    }

    public BigDecimal getMaxTemperature() {
        return this.maxTemperature;
    }

    public void setMaxTemperature(BigDecimal maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getStnm() {
        return this.stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public Integer getDay() {
        return this.day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}
