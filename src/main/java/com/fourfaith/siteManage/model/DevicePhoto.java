//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.model;

import java.io.Serializable;
import java.util.Date;

public class DevicePhoto implements Serializable {
    private static final long serialVersionUID = -3376871865522238793L;
    private String id;
    private String stcd;
    private Integer phototype;
    private String photoid;
    private Integer channelid;
    private String photourl;
    private Date photocreatetime;
    private Date photocomplatetime;
    private Integer photostatus;
    private Date photoseetime;

    public DevicePhoto() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStcd() {
        return this.stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public Integer getPhototype() {
        return this.phototype;
    }

    public void setPhototype(Integer phototype) {
        this.phototype = phototype;
    }

    public String getPhotoid() {
        return this.photoid;
    }

    public void setPhotoid(String photoid) {
        this.photoid = photoid;
    }

    public String getPhotourl() {
        return this.photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public Date getPhotocreatetime() {
        return this.photocreatetime;
    }

    public void setPhotocreatetime(Date photocreatetime) {
        this.photocreatetime = photocreatetime;
    }

    public Date getPhotocomplatetime() {
        return this.photocomplatetime;
    }

    public void setPhotocomplatetime(Date photocomplatetime) {
        this.photocomplatetime = photocomplatetime;
    }

    public Integer getPhotostatus() {
        return this.photostatus;
    }

    public void setPhotostatus(Integer photostatus) {
        this.photostatus = photostatus;
    }

    public Integer getChannelid() {
        return this.channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public Date getPhotoseetime() {
        return this.photoseetime;
    }

    public void setPhotoseetime(Date photoseetime) {
        this.photoseetime = photoseetime;
    }
}
