//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.codeGenerator.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CodeDateUtils {
    public static final String DATESTYLE = "yyyyMMddHHmmss";
    public static final String DATESTYLE_EX = "yyyy-MM-dd_HH-mm-ss";
    public static final String DATESTYLE_ = "yyyy-MM-dd";
    public static final String DATESTYLE_YEAR_MONTH = "yyyyMM";
    public static final String DATESTYLE_SHORT = "yyyyMMdd";
    public static final String DATESTYLE_SHORT_EX = "yyyy/MM/dd";
    public static final String DATESTYLE_YEAR_MONTH_EX = "yyyy/MM";
    public static final String DATESTYLE_DETAIL = "yyyyMMddHHmmssSSS";

    public static String dateToString(Date date) {
        return date == null ? "" : FormatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String dateToStringShort(Date date) {
        return date == null ? "" : FormatDate(date, "yyyy-MM-dd");
    }

    public static long diffTwoDate(Date date1, Date date2) {
        long l1 = date1.getTime();
        long l2 = date2.getTime();
        return l1 - l2;
    }

    public static int diffTwoDateDay(Date date1, Date date2) {
        long l1 = date1.getTime();
        long l2 = date2.getTime();
        int diff = Integer.parseInt("" + (l1 - l2) / 3600L / 24L / 1000L);
        return diff;
    }

    public static String FormatDate(Date date, String sf) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat dateformat = new SimpleDateFormat(sf);
            return dateformat.format(date);
        }
    }

    public static String getCurrDate() {
        Date date_time = new Date();
        return FormatDate(date_time, "yyyy-MM-dd");
    }

    public static Date getCurrDateTime() {
        return new Date(System.currentTimeMillis());
    }

    public static String getCurrTime() {
        Date date_time = new Date();
        return FormatDate(date_time, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDate10to8(String str) {
        String y = str.substring(0, 4);
        String m = str.substring(5, 7);
        String d = str.substring(8, 10);
        return y + m + d;
    }

    public static String getDate8to10(String str) {
        String y = str.substring(0, 4);
        String m = str.substring(4, 6);
        String d = str.substring(6, 8);
        return y + "-" + m + "-" + d;
    }

    public static String getDay(Date date) {
        return FormatDate(date, "dd");
    }

    public static String getHour(Date date) {
        return FormatDate(date, "HH");
    }

    public static String getMinute(Date date) {
        return FormatDate(date, "mm");
    }

    public static String getMonth(Date date) {
        return FormatDate(date, "MM");
    }

    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(5, 1);
        int year = endCalendar.get(1) - startCalendar.get(1);
        int month = endCalendar.get(2) - startCalendar.get(2);
        if (startCalendar.get(5) == 1 && temp.get(5) == 1) {
            return year * 12 + month + 1;
        } else if (startCalendar.get(5) != 1 && temp.get(5) == 1) {
            return year * 12 + month;
        } else if (startCalendar.get(5) == 1 && temp.get(5) != 1) {
            return year * 12 + month;
        } else {
            return year * 12 + month - 1 >= 0 ? year * 12 + month : 0;
        }
    }

    public static String getSecond(Date date) {
        return FormatDate(date, "ss");
    }

    public static String getTime(String year, String month) {
        String time = "";
        int len = 31;
        int iYear = Integer.parseInt(year);
        int iMonth = Integer.parseInt(month);
        if (iMonth == 4 || iMonth == 6 || iMonth == 9 || iMonth == 11) {
            len = 30;
        }

        if (iMonth == 2) {
            len = 28;
            if (iYear % 4 == 0 && iYear % 100 == 0 && iYear % 400 == 0 || iYear % 4 == 0 && iYear % 100 != 0) {
                len = 29;
            }
        }

        time = year + "-" + month + "-" + len;
        return time;
    }

    public static String getYear(Date date) {
        return FormatDate(date, "yyyy");
    }

    public static void main(String[] args) {
        new CodeDateUtils();
        String strDate = "2007-02-11";
        Date aa = stringToDateShort(strDate);
        new CodeDateUtils();
    }

    public static Date stringToDate(String dateString) {
        if (dateString != null && dateString.trim().length() != 0) {
            String datestr = dateString.trim();
            String sf = "yyyy-MM-dd HH:mm:ss";
            Date dt = stringToDate(datestr, sf);
            if (dt == null) {
                dt = stringToDate(datestr, "yyyy-MM-dd");
            }

            if (dt == null) {
                dt = stringToDate(datestr, "yyyyMMdd");
            }

            return dt;
        } else {
            return null;
        }
    }

    public static Date stringToDate(String dateString, String sf) {
        ParsePosition pos = new ParsePosition(0);
        SimpleDateFormat sdf = new SimpleDateFormat(sf);
        Date dt = sdf.parse(dateString, pos);
        return dt;
    }

    public static Date stringToDateShort(String dateString) {
        String sf = "yyyy-MM-dd";
        Date dt = stringToDate(dateString, sf);
        return dt;
    }

    public CodeDateUtils() {
    }

    public String getBeginDate(String granularity, String statisticDate) {
        String beginDate = "";
        Date date = stringToDateShort(statisticDate);
        Date beginDateTemp = null;
        if (granularity.equals("1")) {
            beginDateTemp = date;
        }

        if (granularity.equals("2")) {
            beginDateTemp = this.getWeekBegin(date);
        }

        if (granularity.equals("3")) {
            beginDateTemp = this.getPeriodBegin(date);
        } else if (granularity.equals("4")) {
            beginDateTemp = this.getMonthBegin(date);
        } else if (granularity.equals("5")) {
            beginDateTemp = this.getSeasonBegin(date);
        } else if (granularity.equals("6")) {
            beginDateTemp = this.getHalfYearBegin(date);
        } else if (granularity.equals("7")) {
            beginDateTemp = this.getYearBegin(date);
        }

        beginDate = dateToStringShort(beginDateTemp);
        return beginDate;
    }

    public String getDateChangeALL(String currentTime, String type, int iQuantity) {
        Date curr = null;
        String newtype = "";
        if (currentTime.length() == 10) {
            curr = stringToDateShort(currentTime);
        }

        if (currentTime.length() > 10) {
            curr = stringToDate(currentTime);
        }

        if (type.equals("1")) {
            iQuantity = iQuantity;
            newtype = "d";
        } else if (type.equals("2")) {
            iQuantity *= 7;
            newtype = "d";
        } else if (type.equals("3")) {
            iQuantity *= 10;
            newtype = "d";
        } else if (type.equals("4")) {
            iQuantity = iQuantity;
            newtype = "m";
        } else if (type.equals("5")) {
            iQuantity *= 3;
            newtype = "m";
        } else if (type.equals("6")) {
            iQuantity *= 6;
            newtype = "m";
        } else if (type.equals("7")) {
            newtype = "y";
        } else {
            iQuantity = iQuantity;
            newtype = "d";
        }

        Date change = this.getDateChangeTime(curr, newtype, iQuantity);
        return dateToStringShort(change);
    }

    public Date getDateChangeTime(Date currentTime, String type, int iQuantity) {
        int year = Integer.parseInt(FormatDate(currentTime, "yyyy"));
        int month = Integer.parseInt(FormatDate(currentTime, "MM"));
        --month;
        int day = Integer.parseInt(FormatDate(currentTime, "dd"));
        int hour = Integer.parseInt(FormatDate(currentTime, "HH"));
        int mi = Integer.parseInt(FormatDate(currentTime, "mm"));
        int ss = Integer.parseInt(FormatDate(currentTime, "ss"));
        GregorianCalendar gc = new GregorianCalendar(year, month, day, hour, mi, ss);
        if (type.equalsIgnoreCase("y")) {
            gc.add(1, iQuantity);
        } else if (type.equalsIgnoreCase("m")) {
            gc.add(2, iQuantity);
        } else if (type.equalsIgnoreCase("d")) {
            gc.add(5, iQuantity);
        } else if (type.equalsIgnoreCase("h")) {
            gc.add(10, iQuantity);
        } else if (type.equalsIgnoreCase("mi")) {
            gc.add(12, iQuantity);
        } else if (type.equalsIgnoreCase("s")) {
            gc.add(13, iQuantity);
        }

        return gc.getTime();
    }

    public String getDateChangeTime(String currentTime, String type, int iQuantity) {
        Date curr = stringToDate(currentTime);
        curr = this.getDateChangeTime(curr, type, iQuantity);
        return dateToString(curr);
    }

    public String getEndDate(String granularity, String statisticDate) {
        String beginDate = "";
        Date date = stringToDateShort(statisticDate);
        Date beginDateTemp = null;
        if (granularity.equals("1")) {
            beginDateTemp = date;
        }

        if (granularity.equals("2")) {
            beginDateTemp = this.getWeekEnd(date);
        }

        if (granularity.equals("3")) {
            beginDateTemp = this.getPeriodEnd(date);
        } else if (granularity.equals("4")) {
            beginDateTemp = this.getMonthEnd(date);
        } else if (granularity.equals("5")) {
            beginDateTemp = this.getSeasonEnd(date);
        } else if (granularity.equals("6")) {
            beginDateTemp = this.getHalfYearEnd(date);
        } else if (granularity.equals("7")) {
            beginDateTemp = this.getYearEnd(date);
        }

        beginDate = dateToStringShort(beginDateTemp);
        return beginDate;
    }

    public Date getHalfYearBegin(Date date) {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        String newDateStr = FormatDate(date, "yyyy") + "-";
        if (month <= 6) {
            newDateStr = newDateStr + "01-01";
        } else {
            newDateStr = newDateStr + "07-01";
        }

        return stringToDateShort(newDateStr);
    }

    public Date getHalfYearEnd(Date date) {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        String newDateStr = FormatDate(date, "yyyy") + "-";
        if (month <= 6) {
            newDateStr = newDateStr + "06-30";
        } else {
            newDateStr = newDateStr + "12-31";
        }

        return stringToDateShort(newDateStr);
    }

    public Date getMonthBegin(Date date) {
        String newDateStr = FormatDate(date, "yyyy-MM") + "-01";
        return stringToDateShort(newDateStr);
    }

    public Date getMonthEnd(Date date) {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        int day = Integer.parseInt(FormatDate(date, "dd"));
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day, 0, 0, 0);
        int monthLength = calendar.getActualMaximum(5);
        String newDateStr = FormatDate(date, "yyyy") + "-" + FormatDate(date, "MM") + "-";
        if (monthLength < 10) {
            newDateStr = newDateStr + "0" + monthLength;
        } else {
            newDateStr = newDateStr + monthLength;
        }

        return stringToDateShort(newDateStr);
    }

    public Date getPeriodBegin(Date date) {
        int days = Integer.parseInt(FormatDate(date, "dd"));
        String newDateStr = FormatDate(date, "yyyy-MM") + "-";
        if (days <= 10) {
            newDateStr = newDateStr + "01";
        } else if (days <= 20) {
            newDateStr = newDateStr + "11";
        } else {
            newDateStr = newDateStr + "21";
        }

        return stringToDateShort(newDateStr);
    }

    public Date getPeriodEnd(Date date) {
        int days = Integer.parseInt(FormatDate(date, "dd"));
        String newDateStr = FormatDate(date, "yyyy-MM") + "-";
        if (days <= 10) {
            newDateStr = newDateStr + "10";
        } else if (days <= 20) {
            newDateStr = newDateStr + "20";
        } else {
            newDateStr = FormatDate(this.getMonthEnd(date), "yyyy-MM-dd");
        }

        return stringToDateShort(newDateStr);
    }

    public Date getSeasonBegin(Date date) {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        String newDateStr = FormatDate(date, "yyyy") + "-";
        if (month >= 1 && month <= 3) {
            newDateStr = newDateStr + "01-01";
        } else if (month >= 4 && month <= 6) {
            newDateStr = newDateStr + "04-01";
        } else if (month >= 7 && month <= 9) {
            newDateStr = newDateStr + "07-01";
        } else if (month >= 10 && month <= 12) {
            newDateStr = newDateStr + "10-01";
        }

        return stringToDateShort(newDateStr);
    }

    public Date getSeasonEnd(Date date) {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        String newDateStr = FormatDate(date, "yyyy") + "-";
        if (month >= 1 && month <= 3) {
            newDateStr = newDateStr + "03-31";
        } else if (month >= 4 && month <= 6) {
            newDateStr = newDateStr + "06-30";
        } else if (month >= 7 && month <= 9) {
            newDateStr = newDateStr + "09-30";
        } else if (month >= 10 && month <= 12) {
            newDateStr = newDateStr + "12-31";
        }

        return stringToDateShort(newDateStr);
    }

    public String getTimedes(String granularity, String statisticDate) {
        String timedes = "";
        Date date = stringToDateShort(statisticDate);
        String year = "";
        String month = "01";
        String day = "01";
        year = getYear(date);
        month = getMonth(date);
        day = getDay(date);
        if (granularity.equals("1")) {
            timedes = statisticDate;
        } else if (granularity.equals("4")) {
            timedes = year + "年" + month + "月";
        } else if (granularity.equals("8")) {
            String quarter = month + "-" + day;
            if (quarter.equals("03-31")) {
                timedes = year + "年 第1季度";
            } else if (quarter.equals("06-30")) {
                timedes = year + "年 第2季度";
            } else if (quarter.equals("09-30")) {
                timedes = year + "年 第3季度";
            } else if (quarter.equals("12-31")) {
                timedes = year + "年 第4季度";
            }
        } else if (granularity.equals("32")) {
            timedes = year + "年";
        }

        return timedes;
    }

    public Date getWeekBegin(Date date) {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        --month;
        int day = Integer.parseInt(FormatDate(date, "dd"));
        GregorianCalendar gc = new GregorianCalendar(year, month, day);
        int week = 6;
        if (week == 0) {
            week = 7;
        }

        gc.add(5, 0 - week + 1);
        return gc.getTime();
    }

    public Date getWeekEnd(Date date) {
        int year = Integer.parseInt(FormatDate(date, "yyyy"));
        int month = Integer.parseInt(FormatDate(date, "MM"));
        --month;
        int day = Integer.parseInt(FormatDate(date, "dd"));
        GregorianCalendar gc = new GregorianCalendar(year, month, day);
        int week = 6;
        if (week == 0) {
            week = 7;
        }

        gc.add(5, 7 - week);
        return gc.getTime();
    }

    public Date getYearBegin(Date date) {
        String newDateStr = FormatDate(date, "yyyy") + "-01-01";
        return stringToDateShort(newDateStr);
    }

    public Date getYearEnd(Date date) {
        String newDateStr = FormatDate(date, "yyyy") + "-12-31";
        return stringToDateShort(newDateStr);
    }

    public boolean IsXperiodEnd(Date date) {
        boolean flag = false;
        String day = getDay(date);
        String month = getMonth(date);
        if (day.equalsIgnoreCase("10")) {
            flag = true;
        } else if (day.equalsIgnoreCase("20")) {
            flag = true;
        }

        return flag;
    }

    public static String addDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(5, n);
            return sdf.format(cd.getTime());
        } catch (Exception var4) {
            return null;
        }
    }

    public static String delDay(String s, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(s));
            cd.add(5, -n);
            return sdf.format(cd.getTime());
        } catch (Exception var4) {
            return null;
        }
    }
}
