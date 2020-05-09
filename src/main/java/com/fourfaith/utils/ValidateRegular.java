//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateRegular {
    public ValidateRegular() {
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher match = p.matcher(mobiles);
        return match.matches();
    }

    public static boolean isMaxNum(String num) {
        Pattern p = Pattern.compile("^[0-9]*$");
        Matcher match = p.matcher(num);
        if (match.matches()) {
            return num.length() <= 9;
        } else {
            return match.matches();
        }
    }

    public static boolean centerVal(String num) {
        Pattern p = Pattern.compile("^\\+?[1-9][0-9]*$");
        Matcher match = p.matcher(num);
        if (match.matches()) {
            return Integer.parseInt(num) <= 255;
        } else {
            return match.matches();
        }
    }

    public static void main(String[] args) {
        System.out.print(centerVal("-1"));
    }
}
