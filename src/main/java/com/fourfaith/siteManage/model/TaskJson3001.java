//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.model;

import java.util.Map;

public class TaskJson3001 {
    private Integer type;
    private Integer secureflag;
    private Integer rspcode;
    private String token;
    private Map<String, Object> payload;

    public TaskJson3001() {
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSecureflag() {
        return this.secureflag;
    }

    public void setSecureflag(Integer secureflag) {
        this.secureflag = secureflag;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<String, Object> getPayload() {
        return this.payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    public Integer getRspcode() {
        return this.rspcode;
    }

    public void setRspcode(Integer rspcode) {
        this.rspcode = rspcode;
    }
}
