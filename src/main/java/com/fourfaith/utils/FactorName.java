//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.RequestContext;

public enum FactorName {
    voltage("电压", "Voltage", "voltage", "V", "38", "VT"),
    signalinten("信号强度", "Signal strength", "signalinten", "", "FF1F", "CSQ"),
    pn05("时段降水量", "rainfall", "pn05", "mm", "20", "PJ"),
    z("水位", "Water level", "z", "m", "39", "Z"),
    zb("库下水位", "Library water level", "zb", "m", "3A", "ZB"),
    zu("库上水位", "The water level on the reservoir", "zu", "m", "3B", "ZU"),
    vj("瞬时流速", "Instantaneous flow rate", "vj", "m/s", "37", "VJ"),
    va("平均流速", "Average flow rate", "va", "m/s", "36", "VA"),
    q("瞬时流量", "Instantaneous flow", "q", "m³/h", "27", "Q"),
    qa("总流量", "Total traffic", "qa", "m³", "30", "QA"),
    ai("气温", "Temperature", "ai", "℃", "02", "AI"),
    c("水温", "Water temperature", "c", "℃", "03", "C"),
    mst("湿度", "humidity", "mst", "%", "18", "MST"),
    fl("气压", "Pressure", "fl", "hPa", "08", "FL"),
    uc("风向", "wind direction", "uc", "", "33", "UC"),
    us("风速", "Wind speed", "us", "m/s", "35", "US"),
    ue("风力", "Wind power", "ue", "", "34", "UE"),
    ej("当前蒸发", "Currently evaporating", "ej", "mm", "07", "EJ"),
    ed("日蒸发量", "Daily evaporation", "ed", "mm", "06", "ED"),
    gtp("地温", "Ground temperature", "gtp", "℃", "0D", "GTP"),
    m10("10厘米土壤含水率", "10 cm soil moisture content", "m10", "%", "10", "M10"),
    m20("20厘米土壤含水率", "20 cm soil moisture content", "m20", "%", "11", "M20"),
    m30("30厘米土壤含水率", "30 cm soil moisture content", "m30", "%", "12", "M30"),
    m40("40厘米土壤含水率", "40 cm soil moisture content", "m40", "%", "13", "M40"),
    m50("50厘米土壤含水率", "50 cm soil moisture content", "m50", "%", "14", "M50"),
    m60("60厘米土壤含水率", "60 cm soil moisture content", "m60", "%", "15", "M60"),
    m80("80厘米土壤含水率", "80 cm soil moisture content", "m80", "%", "16", "M80"),
    m100("100厘米土壤含水率", "100 cm soil moisture content", "m100", "%", "17", "M100"),
    ph("PH", "PH", "ph", "", "46", "pH"),
    doxy("溶解氧", "Dissolved oxygen", "doxy", "mg/L", "47", "DO"),
    cond("电导率", "Conductivity", "cond", "us/cm", "48", "COND"),
    turb("浊度", "Turbidity", "turb", "NTU", "49", "TURB"),
    nh4n("氨氮", "Ammonia", "nh4n", "mg/L", "4C", "NH4N"),
    tp("总磷", "Total phosphorus", "tp", "mg/L", "4D", "TP"),
    tn("总氮", "Total nitrogen", "tn", "mg/L", "4E", "TN"),
    chla("叶绿素a", "Chlorophyll a", "chla", "mg/L", "57", "CHLA"),
    td11("通道11", "Channel 11", "td11", "", "FF2F", "DI0"),
    td12("通道12", "Channel 12", "td12", "", "FF12", "TD12"),
    td13("通道13", "Channel 13", "td13", "", "FF13", "TD13"),
    td14("通道14", "Channel 14", "td14", "", "FF14", "TD14"),
    td15("通道15", "Channel 15", "td15", "", "FF15", "TD15"),
    td16("通道16", "Channel 16", "td16", "", "FF16", "TD16"),
    td17("通道17", "Channel 17", "td17", "", "FF17", "TD17"),
    td18("通道18", "Channel 18", "td18", "", "FF18", "TD18"),
    td19("通道19", "Channel 19", "td19", "", "FF19", "TD19"),
    td20("通道20", "Channel 20", "td20", "", "FF1A", "TD20"),
    td21("通道21", "Channel 21", "td21", "", "FF1B", "TD21"),
    td22("通道22", "Channel 22", "td22", "", "FF1C", "TD22"),
    td23("通道23", "Channel 23", "td23", "", "FF1D", "TD23"),
    td24("通道24", "Channel 24", "td24", "", "FF1E", "TD24");

    private String name;
    private String enName;
    private String flag;
    private String unit;
    private String hexCode;
    private String AsciiCode;

    private FactorName(String name, String enName, String flag, String unit, String hexCode, String AsciiCode) {
        this.name = name;
        this.enName = enName;
        this.flag = flag;
        this.unit = unit;
        this.hexCode = hexCode;
        this.AsciiCode = AsciiCode;
    }

    public String getName() {
        return this.name;
    }

    public static String getName(FactorName factorName) {
        return factorName.name;
    }

    public String getEnName() {
        return this.enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getHexCode() {
        return this.hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getAsciiCode() {
        return this.AsciiCode;
    }

    public void setAsciiCode(String asciiCode) {
        this.AsciiCode = asciiCode;
    }

    public String toString() {
        return "{\"name\":\"" + this.name + "\",\"flag\":\"" + this.flag + "\",\"unit\":\"" + this.unit + "\",\"hexCode\":\"" + this.hexCode + "\",\"AsciiCode\":\"" + this.AsciiCode + "\"}";
    }

    public static LinkedHashMap<String, FactorName> getFactorMap() {
        LinkedHashMap<String, FactorName> map = new LinkedHashMap();
        FactorName[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            FactorName e = var4[var2];
            map.put(e.flag, e);
        }

        return map;
    }

    public static LinkedHashMap<String, String> getFactorNameMap(HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        LinkedHashMap<String, String> map = new LinkedHashMap();
        FactorName[] var6;
        int var5 = (var6 = values()).length;

        for(int var4 = 0; var4 < var5; ++var4) {
            FactorName e = var6[var4];
            if (requestContext.getMessage("userLogin").equals("User login")) {
                map.put(e.flag, e.enName);
            } else {
                map.put(e.flag, e.name);
            }
        }

        return map;
    }

    public static LinkedHashMap<String, String> getFactorUnitMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap();
        FactorName[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            FactorName e = var4[var2];
            map.put(e.flag, e.unit);
        }

        return map;
    }
}
