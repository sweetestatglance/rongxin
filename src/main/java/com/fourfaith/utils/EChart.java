//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.util.List;
import java.util.Map;

public class EChart {
    private List<?> xAxiDataList;
    private List<?> legendDataList;
    private List<Map<String, Object>> series;
    private Map<String, Object> dataMap;
    private List<Map<String, Object>> yAxis;
    private int gridLeft = 20;
    private int gridRight = 45;

    public EChart() {
    }

    public List<?> getLegendDataList() {
        return this.legendDataList;
    }

    public void setLegendDataList(List<?> legendDataList) {
        this.legendDataList = legendDataList;
    }

    public List<Map<String, Object>> getSeries() {
        return this.series;
    }

    public void setSeries(List<Map<String, Object>> series) {
        this.series = series;
    }

    public List<?> getxAxiDataList() {
        return this.xAxiDataList;
    }

    public void setxAxiDataList(List<?> xAxiDataList) {
        this.xAxiDataList = xAxiDataList;
    }

    public Map<String, Object> getDataMap() {
        return this.dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public List<Map<String, Object>> getyAxis() {
        return this.yAxis;
    }

    public void setyAxis(List<Map<String, Object>> yAxis) {
        this.yAxis = yAxis;
    }

    public int getGridLeft() {
        return this.gridLeft;
    }

    public void setGridLeft(int gridLeft) {
        this.gridLeft = gridLeft;
    }

    public int getGridRight() {
        return this.gridRight;
    }

    public void setGridRight(int gridRight) {
        this.gridRight = gridRight;
    }
}
