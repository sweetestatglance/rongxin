//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.codeGenerator.generate;

import com.fourfaith.codeGenerator.core.CreateFileProperty;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CgformCodeGenerate implements ICallBack {
    private static final Log log = LogFactory.getLog(CgformCodeGenerate.class);
    private String entityPackage = "test";
    private String entityName = "Person";
    private String tableName = "person";
    private String ftlDescription = "公告";
    private String primaryKeyPolicy = "uuid";
    private String sequenceCode = "";
    private String[] foreignKeys;
    public static int FIELD_ROW_NUM = 1;
    private CreateFileProperty subFileProperty;
    private String policy;
    private String[] array;
    private static CreateFileProperty createFileProperty = new CreateFileProperty();

    static {
        createFileProperty.setActionFlag(true);
        createFileProperty.setServiceIFlag(true);
        createFileProperty.setJspFlag(true);
        createFileProperty.setServiceImplFlag(true);
        createFileProperty.setJspMode("01");
        createFileProperty.setPageFlag(true);
        createFileProperty.setEntityFlag(true);
    }

    public CgformCodeGenerate() {
    }

    public Map<String, Object> execute() {
        return null;
    }
}
