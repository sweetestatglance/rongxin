

package com.fourfaith.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class StringUtils {
    public StringUtils() {
    }

    public static boolean isNullOrEmpty(String str) {
        boolean flag = true;
        if (str != null && !str.trim().equals("")) {
            flag = false;
        }

        return flag;
    }

    public static String encryptMd5(String str) {
        String re_md5 = new String();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] b = md.digest();
            StringBuilder build = new StringBuilder("");

            for(int offset = 0; offset < b.length; ++offset) {
                int i = b[offset];
                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    build.append("0");
                }

                build.append(Integer.toHexString(i));
            }

            re_md5 = build.toString();
        } catch (NoSuchAlgorithmException var7) {
            var7.printStackTrace();
        }

        return re_md5;
    }

    public static String getRandomStr(int length, boolean onlyDigital) {
        char[] mapStr = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        char[] mapint = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String str = "";
        int i;
        int count;
        if (onlyDigital) {
            for(i = 0; i < length; ++i) {
                count = (int)((double)mapint.length * Math.random());
                if (count >= mapint.length) {
                    str = str + mapint[mapint.length - 1];
                } else {
                    str = str + mapint[count];
                }
            }
        } else {
            for(i = 0; i < length; ++i) {
                count = (int)((double)mapStr.length * Math.random());
                if (count >= mapStr.length) {
                    str = str + mapStr[mapStr.length - 1];
                } else {
                    str = str + mapStr[count];
                }
            }
        }

        return str;
    }

    public static String join(String split, List elementList) {
        StringBuffer result = new StringBuffer();
        if (elementList != null && elementList.size() > 0) {
            for(int i = 0; i < elementList.size(); ++i) {
                result.append(elementList.get(i));
                if (i != elementList.size() - 1) {
                    result.append(split);
                }
            }
        }

        return result.toString();
    }

    public static boolean contains(String container, String[] regex) {
        String[] var5 = regex;
        int var4 = regex.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            String str = var5[var3];
            if (container.contains(str)) {
                return true;
            }
        }

        return false;
    }

    public static boolean contains(String[] container, String regex) {
        String[] var5 = container;
        int var4 = container.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            String str = var5[var3];
            if (str.equals(regex)) {
                return true;
            }
        }

        return false;
    }

    public static String trimFirstAndLastChar(String source, char element, char endelement) {
        boolean beginIndexFlag = true;
        boolean endIndexFlag = true;

        do {
            int beginIndex = source.indexOf(element) == 0 ? 1 : 0;
            int endIndex = source.lastIndexOf(endelement) + 1 == source.length() ? source.lastIndexOf(endelement) : source.length();
            source = source.substring(beginIndex, endIndex);
            beginIndexFlag = source.indexOf(element) == 0;
            endIndexFlag = source.lastIndexOf(endelement) + 1 == source.length();
        } while(beginIndexFlag || endIndexFlag);

        return source;
    }
}
