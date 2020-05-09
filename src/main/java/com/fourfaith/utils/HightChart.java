//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.util.List;
import java.util.Map;

public class HightChart {
    private Map<String, Object> xAxi;
    private List dataList;
    private String yAxisTitle;
    private String chartType = "line";

    public HightChart() {
    }

    public List getDataList() {
        return this.dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public Map<String, Object> getxAxi() {
        return this.xAxi;
    }

    public void setxAxi(Map<String, Object> xAxi) {
        this.xAxi = xAxi;
    }

    public String getyAxisTitle() {
        return this.yAxisTitle;
    }

    public void setyAxisTitle(String yAxisTitle) {
        this.yAxisTitle = yAxisTitle;
    }

    public String getChartType() {
        return this.chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }
}
