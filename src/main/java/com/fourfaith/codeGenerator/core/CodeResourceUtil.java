//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.codeGenerator.core;

import java.util.ResourceBundle;

public class CodeResourceUtil {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("sixin_database");
    private static final ResourceBundle bundlePath = ResourceBundle.getBundle("sixin_config");
    public static String DIVER_NAME = "com.mysql.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/sys?useUnicode=true&characterEncoding=UTF-8";
    public static String USERNAME = "root";
    public static String PASSWORD = "root";
    public static String DATABASE_NAME = "sys";
    public static String DATABASE_TYPE = "mysql";
    public static String JEECG_UI_FIELD_REQUIRED_NUM = "4";
    public static String JEECG_UI_FIELD_SEARCH_NUM = "3";
    public static String project_path = "c:/workspace/jeecg";
    public static String web_root_package = "WebRoot";
    public static String source_root_package = "src";
    public static String bussiPackage = "sun";
    public static String entity_package = "entity";
    public static String page_package = "page";
    public static boolean JEECG_FILED_CONVERT = true;
    public static String FREEMARKER_CLASSPATH = "../template";
    public static String ENTITY_URL;
    public static String PAGE_URL;
    public static String ENTITY_URL_INX;
    public static String PAGE_URL_INX;
    public static String TEMPLATEPATH;
    public static String CODEPATH;
    public static String JSPPATH;
    public static String JEECG_GENERATE_TABLE_ID;
    public static String JEECG_GENERATE_UI_FILTER_FIELDS;
    public static String SYSTEM_ENCODING;

    static {
        DIVER_NAME = getDIVER_NAME();
        URL = getURL();
        USERNAME = getUSERNAME();
        PASSWORD = getPASSWORD();
        DATABASE_NAME = getDATABASE_NAME();
        JEECG_FILED_CONVERT = getJEECG_FILED_CONVERT();
        SYSTEM_ENCODING = getSYSTEM_ENCODING();
        TEMPLATEPATH = getTEMPLATEPATH();
        source_root_package = getSourceRootPackage();
        web_root_package = getWebRootPackage();
        bussiPackage = getBussiPackage();
        JEECG_GENERATE_TABLE_ID = getJeecg_generate_table_id();
        JEECG_GENERATE_UI_FILTER_FIELDS = getJeecg_generate_ui_filter_fields();
        JEECG_UI_FIELD_SEARCH_NUM = getJeecg_ui_search_filed_num();
        if (URL.indexOf("mysql") < 0 && URL.indexOf("MYSQL") < 0) {
            if (URL.indexOf("oracle") < 0 && URL.indexOf("ORACLE") < 0) {
                if (URL.indexOf("postgresql") < 0 && URL.indexOf("POSTGRESQL") < 0) {
                    if (URL.indexOf("sqlserver") >= 0 || URL.indexOf("sqlserver") >= 0) {
                        DATABASE_TYPE = "sqlserver";
                    }
                } else {
                    DATABASE_TYPE = "postgresql";
                }
            } else {
                DATABASE_TYPE = "oracle";
            }
        } else {
            DATABASE_TYPE = "mysql";
        }

        source_root_package = source_root_package.replace(".", "/");
        web_root_package = web_root_package.replace(".", "/");
        String bussiPackageUrl = bussiPackage.replace(".", "/");
        String bussiPackageUrl1 = CodeWindow.entityPackage;
        ENTITY_URL = source_root_package + "/" + bussiPackageUrl + "/" + entity_package + "/";
        PAGE_URL = source_root_package + "/" + bussiPackageUrl + "/" + page_package + "/";
        ENTITY_URL_INX = bussiPackage + "." + entity_package + ".";
        PAGE_URL_INX = bussiPackage + "." + page_package + ".";
        CODEPATH = source_root_package + "/" + bussiPackageUrl + "/";
        JSPPATH = web_root_package + "/" + "WebRoot" + "/" + bussiPackageUrl1 + "/";
    }

    public CodeResourceUtil() {
    }

    private void ResourceUtil() {
    }

    public static final String getDIVER_NAME() {
        return bundle.getString("diver_name");
    }

    public static final String getURL() {
        System.out.println(bundle.getString("url"));
        return bundle.getString("url");
    }

    public static final String getUSERNAME() {
        return bundle.getString("username");
    }

    public static final String getPASSWORD() {
        return bundle.getString("password");
    }

    public static final String getDATABASE_NAME() {
        return bundle.getString("database_name");
    }

    public static final boolean getJEECG_FILED_CONVERT() {
        String s = bundlePath.getString("sixin_filed_convert");
        return !s.toString().equals("false");
    }

    private static String getBussiPackage() {
        return bundlePath.getString("bussi_package");
    }

    public static final String getEntityPackage() {
        return bundlePath.getString("entity_package");
    }

    public static final String getPagePackage() {
        return bundlePath.getString("page_package");
    }

    public static final String getTEMPLATEPATH() {
        return bundlePath.getString("templatepath");
    }

    public static final String getSourceRootPackage() {
        return bundlePath.getString("source_root_package");
    }

    public static final String getWebRootPackage() {
        return bundlePath.getString("webroot_package");
    }

    public static final String getSYSTEM_ENCODING() {
        return bundlePath.getString("system_encoding");
    }

    public static final String getJeecg_generate_table_id() {
        return bundlePath.getString("sixin_generate_table_id");
    }

    public static final String getJeecg_generate_ui_filter_fields() {
        return bundlePath.getString("ui_filter_fields");
    }

    public static final String getJeecg_ui_search_filed_num() {
        return bundlePath.getString("sixin_ui_search_filed_num");
    }

    public static final String getJeecg_ui_field_required_num() {
        return bundlePath.getString("jeecg_ui_field_required_num");
    }

    public static String getProject_path() {
        String projp = bundlePath.getString("project_path");
        if (projp != null && !"".equals(projp)) {
            project_path = projp;
        }

        return project_path;
    }

    public static void setProject_path(String project_path) {
    }
}
