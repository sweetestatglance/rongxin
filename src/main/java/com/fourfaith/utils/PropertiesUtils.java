//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {
    public static String PATH = "/web.properties";
    public static String SAFETYPATH = "/safety.properties";
    private static Properties properties;

    static {
        try {
            InputStream is = PropertiesUtils.class.getResourceAsStream(PATH);
            InputStream safety = PropertiesUtils.class.getResourceAsStream(SAFETYPATH);
            properties = new Properties();
            properties.load(is);
            properties.load(safety);
            if (is != null) {
                is.close();
            }
        } catch (Exception var2) {
            System.out.println(var2 + "file  not found");
        }

    }

    public PropertiesUtils() {
    }

    public static String getPara(String propertiesPath, String key) {
        return properties.getProperty(key);
    }

    public static String getPara(String key) {
        return properties.getProperty(key);
    }

    public static void main(String[] args) {
    }
}
