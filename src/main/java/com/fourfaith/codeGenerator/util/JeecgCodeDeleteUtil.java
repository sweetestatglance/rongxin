//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.codeGenerator.util;

import com.fourfaith.codeGenerator.core.CodeResourceUtil;
import java.io.File;

public class JeecgCodeDeleteUtil {
    private static String buss_package;
    private static final String src_url;
    private static final String web_url;
    private static final String action_inx = "action";
    private static final String entity_inx = "entity";
    private static final String page_inx = "page";
    private static final String service_inx = "service";
    private static final String impl_inx = "impl";
    private static String action_url;
    private static String entity_url;
    private static String page_url;
    private static String service_url;
    private static String service_impl_url;
    private static String jsp_url;
    private static String jsp_add_url;
    private static String jsp_edit_url;

    static {
        buss_package = CodeResourceUtil.bussiPackage;
        src_url = "src/" + buss_package;
        web_url = "WebRoot/" + buss_package;
    }

    public JeecgCodeDeleteUtil() {
    }

    public static void init(String gn_package, String name) {
        action_url = src_url + "/" + "action" + "/" + gn_package + "/" + name + "Action.java";
        entity_url = src_url + "/" + "entity" + "/" + gn_package + "/" + name + "Entity.java";
        page_url = src_url + "/" + "page" + "/" + gn_package + "/" + name + "Page.java";
        service_url = src_url + "/" + "service" + "/" + gn_package + "/" + name + "ServiceI.java";
        service_impl_url = src_url + "/" + "service" + "/" + "impl" + "/" + gn_package + "/" + name + "ServiceImpl.java";
        jsp_url = web_url + "/" + gn_package + "/" + name + ".jsp";
        jsp_add_url = web_url + "/" + gn_package + "/" + name + "-main-add.jsp";
        jsp_edit_url = web_url + "/" + gn_package + "/" + name + "-main-edit.jsp";
        String path = getProjectPath();
        action_url = path + "/" + action_url;
        entity_url = path + "/" + entity_url;
        page_url = path + "/" + page_url;
        service_url = path + "/" + service_url;
        service_impl_url = path + "/" + service_impl_url;
        jsp_url = path + "/" + jsp_url;
        jsp_add_url = path + "/" + jsp_add_url;
        jsp_edit_url = path + "/" + jsp_edit_url;
    }

    public static void main(String[] args) {
        String name = "Company";
        String subPackage = "test";
        delCode(subPackage, name);
    }

    public static void delCode(String subPackage, String codeName) {
        init(subPackage, codeName);
        delete(action_url);
        delete(entity_url);
        delete(page_url);
        delete(service_url);
        delete(service_impl_url);
        delete(jsp_edit_url);
        delete(jsp_url);
        delete(jsp_add_url);
        System.out.println("--------------------删除动作结束-----------------------");
    }

    public static String getProjectPath() {
        String path = System.getProperty("user.dir").replace("\\", "/") + "/";
        return path;
    }

    public static boolean delete(String strFileName) {
        File fileDelete = new File(strFileName);
        if (fileDelete.exists() && fileDelete.isFile()) {
            System.out.println("--------成功删除文件---------" + strFileName);
            return fileDelete.delete();
        } else {
            return false;
        }
    }
}
