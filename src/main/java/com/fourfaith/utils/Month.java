//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

public enum Month {
    january(1, "1月", "january"),
    february(2, "2月", "february"),
    march(3, "3月", "march"),
    april(4, "4月", "april"),
    may(5, "5月", "may"),
    june(6, "6月", "june"),
    july(7, "7月", "july"),
    august(8, "8月", "august"),
    september(9, "9月", "september"),
    october(10, "10月", "october"),
    november(11, "11月", "november"),
    december(12, "12月", "december");

    private int months;
    private String chineseName;
    private String englishName;

    public int getMonths() {
        return this.months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public String getChineseName() {
        return this.chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return this.englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    private Month(int months, String chineseName, String englishName) {
        this.months = months;
        this.chineseName = chineseName;
        this.englishName = englishName;
    }
}
