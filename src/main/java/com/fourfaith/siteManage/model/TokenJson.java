//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.model;

public class TokenJson {
    private Integer type;
    private Integer secureflag;
    private String token;
    private Token payload;

    public TokenJson() {
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

    public Token getPayload() {
        return this.payload;
    }

    public void setPayload(Token payload) {
        this.payload = payload;
    }
}
