//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.reportManage.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StAllDetailsFactor implements Serializable {
    private static final long serialVersionUID = -69930348243749077L;
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
    private Integer dsfl;
    private String addvnm1;
    private String addvnm2;
    private String faddvcd;
    private BigDecimal newFactorValue;
    private Date newDate;
    private BigDecimal maxFactorValue;
    private Date maxDate;
    private BigDecimal minFactorValue;
    private Date minDate;
    private BigDecimal minvoltage;
    private BigDecimal maxvoltage;
    private BigDecimal minsignalinten;
    private BigDecimal maxsignalinten;
    private BigDecimal minpn05;
    private BigDecimal maxpn05;
    private BigDecimal minz;
    private BigDecimal maxz;
    private BigDecimal minzb;
    private BigDecimal maxzb;
    private BigDecimal minzu;
    private BigDecimal maxzu;
    private BigDecimal minvj;
    private BigDecimal maxvj;
    private BigDecimal minva;
    private BigDecimal maxva;
    private BigDecimal minq;
    private BigDecimal maxq;
    private BigDecimal minqa;
    private BigDecimal maxqa;
    private BigDecimal minai;
    private BigDecimal maxai;
    private BigDecimal minc;
    private BigDecimal maxc;
    private BigDecimal minmst;
    private BigDecimal maxmst;
    private BigDecimal minfl;
    private BigDecimal maxfl;
    private BigDecimal minuc;
    private BigDecimal maxuc;
    private BigDecimal minus;
    private BigDecimal maxus;
    private BigDecimal minue;
    private BigDecimal maxue;
    private BigDecimal minej;
    private BigDecimal maxej;
    private BigDecimal mined;
    private BigDecimal maxed;
    private BigDecimal mingtp;
    private BigDecimal maxgtp;
    private BigDecimal minm10;
    private BigDecimal maxm10;
    private BigDecimal minm20;
    private BigDecimal maxm20;
    private BigDecimal minm30;
    private BigDecimal maxm30;
    private BigDecimal minm40;
    private BigDecimal maxm40;
    private BigDecimal minm50;
    private BigDecimal maxm50;
    private BigDecimal minm60;
    private BigDecimal maxm60;
    private BigDecimal minm80;
    private BigDecimal maxm80;
    private BigDecimal minm100;
    private BigDecimal maxm100;
    private BigDecimal minph;
    private BigDecimal maxph;
    private BigDecimal mindoxy;
    private BigDecimal maxdoxy;
    private BigDecimal mincond;
    private BigDecimal maxcond;
    private BigDecimal minturb;
    private BigDecimal maxturb;
    private BigDecimal minnh4n;
    private BigDecimal maxnh4n;
    private BigDecimal mintp;
    private BigDecimal maxtp;
    private BigDecimal mintn;
    private BigDecimal maxtn;
    private BigDecimal minchla;
    private BigDecimal maxchla;
    private BigDecimal mintd11;
    private BigDecimal maxtd11;
    private BigDecimal mintd12;
    private BigDecimal maxtd12;
    private BigDecimal mintd13;
    private BigDecimal maxtd13;
    private BigDecimal mintd14;
    private BigDecimal maxtd14;
    private BigDecimal mintd15;
    private BigDecimal maxtd15;
    private BigDecimal mintd16;
    private BigDecimal maxtd16;
    private BigDecimal mintd17;
    private BigDecimal maxtd17;
    private BigDecimal mintd18;
    private BigDecimal maxtd18;
    private BigDecimal mintd19;
    private BigDecimal maxtd19;
    private BigDecimal mintd20;
    private BigDecimal maxtd20;
    private BigDecimal mintd21;
    private BigDecimal maxtd21;
    private BigDecimal mintd22;
    private BigDecimal maxtd22;
    private BigDecimal mintd23;
    private BigDecimal maxtd23;
    private BigDecimal mintd24;
    private BigDecimal maxtd24;

    public StAllDetailsFactor() {
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

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public Date getTm() {
        return this.tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
    }

    public String getStnm() {
        return this.stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public String getAddvcd() {
        return this.addvcd;
    }

    public void setAddvcd(String addvcd) {
        this.addvcd = addvcd;
    }

    public Integer getDsfl() {
        return this.dsfl;
    }

    public void setDsfl(Integer dsfl) {
        this.dsfl = dsfl;
    }

    public Date getNewDate() {
        return this.newDate;
    }

    public void setNewDate(Date newDate) {
        this.newDate = newDate;
    }

    public Date getMaxDate() {
        return this.maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public Date getMinDate() {
        return this.minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }

    public BigDecimal getNewFactorValue() {
        return this.newFactorValue;
    }

    public void setNewFactorValue(BigDecimal newFactorValue) {
        this.newFactorValue = newFactorValue;
    }

    public BigDecimal getMaxFactorValue() {
        return this.maxFactorValue;
    }

    public void setMaxFactorValue(BigDecimal maxFactorValue) {
        this.maxFactorValue = maxFactorValue;
    }

    public BigDecimal getMinFactorValue() {
        return this.minFactorValue;
    }

    public void setMinFactorValue(BigDecimal minFactorValue) {
        this.minFactorValue = minFactorValue;
    }

    public BigDecimal getMinvoltage() {
        return this.minvoltage;
    }

    public void setMinvoltage(BigDecimal minvoltage) {
        this.minvoltage = minvoltage;
    }

    public BigDecimal getMaxvoltage() {
        return this.maxvoltage;
    }

    public void setMaxvoltage(BigDecimal maxvoltage) {
        this.maxvoltage = maxvoltage;
    }

    public BigDecimal getMinsignalinten() {
        return this.minsignalinten;
    }

    public void setMinsignalinten(BigDecimal minsignalinten) {
        this.minsignalinten = minsignalinten;
    }

    public BigDecimal getMaxsignalinten() {
        return this.maxsignalinten;
    }

    public void setMaxsignalinten(BigDecimal maxsignalinten) {
        this.maxsignalinten = maxsignalinten;
    }

    public BigDecimal getMinpn05() {
        return this.minpn05;
    }

    public void setMinpn05(BigDecimal minpn05) {
        this.minpn05 = minpn05;
    }

    public BigDecimal getMaxpn05() {
        return this.maxpn05;
    }

    public void setMaxpn05(BigDecimal maxpn05) {
        this.maxpn05 = maxpn05;
    }

    public BigDecimal getMinz() {
        return this.minz;
    }

    public void setMinz(BigDecimal minz) {
        this.minz = minz;
    }

    public BigDecimal getMaxz() {
        return this.maxz;
    }

    public void setMaxz(BigDecimal maxz) {
        this.maxz = maxz;
    }

    public BigDecimal getMinzb() {
        return this.minzb;
    }

    public void setMinzb(BigDecimal minzb) {
        this.minzb = minzb;
    }

    public BigDecimal getMaxzb() {
        return this.maxzb;
    }

    public void setMaxzb(BigDecimal maxzb) {
        this.maxzb = maxzb;
    }

    public BigDecimal getMinzu() {
        return this.minzu;
    }

    public void setMinzu(BigDecimal minzu) {
        this.minzu = minzu;
    }

    public BigDecimal getMaxzu() {
        return this.maxzu;
    }

    public void setMaxzu(BigDecimal maxzu) {
        this.maxzu = maxzu;
    }

    public BigDecimal getMinvj() {
        return this.minvj;
    }

    public void setMinvj(BigDecimal minvj) {
        this.minvj = minvj;
    }

    public BigDecimal getMaxvj() {
        return this.maxvj;
    }

    public void setMaxvj(BigDecimal maxvj) {
        this.maxvj = maxvj;
    }

    public BigDecimal getMinva() {
        return this.minva;
    }

    public void setMinva(BigDecimal minva) {
        this.minva = minva;
    }

    public BigDecimal getMaxva() {
        return this.maxva;
    }

    public void setMaxva(BigDecimal maxva) {
        this.maxva = maxva;
    }

    public BigDecimal getMinq() {
        return this.minq;
    }

    public void setMinq(BigDecimal minq) {
        this.minq = minq;
    }

    public BigDecimal getMaxq() {
        return this.maxq;
    }

    public void setMaxq(BigDecimal maxq) {
        this.maxq = maxq;
    }

    public BigDecimal getMinqa() {
        return this.minqa;
    }

    public void setMinqa(BigDecimal minqa) {
        this.minqa = minqa;
    }

    public BigDecimal getMaxqa() {
        return this.maxqa;
    }

    public void setMaxqa(BigDecimal maxqa) {
        this.maxqa = maxqa;
    }

    public BigDecimal getMinai() {
        return this.minai;
    }

    public void setMinai(BigDecimal minai) {
        this.minai = minai;
    }

    public BigDecimal getMaxai() {
        return this.maxai;
    }

    public void setMaxai(BigDecimal maxai) {
        this.maxai = maxai;
    }

    public BigDecimal getMinc() {
        return this.minc;
    }

    public void setMinc(BigDecimal minc) {
        this.minc = minc;
    }

    public BigDecimal getMaxc() {
        return this.maxc;
    }

    public void setMaxc(BigDecimal maxc) {
        this.maxc = maxc;
    }

    public BigDecimal getMinmst() {
        return this.minmst;
    }

    public void setMinmst(BigDecimal minmst) {
        this.minmst = minmst;
    }

    public BigDecimal getMaxmst() {
        return this.maxmst;
    }

    public void setMaxmst(BigDecimal maxmst) {
        this.maxmst = maxmst;
    }

    public BigDecimal getMinfl() {
        return this.minfl;
    }

    public void setMinfl(BigDecimal minfl) {
        this.minfl = minfl;
    }

    public BigDecimal getMaxfl() {
        return this.maxfl;
    }

    public void setMaxfl(BigDecimal maxfl) {
        this.maxfl = maxfl;
    }

    public BigDecimal getMinuc() {
        return this.minuc;
    }

    public void setMinuc(BigDecimal minuc) {
        this.minuc = minuc;
    }

    public BigDecimal getMaxuc() {
        return this.maxuc;
    }

    public void setMaxuc(BigDecimal maxuc) {
        this.maxuc = maxuc;
    }

    public BigDecimal getMinus() {
        return this.minus;
    }

    public void setMinus(BigDecimal minus) {
        this.minus = minus;
    }

    public BigDecimal getMaxus() {
        return this.maxus;
    }

    public void setMaxus(BigDecimal maxus) {
        this.maxus = maxus;
    }

    public BigDecimal getMinue() {
        return this.minue;
    }

    public void setMinue(BigDecimal minue) {
        this.minue = minue;
    }

    public BigDecimal getMaxue() {
        return this.maxue;
    }

    public void setMaxue(BigDecimal maxue) {
        this.maxue = maxue;
    }

    public BigDecimal getMinej() {
        return this.minej;
    }

    public void setMinej(BigDecimal minej) {
        this.minej = minej;
    }

    public BigDecimal getMaxej() {
        return this.maxej;
    }

    public void setMaxej(BigDecimal maxej) {
        this.maxej = maxej;
    }

    public BigDecimal getMined() {
        return this.mined;
    }

    public void setMined(BigDecimal mined) {
        this.mined = mined;
    }

    public BigDecimal getMaxed() {
        return this.maxed;
    }

    public void setMaxed(BigDecimal maxed) {
        this.maxed = maxed;
    }

    public BigDecimal getMingtp() {
        return this.mingtp;
    }

    public void setMingtp(BigDecimal mingtp) {
        this.mingtp = mingtp;
    }

    public BigDecimal getMaxgtp() {
        return this.maxgtp;
    }

    public void setMaxgtp(BigDecimal maxgtp) {
        this.maxgtp = maxgtp;
    }

    public BigDecimal getMinm10() {
        return this.minm10;
    }

    public void setMinm10(BigDecimal minm10) {
        this.minm10 = minm10;
    }

    public BigDecimal getMaxm10() {
        return this.maxm10;
    }

    public void setMaxm10(BigDecimal maxm10) {
        this.maxm10 = maxm10;
    }

    public BigDecimal getMinm20() {
        return this.minm20;
    }

    public void setMinm20(BigDecimal minm20) {
        this.minm20 = minm20;
    }

    public BigDecimal getMaxm20() {
        return this.maxm20;
    }

    public void setMaxm20(BigDecimal maxm20) {
        this.maxm20 = maxm20;
    }

    public BigDecimal getMinm30() {
        return this.minm30;
    }

    public void setMinm30(BigDecimal minm30) {
        this.minm30 = minm30;
    }

    public BigDecimal getMaxm30() {
        return this.maxm30;
    }

    public void setMaxm30(BigDecimal maxm30) {
        this.maxm30 = maxm30;
    }

    public BigDecimal getMinm40() {
        return this.minm40;
    }

    public void setMinm40(BigDecimal minm40) {
        this.minm40 = minm40;
    }

    public BigDecimal getMaxm40() {
        return this.maxm40;
    }

    public void setMaxm40(BigDecimal maxm40) {
        this.maxm40 = maxm40;
    }

    public BigDecimal getMinm50() {
        return this.minm50;
    }

    public void setMinm50(BigDecimal minm50) {
        this.minm50 = minm50;
    }

    public BigDecimal getMaxm50() {
        return this.maxm50;
    }

    public void setMaxm50(BigDecimal maxm50) {
        this.maxm50 = maxm50;
    }

    public BigDecimal getMinm60() {
        return this.minm60;
    }

    public void setMinm60(BigDecimal minm60) {
        this.minm60 = minm60;
    }

    public BigDecimal getMaxm60() {
        return this.maxm60;
    }

    public void setMaxm60(BigDecimal maxm60) {
        this.maxm60 = maxm60;
    }

    public BigDecimal getMinm80() {
        return this.minm80;
    }

    public void setMinm80(BigDecimal minm80) {
        this.minm80 = minm80;
    }

    public BigDecimal getMaxm80() {
        return this.maxm80;
    }

    public void setMaxm80(BigDecimal maxm80) {
        this.maxm80 = maxm80;
    }

    public BigDecimal getMinm100() {
        return this.minm100;
    }

    public void setMinm100(BigDecimal minm100) {
        this.minm100 = minm100;
    }

    public BigDecimal getMaxm100() {
        return this.maxm100;
    }

    public void setMaxm100(BigDecimal maxm100) {
        this.maxm100 = maxm100;
    }

    public BigDecimal getMinph() {
        return this.minph;
    }

    public void setMinph(BigDecimal minph) {
        this.minph = minph;
    }

    public BigDecimal getMaxph() {
        return this.maxph;
    }

    public void setMaxph(BigDecimal maxph) {
        this.maxph = maxph;
    }

    public BigDecimal getMindoxy() {
        return this.mindoxy;
    }

    public void setMindoxy(BigDecimal mindoxy) {
        this.mindoxy = mindoxy;
    }

    public BigDecimal getMaxdoxy() {
        return this.maxdoxy;
    }

    public void setMaxdoxy(BigDecimal maxdoxy) {
        this.maxdoxy = maxdoxy;
    }

    public BigDecimal getMincond() {
        return this.mincond;
    }

    public void setMincond(BigDecimal mincond) {
        this.mincond = mincond;
    }

    public BigDecimal getMaxcond() {
        return this.maxcond;
    }

    public void setMaxcond(BigDecimal maxcond) {
        this.maxcond = maxcond;
    }

    public BigDecimal getMinturb() {
        return this.minturb;
    }

    public void setMinturb(BigDecimal minturb) {
        this.minturb = minturb;
    }

    public BigDecimal getMaxturb() {
        return this.maxturb;
    }

    public void setMaxturb(BigDecimal maxturb) {
        this.maxturb = maxturb;
    }

    public BigDecimal getMinnh4n() {
        return this.minnh4n;
    }

    public void setMinnh4n(BigDecimal minnh4n) {
        this.minnh4n = minnh4n;
    }

    public BigDecimal getMaxnh4n() {
        return this.maxnh4n;
    }

    public void setMaxnh4n(BigDecimal maxnh4n) {
        this.maxnh4n = maxnh4n;
    }

    public BigDecimal getMintp() {
        return this.mintp;
    }

    public void setMintp(BigDecimal mintp) {
        this.mintp = mintp;
    }

    public BigDecimal getMaxtp() {
        return this.maxtp;
    }

    public void setMaxtp(BigDecimal maxtp) {
        this.maxtp = maxtp;
    }

    public BigDecimal getMintn() {
        return this.mintn;
    }

    public void setMintn(BigDecimal mintn) {
        this.mintn = mintn;
    }

    public BigDecimal getMaxtn() {
        return this.maxtn;
    }

    public void setMaxtn(BigDecimal maxtn) {
        this.maxtn = maxtn;
    }

    public BigDecimal getMinchla() {
        return this.minchla;
    }

    public void setMinchla(BigDecimal minchla) {
        this.minchla = minchla;
    }

    public BigDecimal getMaxchla() {
        return this.maxchla;
    }

    public void setMaxchla(BigDecimal maxchla) {
        this.maxchla = maxchla;
    }

    public BigDecimal getMintd11() {
        return this.mintd11;
    }

    public void setMintd11(BigDecimal mintd11) {
        this.mintd11 = mintd11;
    }

    public BigDecimal getMaxtd11() {
        return this.maxtd11;
    }

    public void setMaxtd11(BigDecimal maxtd11) {
        this.maxtd11 = maxtd11;
    }

    public BigDecimal getMintd12() {
        return this.mintd12;
    }

    public void setMintd12(BigDecimal mintd12) {
        this.mintd12 = mintd12;
    }

    public BigDecimal getMaxtd12() {
        return this.maxtd12;
    }

    public void setMaxtd12(BigDecimal maxtd12) {
        this.maxtd12 = maxtd12;
    }

    public BigDecimal getMintd13() {
        return this.mintd13;
    }

    public void setMintd13(BigDecimal mintd13) {
        this.mintd13 = mintd13;
    }

    public BigDecimal getMaxtd13() {
        return this.maxtd13;
    }

    public void setMaxtd13(BigDecimal maxtd13) {
        this.maxtd13 = maxtd13;
    }

    public BigDecimal getMintd14() {
        return this.mintd14;
    }

    public void setMintd14(BigDecimal mintd14) {
        this.mintd14 = mintd14;
    }

    public BigDecimal getMaxtd14() {
        return this.maxtd14;
    }

    public void setMaxtd14(BigDecimal maxtd14) {
        this.maxtd14 = maxtd14;
    }

    public BigDecimal getMintd15() {
        return this.mintd15;
    }

    public void setMintd15(BigDecimal mintd15) {
        this.mintd15 = mintd15;
    }

    public BigDecimal getMaxtd15() {
        return this.maxtd15;
    }

    public void setMaxtd15(BigDecimal maxtd15) {
        this.maxtd15 = maxtd15;
    }

    public BigDecimal getMintd16() {
        return this.mintd16;
    }

    public void setMintd16(BigDecimal mintd16) {
        this.mintd16 = mintd16;
    }

    public BigDecimal getMaxtd16() {
        return this.maxtd16;
    }

    public void setMaxtd16(BigDecimal maxtd16) {
        this.maxtd16 = maxtd16;
    }

    public BigDecimal getMintd17() {
        return this.mintd17;
    }

    public void setMintd17(BigDecimal mintd17) {
        this.mintd17 = mintd17;
    }

    public BigDecimal getMaxtd17() {
        return this.maxtd17;
    }

    public void setMaxtd17(BigDecimal maxtd17) {
        this.maxtd17 = maxtd17;
    }

    public BigDecimal getMintd18() {
        return this.mintd18;
    }

    public void setMintd18(BigDecimal mintd18) {
        this.mintd18 = mintd18;
    }

    public BigDecimal getMaxtd18() {
        return this.maxtd18;
    }

    public void setMaxtd18(BigDecimal maxtd18) {
        this.maxtd18 = maxtd18;
    }

    public BigDecimal getMintd19() {
        return this.mintd19;
    }

    public void setMintd19(BigDecimal mintd19) {
        this.mintd19 = mintd19;
    }

    public BigDecimal getMaxtd19() {
        return this.maxtd19;
    }

    public void setMaxtd19(BigDecimal maxtd19) {
        this.maxtd19 = maxtd19;
    }

    public BigDecimal getMintd20() {
        return this.mintd20;
    }

    public void setMintd20(BigDecimal mintd20) {
        this.mintd20 = mintd20;
    }

    public BigDecimal getMaxtd20() {
        return this.maxtd20;
    }

    public void setMaxtd20(BigDecimal maxtd20) {
        this.maxtd20 = maxtd20;
    }

    public BigDecimal getMintd21() {
        return this.mintd21;
    }

    public void setMintd21(BigDecimal mintd21) {
        this.mintd21 = mintd21;
    }

    public BigDecimal getMaxtd21() {
        return this.maxtd21;
    }

    public void setMaxtd21(BigDecimal maxtd21) {
        this.maxtd21 = maxtd21;
    }

    public BigDecimal getMintd22() {
        return this.mintd22;
    }

    public void setMintd22(BigDecimal mintd22) {
        this.mintd22 = mintd22;
    }

    public BigDecimal getMaxtd22() {
        return this.maxtd22;
    }

    public void setMaxtd22(BigDecimal maxtd22) {
        this.maxtd22 = maxtd22;
    }

    public BigDecimal getMintd23() {
        return this.mintd23;
    }

    public void setMintd23(BigDecimal mintd23) {
        this.mintd23 = mintd23;
    }

    public BigDecimal getMaxtd23() {
        return this.maxtd23;
    }

    public void setMaxtd23(BigDecimal maxtd23) {
        this.maxtd23 = maxtd23;
    }

    public BigDecimal getMintd24() {
        return this.mintd24;
    }

    public void setMintd24(BigDecimal mintd24) {
        this.mintd24 = mintd24;
    }

    public BigDecimal getMaxtd24() {
        return this.maxtd24;
    }

    public void setMaxtd24(BigDecimal maxtd24) {
        this.maxtd24 = maxtd24;
    }

    public BigDecimal getpJ() {
        return this.pJ;
    }

    public void setpJ(BigDecimal pJ) {
        this.pJ = pJ;
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
}
