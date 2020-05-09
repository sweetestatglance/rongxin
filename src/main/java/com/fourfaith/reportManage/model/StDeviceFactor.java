//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StDeviceFactor implements Serializable {
    private static final long serialVersionUID = -4109863376829333270L;
    private String id;
    private String stcd;
    private String addvcd;
    private BigDecimal voltage;
    private BigDecimal signalinten;
    private BigDecimal pn05;
    private BigDecimal pJ;
    private BigDecimal z;
    private BigDecimal zb;
    private BigDecimal zu;
    private BigDecimal vj;
    private BigDecimal va;
    private BigDecimal q;
    private BigDecimal qa;
    private BigDecimal ai;
    private BigDecimal c;
    private BigDecimal mst;
    private BigDecimal fl;
    private BigDecimal uc;
    private BigDecimal us;
    private BigDecimal ue;
    private BigDecimal ej;
    private BigDecimal ed;
    private BigDecimal gtp;
    private BigDecimal m10;
    private BigDecimal m20;
    private BigDecimal m30;
    private BigDecimal m40;
    private BigDecimal m50;
    private BigDecimal m60;
    private BigDecimal m80;
    private BigDecimal m100;
    private BigDecimal ph;
    private BigDecimal doxy;
    private BigDecimal cond;
    private BigDecimal turb;
    private BigDecimal nh4n;
    private BigDecimal tp;
    private BigDecimal tn;
    private BigDecimal chla;
    private BigDecimal td11;
    private BigDecimal td12;
    private BigDecimal td13;
    private BigDecimal td14;
    private BigDecimal td15;
    private BigDecimal td16;
    private BigDecimal td17;
    private BigDecimal td18;
    private BigDecimal td19;
    private BigDecimal td20;
    private BigDecimal td21;
    private BigDecimal td22;
    private BigDecimal td23;
    private BigDecimal td24;
    private Date tm;
    private String stnm;
    private Integer sttp;
    private BigDecimal lgtd;
    private BigDecimal lttd;
    private String stlc;
    private Integer cameratype;
    private Integer iscamera;
    private Integer dsfl;
    private Date lastonline;
    private String commode;
    private String videochannel;
    private String dvraddr;
    private String dvrcode;
    private String addvnm1;
    private String addvnm2;
    private String faddvcd;
    private Integer maxpj;
    private Date moditime;

    public StDeviceFactor() {
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

    public BigDecimal getVoltage() {
        return this.voltage;
    }

    public void setVoltage(BigDecimal voltage) {
        this.voltage = voltage;
    }

    public BigDecimal getPh() {
        return this.ph;
    }

    public void setPh(BigDecimal ph) {
        this.ph = ph;
    }

    public Date getTm() {
        return this.tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public String getAddvcd() {
        return this.addvcd;
    }

    public void setAddvcd(String addvcd) {
        this.addvcd = addvcd;
    }

    public String getStnm() {
        return this.stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public Integer getSttp() {
        return this.sttp;
    }

    public void setSttp(Integer sttp) {
        this.sttp = sttp;
    }

    public BigDecimal getLgtd() {
        return this.lgtd;
    }

    public void setLgtd(BigDecimal lgtd) {
        this.lgtd = lgtd;
    }

    public BigDecimal getLttd() {
        return this.lttd;
    }

    public void setLttd(BigDecimal lttd) {
        this.lttd = lttd;
    }

    public String getStlc() {
        return this.stlc;
    }

    public void setStlc(String stlc) {
        this.stlc = stlc;
    }

    public Integer getIscamera() {
        return this.iscamera;
    }

    public void setIscamera(Integer iscamera) {
        this.iscamera = iscamera;
    }

    public Integer getDsfl() {
        return this.dsfl;
    }

    public void setDsfl(Integer dsfl) {
        this.dsfl = dsfl;
    }

    public Date getLastonline() {
        return this.lastonline;
    }

    public void setLastonline(Date lastonline) {
        this.lastonline = lastonline;
    }

    public Integer getCameratype() {
        return this.cameratype;
    }

    public void setCameratype(Integer cameratype) {
        this.cameratype = cameratype;
    }

    public String getVideochannel() {
        return this.videochannel;
    }

    public void setVideochannel(String videochannel) {
        this.videochannel = videochannel;
    }

    public String getDvraddr() {
        return this.dvraddr;
    }

    public void setDvraddr(String dvraddr) {
        this.dvraddr = dvraddr;
    }

    public String getDvrcode() {
        return this.dvrcode;
    }

    public void setDvrcode(String dvrcode) {
        this.dvrcode = dvrcode;
    }

    public BigDecimal getSignalinten() {
        return this.signalinten;
    }

    public void setSignalinten(BigDecimal signalinten) {
        this.signalinten = signalinten;
    }

    public BigDecimal getPn05() {
        return this.pn05;
    }

    public void setPn05(BigDecimal pn05) {
        this.pn05 = pn05;
    }

    public BigDecimal getZ() {
        return this.z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
    }

    public BigDecimal getZb() {
        return this.zb;
    }

    public void setZb(BigDecimal zb) {
        this.zb = zb;
    }

    public BigDecimal getZu() {
        return this.zu;
    }

    public void setZu(BigDecimal zu) {
        this.zu = zu;
    }

    public BigDecimal getVj() {
        return this.vj;
    }

    public void setVj(BigDecimal vj) {
        this.vj = vj;
    }

    public BigDecimal getVa() {
        return this.va;
    }

    public void setVa(BigDecimal va) {
        this.va = va;
    }

    public BigDecimal getQ() {
        return this.q;
    }

    public void setQ(BigDecimal q) {
        this.q = q;
    }

    public BigDecimal getQa() {
        return this.qa;
    }

    public void setQa(BigDecimal qa) {
        this.qa = qa;
    }

    public BigDecimal getAi() {
        return this.ai;
    }

    public void setAi(BigDecimal ai) {
        this.ai = ai;
    }

    public BigDecimal getC() {
        return this.c;
    }

    public void setC(BigDecimal c) {
        this.c = c;
    }

    public BigDecimal getMst() {
        return this.mst;
    }

    public void setMst(BigDecimal mst) {
        this.mst = mst;
    }

    public BigDecimal getFl() {
        return this.fl;
    }

    public void setFl(BigDecimal fl) {
        this.fl = fl;
    }

    public BigDecimal getUc() {
        return this.uc;
    }

    public void setUc(BigDecimal uc) {
        this.uc = uc;
    }

    public BigDecimal getUs() {
        return this.us;
    }

    public void setUs(BigDecimal us) {
        this.us = us;
    }

    public BigDecimal getUe() {
        return this.ue;
    }

    public void setUe(BigDecimal ue) {
        this.ue = ue;
    }

    public BigDecimal getEj() {
        return this.ej;
    }

    public void setEj(BigDecimal ej) {
        this.ej = ej;
    }

    public BigDecimal getEd() {
        return this.ed;
    }

    public void setEd(BigDecimal ed) {
        this.ed = ed;
    }

    public BigDecimal getGtp() {
        return this.gtp;
    }

    public void setGtp(BigDecimal gtp) {
        this.gtp = gtp;
    }

    public BigDecimal getM10() {
        return this.m10;
    }

    public void setM10(BigDecimal m10) {
        this.m10 = m10;
    }

    public BigDecimal getM20() {
        return this.m20;
    }

    public void setM20(BigDecimal m20) {
        this.m20 = m20;
    }

    public BigDecimal getM30() {
        return this.m30;
    }

    public void setM30(BigDecimal m30) {
        this.m30 = m30;
    }

    public BigDecimal getM40() {
        return this.m40;
    }

    public void setM40(BigDecimal m40) {
        this.m40 = m40;
    }

    public BigDecimal getM50() {
        return this.m50;
    }

    public void setM50(BigDecimal m50) {
        this.m50 = m50;
    }

    public BigDecimal getM60() {
        return this.m60;
    }

    public void setM60(BigDecimal m60) {
        this.m60 = m60;
    }

    public BigDecimal getM80() {
        return this.m80;
    }

    public void setM80(BigDecimal m80) {
        this.m80 = m80;
    }

    public BigDecimal getM100() {
        return this.m100;
    }

    public void setM100(BigDecimal m100) {
        this.m100 = m100;
    }

    public BigDecimal getDoxy() {
        return this.doxy;
    }

    public void setDoxy(BigDecimal doxy) {
        this.doxy = doxy;
    }

    public BigDecimal getCond() {
        return this.cond;
    }

    public void setCond(BigDecimal cond) {
        this.cond = cond;
    }

    public BigDecimal getTurb() {
        return this.turb;
    }

    public void setTurb(BigDecimal turb) {
        this.turb = turb;
    }

    public BigDecimal getNh4n() {
        return this.nh4n;
    }

    public void setNh4n(BigDecimal nh4n) {
        this.nh4n = nh4n;
    }

    public BigDecimal getTp() {
        return this.tp;
    }

    public void setTp(BigDecimal tp) {
        this.tp = tp;
    }

    public BigDecimal getTn() {
        return this.tn;
    }

    public void setTn(BigDecimal tn) {
        this.tn = tn;
    }

    public BigDecimal getChla() {
        return this.chla;
    }

    public void setChla(BigDecimal chla) {
        this.chla = chla;
    }

    public BigDecimal getTd11() {
        return this.td11;
    }

    public void setTd11(BigDecimal td11) {
        this.td11 = td11;
    }

    public BigDecimal getTd12() {
        return this.td12;
    }

    public void setTd12(BigDecimal td12) {
        this.td12 = td12;
    }

    public BigDecimal getTd13() {
        return this.td13;
    }

    public void setTd13(BigDecimal td13) {
        this.td13 = td13;
    }

    public BigDecimal getTd14() {
        return this.td14;
    }

    public void setTd14(BigDecimal td14) {
        this.td14 = td14;
    }

    public BigDecimal getTd15() {
        return this.td15;
    }

    public void setTd15(BigDecimal td15) {
        this.td15 = td15;
    }

    public BigDecimal getTd16() {
        return this.td16;
    }

    public void setTd16(BigDecimal td16) {
        this.td16 = td16;
    }

    public BigDecimal getTd17() {
        return this.td17;
    }

    public void setTd17(BigDecimal td17) {
        this.td17 = td17;
    }

    public BigDecimal getTd18() {
        return this.td18;
    }

    public void setTd18(BigDecimal td18) {
        this.td18 = td18;
    }

    public BigDecimal getTd19() {
        return this.td19;
    }

    public void setTd19(BigDecimal td19) {
        this.td19 = td19;
    }

    public BigDecimal getTd20() {
        return this.td20;
    }

    public void setTd20(BigDecimal td20) {
        this.td20 = td20;
    }

    public BigDecimal getTd21() {
        return this.td21;
    }

    public void setTd21(BigDecimal td21) {
        this.td21 = td21;
    }

    public BigDecimal getTd22() {
        return this.td22;
    }

    public void setTd22(BigDecimal td22) {
        this.td22 = td22;
    }

    public BigDecimal getTd23() {
        return this.td23;
    }

    public void setTd23(BigDecimal td23) {
        this.td23 = td23;
    }

    public BigDecimal getTd24() {
        return this.td24;
    }

    public void setTd24(BigDecimal td24) {
        this.td24 = td24;
    }

    public static long getSerialVersionUID() {
        return -4109863376829333270L;
    }

    public BigDecimal getpJ() {
        return this.pJ;
    }

    public void setpJ(BigDecimal pJ) {
        this.pJ = pJ;
    }

    public String getCommode() {
        return this.commode;
    }

    public void setCommode(String commode) {
        this.commode = commode;
    }

    public String getAddvnm1() {
        return this.addvnm1;
    }

    public void setAddvnm1(String addvnm1) {
        this.addvnm1 = addvnm1;
    }

    public String getAddvnm2() {
        return this.addvnm2;
    }

    public void setAddvnm2(String addvnm2) {
        this.addvnm2 = addvnm2;
    }

    public String getFaddvcd() {
        return this.faddvcd;
    }

    public void setFaddvcd(String faddvcd) {
        this.faddvcd = faddvcd;
    }

    public Integer getMaxpj() {
        return this.maxpj;
    }

    public void setMaxpj(Integer maxpj) {
        this.maxpj = maxpj;
    }

    public Date getModitime() {
        return this.moditime;
    }

    public void setModitime(Date moditime) {
        this.moditime = moditime;
    }
}
