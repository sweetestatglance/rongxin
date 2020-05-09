package com.fourfaith.rongxin.model;

public class Openid {

    private Integer id;

    private String wxuser;

    private String wxopenid;

    private String phone;

    private Integer state;

    public Openid() {
    }

    public Openid(Integer id, String wxuser, String wxopenid, String phone, Integer state) {
        this.id = id;
        this.wxuser = wxuser;
        this.wxopenid = wxopenid;
        this.phone = phone;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWxuser() {
        return wxuser;
    }

    public void setWxuser(String wxuser) {
        this.wxuser = wxuser;
    }

    public String getWxopenid() {
        return wxopenid;
    }

    public void setWxopenid(String wxopenid) {
        this.wxopenid = wxopenid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Openid{" +
                "id=" + id +
                ", wxuser='" + wxuser + '\'' +
                ", wxopenid='" + wxopenid + '\'' +
                ", phone='" + phone + '\'' +
                ", state=" + state +
                '}';
    }
}
