package com.fourfaith.rongxin.utils;

import com.fourfaith.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlexUtil {

    private static String dateFormatalex = "yyyy-MM-dd HH:mm:ss";

    public static String formatDatealex(Date dt) {

        if (dt == null)
            return null;
        SimpleDateFormat format = new SimpleDateFormat(dateFormatalex);
        return format.format(dt);
    }

    public static String getTableName(Date date) {
        String tableName = "ST_AllDetails_Factor";
        if (date != null) {
            tableName = tableName + "_" + DateUtils.DateToString(date, "yyyyMM");
        }

        return tableName;
    }
}
