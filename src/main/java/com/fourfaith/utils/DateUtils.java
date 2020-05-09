//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public DateUtils() {
    }

    public static Date StringToDate(String dateStr, String pattern) {
        Date date = null;

        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            date = df.parse(dateStr);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return date;
    }

    public static String DateToString(Date date, String pattern) {
        String dateStr = null;

        try {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            dateStr = df.format(date);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return dateStr;
    }

    public static Calendar DateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static Date setMinTimeForDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date setMaxTimeForDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(14, 998);
        return cal.getTime();
    }

    public static Date setMinTimeForMonthDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date setMaxTimeForMonthDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, cal.getActualMaximum(5));
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(14, 998);
        return cal.getTime();
    }

    public static Date setMinTimeForYearDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, 1);
        cal.set(2, 0);
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date setMaxTimeForYearDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(5, cal.getActualMaximum(5));
        cal.set(2, cal.getActualMaximum(2));
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(14, 998);
        return cal.getTime();
    }

    public static boolean isEquals(Date date1, Date date2) {
        boolean flag = false;
        if (DateToCalendar(date1).equals(DateToCalendar(date2))) {
            flag = true;
        }

        return flag;
    }

    public static boolean isMoreThan(Date date1, Date date2) {
        boolean flag = false;
        if (DateToCalendar(date1).after(DateToCalendar(date2))) {
            flag = true;
        }

        return flag;
    }

    public static boolean isLessThan(Date date1, Date date2) {
        boolean flag = false;
        if (DateToCalendar(date1).before(DateToCalendar(date2))) {
            flag = true;
        }

        return flag;
    }

    public static boolean isSameHour(Date date1, Date date2) {
        String pattern = "yyyyMMddHH";
        return DateToString(date1, pattern).equals(DateToString(date2, pattern));
    }

    public static boolean isSameDay(Date date1, Date date2) {
        String pattern = "yyyyMMdd";
        return DateToString(date1, pattern).equals(DateToString(date2, pattern));
    }

    public static boolean isSameMonth(Date date1, Date date2) {
        String pattern = "yyyyMM";
        return DateToString(date1, pattern).equals(DateToString(date2, pattern));
    }

    public static boolean isSameYear(Date date1, Date date2) {
        String pattern = "yyyy";
        return DateToString(date1, pattern).equals(DateToString(date2, pattern));
    }

    public static boolean in1Hours(Date beginDate, Date endDate) {
        boolean flag = false;
        Calendar cal1 = DateToCalendar(beginDate);
        Calendar cal2 = DateToCalendar(endDate);
        cal1.add(11, 1);
        if (cal1.after(cal2) || cal1.equals(cal2)) {
            flag = true;
        }

        return flag;
    }

    public static boolean in1Month(Date beginDate, Date endDate) {
        boolean flag = false;
        Calendar cal1 = DateToCalendar(beginDate);
        Calendar cal2 = DateToCalendar(endDate);
        cal1.add(2, 1);
        if (cal1.after(cal2) || cal1.equals(cal2)) {
            flag = true;
        }

        return flag;
    }

    public static boolean in1Year(Date beginDate, Date endDate) {
        boolean flag = false;
        Calendar cal1 = DateToCalendar(beginDate);
        Calendar cal2 = DateToCalendar(endDate);
        cal1.add(1, 1);
        if (cal1.after(cal2) || cal1.equals(cal2)) {
            flag = true;
        }

        return flag;
    }

    public static boolean in24Hours(Date beginDate, Date endDate) {
        boolean flag = false;
        Calendar cal1 = DateToCalendar(beginDate);
        Calendar cal2 = DateToCalendar(endDate);
        cal1.add(5, 1);
        if (cal1.after(cal2) || cal1.equals(cal2)) {
            flag = true;
        }

        return flag;
    }

    public static boolean isOnTheHour(Date date) {
        boolean flag = false;
        Calendar cal1 = DateToCalendar(date);
        if (cal1.get(12) == 0 && cal1.get(13) == 0) {
            flag = true;
        }

        return flag;
    }

    public static boolean isOnTheDay(Date date) {
        boolean flag = false;
        Calendar cal1 = DateToCalendar(date);
        if (cal1.get(11) == 0 && isOnTheHour(date)) {
            flag = true;
        }

        return flag;
    }

    public static Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, year);
        cal.set(2, month - 1);
        cal.set(5, day);
        cal.set(11, hour);
        cal.set(12, minute);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    public static Date set(Date date, int calendarField, int value) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(calendarField, value);
            return c.getTime();
        }
    }

    public static String getTimeByHour(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, calendar.get(11) - hour);
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(calendar.getTime());
    }
}
