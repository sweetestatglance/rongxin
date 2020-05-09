//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.codeGenerator.generate;

import com.fourfaith.codeGenerator.core.CodeResourceUtil;
import com.fourfaith.codeGenerator.util.CodeStringUtils;
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

public class CgformCodeFactory extends BaseCodeFactory {
    private ICallBack callBack;
    private String projectPath;

    public CgformCodeFactory() {
    }

    public void generateFile(String templateFileName, String type, Map data) throws TemplateException, IOException {
        try {
            String entityPackage = data.get("entityPackage").toString();
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
            throw var10;
        } catch (IOException var11) {
            var11.printStackTrace();
            throw var11;
        }
    }

    public String getProjectPath() {
        return this.projectPath;
    }

    public String getClassPath() {
        String path = this.getClass().getResource("/").getPath();
        return path;
    }

    public String getTemplatePath() {
        String path = this.getClassPath() + CodeResourceUtil.TEMPLATEPATH;
        return path;
    }

    public String getCodePath(String type, String entityPackage, String entityName) {
        String path = this.getProjectPath();
        StringBuilder str = new StringBuilder();
        if (!StringUtils.isNotBlank(type)) {
            throw new IllegalArgumentException("type is null");
        } else {
            String codeType = ((CodeType)Enum.valueOf(CodeType.class, type)).getValue();
            str.append(path);
            if (!"jsp".equals(type) && !"jspList".equals(type) && !"js".equals(type) && !"jsList".equals(type) && !"jsp_add".equals(type) && !"jsp_update".equals(type)) {
                str.append(CodeResourceUtil.CODEPATH);
            } else {
                str.append(CodeResourceUtil.JSPPATH);
            }

            if ("Action".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase("action"));
            } else if ("ServiceImpl".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase("service/impl"));
            } else if ("ServiceI".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase("service"));
            } else if (!"List".equalsIgnoreCase(codeType)) {
                str.append(StringUtils.lowerCase(codeType));
            }

            str.append("/");
            str.append(StringUtils.lowerCase(entityPackage));
            str.append("/");
            String jsName;
            if (!"jsp".equals(type) && !"jspList".equals(type)) {
                if (!"jsp_add".equals(type) && !"jspList_add".equals(type)) {
                    if (!"jsp_update".equals(type) && !"jspList_update".equals(type)) {
                        if (!"js".equals(type) && !"jsList".equals(type)) {
                            str.append(StringUtils.capitalize(entityName));
                            str.append(codeType);
                            str.append(".java");
                        } else {
                            jsName = StringUtils.capitalize(entityName);
                            str.append(CodeStringUtils.getInitialSmall(jsName));
                            str.append(codeType);
                            str.append(".js");
                        }
                    } else {
                        jsName = StringUtils.capitalize(entityName);
                        str.append(CodeStringUtils.getInitialSmall(jsName));
                        str.append(codeType);
                        str.append("-update.jsp");
                    }
                } else {
                    jsName = StringUtils.capitalize(entityName);
                    str.append(CodeStringUtils.getInitialSmall(jsName));
                    str.append(codeType);
                    str.append("-add.jsp");
                }
            } else {
                jsName = StringUtils.capitalize(entityName);
                str.append(CodeStringUtils.getInitialSmall(jsName));
                str.append(codeType);
                str.append(".jsp");
            }

            return str.toString();
        }
    }

    public void invoke(String templateFileName, String type) throws TemplateException, IOException {
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

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    public enum CodeType {
        ;

        public String getValue() {
            return null;
        }
    }
}
