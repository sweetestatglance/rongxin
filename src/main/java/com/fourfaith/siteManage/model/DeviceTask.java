//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.siteManage.model;

import java.io.Serializable;
import java.util.Date;

public class DeviceTask implements Serializable {
    private static final long serialVersionUID = -4752397389725240111L;
    private String id;
    private String stcd;
    private String taskcode;
    private String taskcontent;
    private String downlinkpacket;
    private String uplinkpacket;
    private Date taskcreatetime;
    private Date taskexecutiontime;
    private Date taskcomplatetime;
    private Integer taskstatus;

    public DeviceTask() {
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

    public String getTaskcode() {
        return this.taskcode;
    }

    public void setTaskcode(String taskcode) {
        this.taskcode = taskcode;
    }

    public Object getTaskcontent() {
        return this.taskcontent;
    }

    public void setTaskcontent(String taskcontent) {
        this.taskcontent = taskcontent;
    }

    public Object getDownlinkpacket() {
        return this.downlinkpacket;
    }

    public void setDownlinkpacket(String downlinkpacket) {
        this.downlinkpacket = downlinkpacket;
    }

    public Object getUplinkpacket() {
        return this.uplinkpacket;
    }

    public void setUplinkpacket(String uplinkpacket) {
        this.uplinkpacket = uplinkpacket;
    }

    public Date getTaskcreatetime() {
        return this.taskcreatetime;
    }

    public void setTaskcreatetime(Date taskcreatetime) {
        this.taskcreatetime = taskcreatetime;
    }

    public Date getTaskexecutiontime() {
        return this.taskexecutiontime;
    }

    public void setTaskexecutiontime(Date taskexecutiontime) {
        this.taskexecutiontime = taskexecutiontime;
    }

    public Date getTaskcomplatetime() {
        return this.taskcomplatetime;
    }

    public void setTaskcomplatetime(Date taskcomplatetime) {
        this.taskcomplatetime = taskcomplatetime;
    }

    public Integer getTaskstatus() {
        return this.taskstatus;
    }

    public void setTaskstatus(Integer taskstatus) {
        this.taskstatus = taskstatus;
    }
}
