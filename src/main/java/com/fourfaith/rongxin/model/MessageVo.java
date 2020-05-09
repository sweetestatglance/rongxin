package com.fourfaith.rongxin.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * by alex
 * 2020.4.27
 */
public class MessageVo implements Serializable {

    private String id;

    private String username; //预警接收人

    private String phone; //电话

    private String stcd; //测站编码

    private Integer state; //状态 0没处理，1处理

    private String stname; //站点名称

    private Double z; //水位

    private Double zb; //库下水位

    private Double zu; //库上水位

    private Double waterRanges; //水位变幅

    private Double rainRanges; //雨量变幅

    private String content; //预警内容

    private Double pn05; //雨量

    private Double pj; //日降雨量

    private Date tm; //时间

    public MessageVo() {
    }

    public MessageVo(String id, String username, String phone, String stcd,Integer state, String stname, Double z, Double zb, Double zu, Double waterRanges, Double rainRanges, String content, Double pn05, Double pj, Date tm) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.stcd = stcd;
        this. state = state;
        this.stname = stname;
        this.z = z;
        this.zb = zb;
        this.zu = zu;
        this.waterRanges = waterRanges;
        this.rainRanges = rainRanges;
        this.content = content;
        this.pn05 = pn05;
        this.pj = pj;
        this.tm = tm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public String getStname() {
        return stname;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setStname(String stname) {
        this.stname = stname;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    public Double getZb() {
        return zb;
    }

    public void setZb(Double zb) {
        this.zb = zb;
    }

    public Double getZu() {
        return zu;
    }

    public void setZu(Double zu) {
        this.zu = zu;
    }

    public Double getWaterRanges() {
        return waterRanges;
    }

    public void setWaterRanges(Double waterRanges) {
        this.waterRanges = waterRanges;
    }

    public Double getRainRanges() {
        return rainRanges;
    }

    public void setRainRanges(Double rainRanges) {
        this.rainRanges = rainRanges;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getPn05() {
        return pn05;
    }

    public void setPn05(Double pn05) {
        this.pn05 = pn05;
    }

    public Double getPj() {
        return pj;
    }

    public void setPj(Double pj) {
        this.pj = pj;
    }

    public Date getTm() {
        return tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    @Override
    public String toString() {
        return "MessageVo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", stcd='" + stcd + '\'' +
                ", state='" + state + '\'' +
                ", stname='" + stname + '\'' +
                ", z=" + z +
                ", zb=" + zb +
                ", zu=" + zu +
                ", waterRanges=" + waterRanges +
                ", rainRanges=" + rainRanges +
                ", content='" + content + '\'' +
                ", pn05=" + pn05 +
                ", pj=" + pj +
                ", tm=" + tm +
                '}';
    }
}
