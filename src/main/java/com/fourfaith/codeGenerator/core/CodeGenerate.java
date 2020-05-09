//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.codeGenerator.core;

import com.fourfaith.codeGenerator.database.JeecgReadTable;
import com.fourfaith.codeGenerator.generate.ICallBack;
import com.fourfaith.codeGenerator.pojo.Columnt;
import com.fourfaith.codeGenerator.util.CodeDateUtils;
import com.fourfaith.codeGenerator.util.NonceUtils;
import com.fourfaith.codeGenerator.util.def.FtlDef;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodeGenerate implements ICallBack {
    private static final Log log = LogFactory.getLog(CodeGenerate.class);
    private static String entityPackage = "test";
    private static String entityName = "Person";
    private static String tableName = "person";
    private static String ftlDescription = "公告";
    private static String primaryKeyPolicy = "uuid";
    private static String sequenceCode = "";
    private static String[] foreignKeys;
    private List<Columnt> originalColumns = new ArrayList();
    public static int FIELD_ROW_NUM = 1;
    private static CreateFileProperty createFileProperty = new CreateFileProperty();
    private List<Columnt> columns = new ArrayList();
    private JeecgReadTable dbFiledUtil = new JeecgReadTable();

    static {
        createFileProperty.setActionFlag(true);
        createFileProperty.setServiceIFlag(true);
        createFileProperty.setJspFlag(true);
        createFileProperty.setServiceImplFlag(true);
        createFileProperty.setJspMode("01");
        createFileProperty.setPageFlag(false);
        createFileProperty.setEntityFlag(true);
    }

    public CodeGenerate() {
    }

    public CodeGenerate(String entityPackage, String entityName, String tableName, String ftlDescription, CreateFileProperty createFileProperty, int fieldRowNum, String primaryKeyPolicy, String sequenceCode) {
        CodeGenerate.entityName = entityName;
        CodeGenerate.entityPackage = entityPackage;
        CodeGenerate.tableName = tableName;
        CodeGenerate.ftlDescription = ftlDescription;
        CodeGenerate.createFileProperty = createFileProperty;
        FIELD_ROW_NUM = fieldRowNum;
        CodeGenerate.primaryKeyPolicy = primaryKeyPolicy;
        CodeGenerate.sequenceCode = sequenceCode;
    }

    public CodeGenerate(String entityPackage, String entityName, String tableName, String ftlDescription, CreateFileProperty createFileProperty, String primaryKeyPolicy, String sequenceCode) {
        CodeGenerate.entityName = entityName;
        CodeGenerate.entityPackage = entityPackage;
        CodeGenerate.tableName = tableName;
        CodeGenerate.ftlDescription = ftlDescription;
        CodeGenerate.createFileProperty = createFileProperty;
        CodeGenerate.primaryKeyPolicy = primaryKeyPolicy;
        CodeGenerate.sequenceCode = sequenceCode;
    }

    public CodeGenerate(String entityPackage, String entityName, String tableName, String ftlDescription, CreateFileProperty createFileProperty, String primaryKeyPolicy, String sequenceCode, String[] foreignKeys) {
        CodeGenerate.entityName = entityName;
        CodeGenerate.entityPackage = entityPackage;
        CodeGenerate.tableName = tableName;
        CodeGenerate.ftlDescription = ftlDescription;
        CodeGenerate.createFileProperty = createFileProperty;
        CodeGenerate.primaryKeyPolicy = primaryKeyPolicy;
        CodeGenerate.sequenceCode = sequenceCode;
        CodeGenerate.foreignKeys = foreignKeys;
    }

    public Map<String, Object> execute() {
        Map data = new HashMap();
        data.put("bussiPackage", CodeResourceUtil.bussiPackage);
        data.put("entityPackage", entityPackage);
        data.put("entityName", entityName);
        data.put("tableName", tableName);
        data.put("ftl_description", ftlDescription);
        data.put(FtlDef.JEECG_TABLE_ID, CodeResourceUtil.JEECG_GENERATE_TABLE_ID);
        data.put(FtlDef.JEECG_PRIMARY_KEY_POLICY, primaryKeyPolicy);
        data.put(FtlDef.JEECG_SEQUENCE_CODE, sequenceCode);
        data.put("ftl_create_time", CodeDateUtils.dateToString(new Date()));
        data.put("foreignKeys", foreignKeys);
        data.put(FtlDef.FIELD_REQUIRED_NAME, StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM) ? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_REQUIRED_NUM) : -1);
        data.put(FtlDef.SEARCH_FIELD_NUM, StringUtils.isNotEmpty(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM) ? Integer.parseInt(CodeResourceUtil.JEECG_UI_FIELD_SEARCH_NUM) : -1);
        data.put(FtlDef.FIELD_ROW_NAME, FIELD_ROW_NUM);

        try {
            this.columns = this.dbFiledUtil.readTableColumn(tableName);
            data.put("columns", this.columns);
            this.originalColumns = this.dbFiledUtil.readOriginalTableColumn(tableName);
            data.put("originalColumns", this.originalColumns);
            Iterator var3 = this.originalColumns.iterator();

            while(var3.hasNext()) {
                Columnt c = (Columnt)var3.next();
                if (c.getFieldName().toLowerCase().equals(CodeResourceUtil.JEECG_GENERATE_TABLE_ID.toLowerCase())) {
                    data.put("primary_key_type", c.getFieldType());
                }
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            System.exit(-1);
        }

        long serialVersionUID = NonceUtils.randomLong() + NonceUtils.currentMills();
        data.put("serialVersionUID", String.valueOf(serialVersionUID));
        return data;
    }

    public void generateToFile() {
        log.info("----sixin---Code----Generation----[单表模型:" + tableName + "]------- 生成中。。。");
        CodeFactory codeFactory = new CodeFactory();
        codeFactory.setCallBack(new CodeGenerate());
        if (createFileProperty.isJspFlag()) {
            codeFactory.invoke("jspListTemplate.ftl", "list");
            codeFactory.invoke("jspAddTemplate.ftl", "add");
            codeFactory.invoke("jspEditTemplate.ftl", "edit");
        }

        if (createFileProperty.isServiceImplFlag()) {
            codeFactory.invoke("serviceImplTemplate.ftl", "ServiceImpl");
        }

        if (createFileProperty.isServiceIFlag()) {
            codeFactory.invoke("serviceTemplate.ftl", "Service");
        }

        if (createFileProperty.isActionFlag()) {
            codeFactory.invoke("controllerTemplate.ftl", "Controller");
        }

        if (createFileProperty.isEntityFlag()) {
            codeFactory.invoke("entityTemplate.ftl", "Entity");
        }

        if (createFileProperty.isDaoIflag()) {
            codeFactory.invoke("mapperTemplate.ftl", "Mapper");
        }

        createFileProperty.isDaoImplFlag();
        log.info("----sixin----Code----Generation-----[单表模型：" + tableName + "]------ 生成完成。。。");
    }

    public static void main(String[] args) {
        System.out.println("----sixin--------- Code------------- Generation -----[单表模型]------- 生成中。。。");
        (new CodeGenerate()).generateToFile();
        System.out.println("----sixin--------- Code------------- Generation -----[单表模型]------- 生成完成。。。");
    }
}
