//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.codeGenerator.core;

import com.fourfaith.codeGenerator.generate.BaseCodeFactory;
import com.fourfaith.codeGenerator.generate.ICallBack;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class CodeFactory extends BaseCodeFactory {
    private ICallBack callBack;

    public CodeFactory() {
    }

    public void generateFile(String templateFileName, String type, Map data) {
        try {
            String entityPackage = "";
            String entityName = data.get("entityName").toString();
            String fileNamePath = this.getCodePath(type, entityPackage, entityName);
            String fileDir = StringUtils.substringBeforeLast(fileNamePath, "/");
            Template template = this.getConfiguration().getTemplate(templateFileName);
            FileUtils.forceMkdir(new File(fileDir + "/"));
            Writer out = new OutputStreamWriter(new FileOutputStream(fileNamePath), CodeResourceUtil.SYSTEM_ENCODING);
            template.process(data, out);
            out.close();
        } catch (TemplateException var10) {
            var10.printStackTrace();
        } catch (IOException var11) {
            var11.printStackTrace();
        }

    }

    public static String getProjectPath() {
        String path = System.getProperty("user.dir").replace("\\", "/") + "/";
        return path;
    }

    public String getClassPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("./").getPath();
        return path;
    }

    public static void main(String[] args) {
        System.out.println(getProjectPath());
    }

    public String getTemplatePath() {
        String path = this.getClassPath() + CodeResourceUtil.TEMPLATEPATH;
        return path;
    }

    public String getCodePath(String type, String entityPackage, String entityName) {
        String path = getProjectPath();
        StringBuilder str = new StringBuilder();
        if (!StringUtils.isNotBlank(type)) {
            throw new IllegalArgumentException("type is null");
        } else {
            str.append(path);
            if (!"list".equals(type) && !"add".equals(type) && !"edit".equals(type)) {
                str.append(CodeResourceUtil.CODEPATH);
            } else {
                str.append(CodeResourceUtil.JSPPATH);
            }

            if (!"Entity".equalsIgnoreCase(type)) {
                str.append("/");
                str.append(StringUtils.lowerCase(entityPackage));
                str.append("/");
            }

            if ("Controller".equalsIgnoreCase(type)) {
                str.append(StringUtils.lowerCase("controller/"));
            } else if ("ServiceImpl".equalsIgnoreCase(type)) {
                str.append(StringUtils.lowerCase("service/impl/"));
            } else if ("Service".equalsIgnoreCase(type)) {
                str.append(StringUtils.lowerCase("service/"));
            } else if ("Mapper".equalsIgnoreCase(type)) {
                str.append(StringUtils.lowerCase("dao/"));
            } else if ("DAOImpl".equalsIgnoreCase(type)) {
                str.append(StringUtils.lowerCase("dao/impl/"));
            } else if ("Entity".equalsIgnoreCase(type)) {
                str.append(StringUtils.lowerCase("model/"));
            } else {
                "xml".equalsIgnoreCase(type);
            }

            if (!"list".equals(type) && !"add".equals(type) && !"edit".equals(type)) {
                str.append(StringUtils.capitalize(entityName));
                if (!"Entity".equals(type)) {
                    str.append(type);
                }

                str.append(".java");
            } else {
                String jspName = StringUtils.capitalize(entityName);
                str.append("");
                str.append(type);
                str.append(".jsp");
            }

            return str.toString();
        }
    }

    public void invoke(String templateFileName, String type) {
        new HashMap();
        Map data = this.callBack.execute();
        this.generateFile(templateFileName, type, data);
    }

    public ICallBack getCallBack() {
        return this.callBack;
    }

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }
}
