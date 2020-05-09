package com.fourfaith.rongxin.wechat;

import java.io.Serializable;

/**
 * Created by Tiffany on 2019-7-18.
 */
public class WechatToken implements Serializable {

    //获取token
    private String token;
    //获取token时间
    private Long addTime;
    //token保存时长
    private int expiresIn;

    public WechatToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "AzureToken{" +
                "token='" + token + '\'' +
                ", addTime=" + addTime +
                ", expiresIn=" + expiresIn +
                '}';
    }
}
