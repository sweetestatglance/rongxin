//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.codeGenerator.util;

import java.util.List;
import org.apache.commons.lang.StringUtils;

public class CodeStringUtils {
    public CodeStringUtils() {
    }

    public static String getStringSplit(String[] val) {
        StringBuffer sqlStr = new StringBuffer();
        String[] arrayOfString = val;
        int j = val.length;

        for(int i = 0; i < j; ++i) {
            String s = arrayOfString[i];
            if (StringUtils.isNotBlank(s)) {
                sqlStr.append(",");
                sqlStr.append("'");
                sqlStr.append(s.trim());
                sqlStr.append("'");
            }
        }

        return sqlStr.toString().substring(1);
    }

    public static String getInitialSmall(String str) {
        if (StringUtils.isNotBlank(str)) {
            str = str.substring(0, 1).toLowerCase() + str.substring(1);
        }

        return str;
    }

    public static Integer getIntegerNotNull(Integer t) {
        return t == null ? 0 : t;
    }

    public static boolean isIn(String substring, String[] source) {
        if (source != null && source.length != 0) {
            for(int i = 0; i < source.length; ++i) {
                String aSource = source[i];
                if (aSource.equals(substring)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static boolean isIn(String substring, List<String> ls) {
        String[] source = new String[0];
        if (ls != null) {
            source = (String[])ls.toArray();
        }

        if (source != null && source.length != 0) {
            for(int i = 0; i < source.length; ++i) {
                String aSource = source[i];
                if (aSource.equals(substring)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }
}
