//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.fourfaith.codeGenerator.database;

import com.fourfaith.codeGenerator.core.CodeResourceUtil;
import com.fourfaith.codeGenerator.pojo.Columnt;
import com.fourfaith.codeGenerator.pojo.TableConvert;
import com.fourfaith.codeGenerator.util.CodeStringUtils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JeecgReadTable {
    private static final Log log = LogFactory.getLog("com/fourfaith/codeGenerator/database/JeecgReadTable");
    private static final long serialVersionUID = -5324160085184088010L;
    private Connection conn;
    private Statement stmt;
    private String sql;
    private ResultSet rs;

    public JeecgReadTable() {
    }

    public static void main(String[] args) throws SQLException {
        try {
            List cls = (new JeecgReadTable()).readTableColumn("person");
            Iterator iterator = cls.iterator();

            while(iterator.hasNext()) {
                Columnt c = (Columnt)iterator.next();
                System.out.println(c.getFieldName());
            }
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        System.out.println(ArrayUtils.toString((new JeecgReadTable()).readAllTableNames()));
    }

    public List readAllTableNames() throws SQLException {
        ArrayList tableNames = new ArrayList(0);

        try {
            Class.forName(CodeResourceUtil.DIVER_NAME);
            this.conn = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME, CodeResourceUtil.PASSWORD);
            this.stmt = this.conn.createStatement(1005, 1007);
            if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
                this.sql = MessageFormat.format("select distinct table_name from information_schema.columns where table_schema = {0}", TableConvert.getV(CodeResourceUtil.DATABASE_NAME));
            }

            if (CodeResourceUtil.DATABASE_TYPE.equals("oracle")) {
                this.sql = " select distinct colstable.table_name as  table_name from user_tab_cols colstable";
            }

            if (CodeResourceUtil.DATABASE_TYPE.equals("postgresql")) {
                this.sql = "SELECT distinct c.relname AS  table_name FROM pg_class c";
            }

            if (CodeResourceUtil.DATABASE_TYPE.equals("sqlserver")) {
                this.sql = "select distinct c.name as  table_name from sys.objects c ";
            }

            this.rs = this.stmt.executeQuery(this.sql);

            while(this.rs.next()) {
                String tableName = this.rs.getString(1);
                tableNames.add(tableName);
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        try {
            if (this.stmt != null) {
                this.stmt.close();
                this.stmt = null;
                System.gc();
            }

            if (this.conn != null) {
                this.conn.close();
                this.conn = null;
                System.gc();
            }
        } catch (SQLException var4) {
            throw var4;
        }

        try {
            if (this.stmt != null) {
                this.stmt.close();
                this.stmt = null;
                System.gc();
            }

            if (this.conn != null) {
                this.conn.close();
                this.conn = null;
                System.gc();
            }

            return tableNames;
        } catch (SQLException var3) {
            throw var3;
        }
    }

    public List readTableColumn(String tableName) throws Exception {
        ArrayList columntList = new ArrayList();

        Columnt ch;
        try {
            Class.forName(CodeResourceUtil.DIVER_NAME);
            this.conn = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME, CodeResourceUtil.PASSWORD);
            this.stmt = this.conn.createStatement(1005, 1007);
            if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
                this.sql = MessageFormat.format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}", TableConvert.getV(tableName.toUpperCase()), TableConvert.getV(CodeResourceUtil.DATABASE_NAME));
            }

            if (CodeResourceUtil.DATABASE_TYPE.equals("oracle")) {
                this.sql = MessageFormat.format(" select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}", TableConvert.getV(tableName.toUpperCase()));
            }

            if (CodeResourceUtil.DATABASE_TYPE.equals("postgresql")) {
                this.sql = MessageFormat.format("SELECT a.attname AS  field,t.typname AS type,col_description(a.attrelid,a.attnum) as comment,null as column_precision,null as column_scale,null as Char_Length,a.attnotnull  FROM pg_class c,pg_attribute  a,pg_type t  WHERE c.relname = {0} and a.attnum > 0  and a.attrelid = c.oid and a.atttypid = t.oid  ORDER BY a.attnum ", TableConvert.getV(tableName.toLowerCase()));
            }

            if (CodeResourceUtil.DATABASE_TYPE.equals("sqlserver")) {
                this.sql = MessageFormat.format("select cast(a.name as varchar(50)) column_name,  cast(b.name as varchar(50)) data_type,  cast(e.value as varchar(200)) comment,  cast(ColumnProperty(a.object_id,a.Name,'''Precision''') as int) num_precision,  cast(ColumnProperty(a.object_id,a.Name,'''Scale''') as int) num_scale,  a.max_length,  (case when a.is_nullable=1 then '''y''' else '''n''' end) nullable   from sys.columns a left join sys.types b on a.user_type_id=b.user_type_id left join sys.objects c on a.object_id=c.object_id and c.type='''U''' left join sys.extended_properties e on e.major_id=c.object_id and e.minor_id=a.column_id and e.class=1 where c.name={0}", TableConvert.getV(tableName.toLowerCase()));
            }

            this.rs = this.stmt.executeQuery(this.sql);
            this.rs.last();
            int fieldNum = this.rs.getRow();
            if (fieldNum <= 0) {
                throw new Exception("该表不存在或者表中没有字段");
            }

            ch = new Columnt();
            if (CodeResourceUtil.JEECG_FILED_CONVERT) {
                ch.setFieldName(formatField(this.rs.getString(1).toLowerCase()));
            } else {
                ch.setFieldName(this.rs.getString(1).toLowerCase());
            }

            ch.setFieldDbName(this.rs.getString(1).toUpperCase());
            ch.setFieldType(formatField(this.rs.getString(2).toLowerCase()));
            ch.setPrecision(this.rs.getString(4));
            ch.setScale(this.rs.getString(5));
            ch.setCharmaxLength(this.rs.getString(6));
            ch.setNullable(TableConvert.getNullAble(this.rs.getString(7)));
            this.formatFieldClassType(ch);
            ch.setFiledComment(StringUtils.isBlank(this.rs.getString(3)) ? ch.getFieldName() : this.rs.getString(3));
            String[] ui_filter_fields = new String[0];
            if (CodeResourceUtil.JEECG_GENERATE_UI_FILTER_FIELDS != null) {
                ui_filter_fields = CodeResourceUtil.JEECG_GENERATE_UI_FILTER_FIELDS.toLowerCase().split(",");
            }

            if (!CodeResourceUtil.JEECG_GENERATE_TABLE_ID.equals(ch.getFieldName()) && !CodeStringUtils.isIn(ch.getFieldDbName().toLowerCase(), ui_filter_fields)) {
                columntList.add(ch);
            }

            while(this.rs.previous()) {
                Columnt po = new Columnt();
                if (CodeResourceUtil.JEECG_FILED_CONVERT) {
                    po.setFieldName(formatField(this.rs.getString(1).toLowerCase()));
                } else {
                    po.setFieldName(this.rs.getString(1).toLowerCase());
                }

                po.setFieldDbName(this.rs.getString(1).toUpperCase());
                if (!CodeResourceUtil.JEECG_GENERATE_TABLE_ID.equals(po.getFieldName()) && !CodeStringUtils.isIn(po.getFieldDbName().toLowerCase(), ui_filter_fields)) {
                    po.setFieldType(formatField(this.rs.getString(2).toLowerCase()));
                    po.setPrecision(this.rs.getString(4));
                    po.setScale(this.rs.getString(5));
                    po.setCharmaxLength(this.rs.getString(6));
                    po.setNullable(TableConvert.getNullAble(this.rs.getString(7)));
                    this.formatFieldClassType(po);
                    po.setFiledComment(StringUtils.isBlank(this.rs.getString(3)) ? po.getFieldName() : this.rs.getString(3));
                    columntList.add(po);
                }
            }
        } catch (ClassNotFoundException var9) {
            throw var9;
        } catch (SQLException var10) {
            throw var10;
        }

        try {
            if (this.stmt != null) {
                this.stmt.close();
                this.stmt = null;
                System.gc();
            }

            if (this.conn != null) {
                this.conn.close();
                this.conn = null;
                System.gc();
            }
        } catch (SQLException var8) {
            throw var8;
        }

        List rsList = new ArrayList();

        for(int i = columntList.size() - 1; i >= 0; --i) {
            ch = (Columnt)columntList.get(i);
            rsList.add(ch);
        }

        return rsList;
    }

    public List readOriginalTableColumn(String tableName) throws Exception {
        ArrayList columntList = new ArrayList();

        Columnt ch;
        try {
            Class.forName(CodeResourceUtil.DIVER_NAME);
            this.conn = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME, CodeResourceUtil.PASSWORD);
            this.stmt = this.conn.createStatement(1005, 1007);
            if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
                this.sql = MessageFormat.format("select column_name,data_type,column_comment,numeric_precision,numeric_scale,character_maximum_length,is_nullable nullable from information_schema.columns where table_name = {0} and table_schema = {1}", TableConvert.getV(tableName.toUpperCase()), TableConvert.getV(CodeResourceUtil.DATABASE_NAME));
            }

            if (CodeResourceUtil.DATABASE_TYPE.equals("oracle")) {
                this.sql = MessageFormat.format(" select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment, colstable.Data_Precision column_precision, colstable.Data_Scale column_scale,colstable.Char_Length,colstable.nullable from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = {0}", TableConvert.getV(tableName.toUpperCase()));
            }

            if (CodeResourceUtil.DATABASE_TYPE.equals("postgresql")) {
                this.sql = MessageFormat.format("SELECT a.attname AS  field,t.typname AS type,col_description(a.attrelid,a.attnum) as comment,null as column_precision,null as column_scale,null as Char_Length,a.attnotnull  FROM pg_class c,pg_attribute  a,pg_type t  WHERE c.relname = {0} and a.attnum > 0  and a.attrelid = c.oid and a.atttypid = t.oid  ORDER BY a.attnum ", TableConvert.getV(tableName.toLowerCase()));
            }

            if (CodeResourceUtil.DATABASE_TYPE.equals("sqlserver")) {
                this.sql = MessageFormat.format("select cast(a.name as varchar(50)) column_name,  cast(b.name as varchar(50)) data_type,  cast(e.value as varchar(200)) comment,  cast(ColumnProperty(a.object_id,a.Name,'''Precision''') as int) num_precision,  cast(ColumnProperty(a.object_id,a.Name,'''Scale''') as int) num_scale,  a.max_length,  (case when a.is_nullable=1 then '''y''' else '''n''' end) nullable   from sys.columns a left join sys.types b on a.user_type_id=b.user_type_id left join sys.objects c on a.object_id=c.object_id and c.type='''U''' left join sys.extended_properties e on e.major_id=c.object_id and e.minor_id=a.column_id and e.class=1 where c.name={0}", TableConvert.getV(tableName.toLowerCase()));
            }

            this.rs = this.stmt.executeQuery(this.sql);
            this.rs.last();
            int fieldNum = this.rs.getRow();
            if (fieldNum <= 0) {
                throw new Exception("该表不存在或者表中没有字段");
            }

            ch = new Columnt();
            if (CodeResourceUtil.JEECG_FILED_CONVERT) {
                ch.setFieldName(formatField(this.rs.getString(1).toLowerCase()));
            } else {
                ch.setFieldName(this.rs.getString(1).toLowerCase());
            }

            ch.setFieldDbName(this.rs.getString(1).toUpperCase());
            ch.setPrecision(TableConvert.getNullString(this.rs.getString(4)));
            ch.setScale(TableConvert.getNullString(this.rs.getString(5)));
            ch.setCharmaxLength(TableConvert.getNullString(this.rs.getString(6)));
            ch.setNullable(TableConvert.getNullAble(this.rs.getString(7)));
            ch.setFieldType(this.formatDataType(this.rs.getString(2).toLowerCase(), ch.getPrecision(), ch.getScale()));
            this.formatFieldClassType(ch);
            ch.setFiledComment(StringUtils.isBlank(this.rs.getString(3)) ? ch.getFieldName() : this.rs.getString(3));
            columntList.add(ch);

            while(this.rs.previous()) {
                Columnt po = new Columnt();
                if (CodeResourceUtil.JEECG_FILED_CONVERT) {
                    po.setFieldName(formatField(this.rs.getString(1).toLowerCase()));
                } else {
                    po.setFieldName(this.rs.getString(1).toLowerCase());
                }

                po.setFieldDbName(this.rs.getString(1).toUpperCase());
                po.setPrecision(TableConvert.getNullString(this.rs.getString(4)));
                po.setScale(TableConvert.getNullString(this.rs.getString(5)));
                po.setCharmaxLength(TableConvert.getNullString(this.rs.getString(6)));
                po.setNullable(TableConvert.getNullAble(this.rs.getString(7)));
                po.setFieldType(this.formatDataType(this.rs.getString(2).toLowerCase(), po.getPrecision(), po.getScale()));
                this.formatFieldClassType(po);
                po.setFiledComment(StringUtils.isBlank(this.rs.getString(3)) ? po.getFieldName() : this.rs.getString(3));
                columntList.add(po);
            }
        } catch (ClassNotFoundException var8) {
            throw var8;
        } catch (SQLException var9) {
            throw var9;
        }

        try {
            if (this.stmt != null) {
                this.stmt.close();
                this.stmt = null;
                System.gc();
            }

            if (this.conn != null) {
                this.conn.close();
                this.conn = null;
                System.gc();
            }
        } catch (SQLException var7) {
            throw var7;
        }

        List rsList = new ArrayList();

        for(int i = columntList.size() - 1; i >= 0; --i) {
            ch = (Columnt)columntList.get(i);
            rsList.add(ch);
        }

        return rsList;
    }

    public static String formatField(String field) {
        String[] strs = field.split("_");
        field = "";
        int m = 0;

        for(int length = strs.length; m < length; ++m) {
            if (m > 0) {
                String tempStr = strs[m].toLowerCase();
                tempStr = tempStr.substring(0, 1).toUpperCase() + tempStr.substring(1, tempStr.length());
                field = field + tempStr;
            } else {
                field = field + strs[m].toLowerCase();
            }
        }

        return field;
    }

    public static String formatFieldCapital(String field) {
        String[] strs = field.split("_");
        field = "";
        int m = 0;

        for(int length = strs.length; m < length; ++m) {
            if (m > 0) {
                String tempStr = strs[m].toLowerCase();
                tempStr = tempStr.substring(0, 1).toUpperCase() + tempStr.substring(1, tempStr.length());
                field = field + tempStr;
            } else {
                field = field + strs[m].toLowerCase();
            }
        }

        field = field.substring(0, 1).toUpperCase() + field.substring(1);
        return field;
    }

    public boolean checkTableExist(String tableName) {
        int fieldNum = 0;
        System.out.println("数据库驱动: " + CodeResourceUtil.DIVER_NAME);

        try {
            Class.forName(CodeResourceUtil.DIVER_NAME);
        } catch (ClassNotFoundException var9) {
            var9.printStackTrace();
        }

        try {
            this.conn = DriverManager.getConnection(CodeResourceUtil.URL, CodeResourceUtil.USERNAME, CodeResourceUtil.PASSWORD);
        } catch (SQLException var8) {
            var8.printStackTrace();
        }

        try {
            this.stmt = this.conn.createStatement(1005, 1007);
        } catch (SQLException var7) {
            var7.printStackTrace();
        }

        if (CodeResourceUtil.DATABASE_TYPE.equals("mysql")) {
            this.sql = "select column_name,data_type,column_comment,0,0 from information_schema.columns where table_name = '" + tableName.toUpperCase() + "'" + " and table_schema = '" + CodeResourceUtil.DATABASE_NAME + "'";
        }

        if (CodeResourceUtil.DATABASE_TYPE.equals("oracle")) {
            this.sql = "select colstable.column_name column_name, colstable.data_type data_type, commentstable.comments column_comment from user_tab_cols colstable  inner join user_col_comments commentstable  on colstable.column_name = commentstable.column_name  where colstable.table_name = commentstable.table_name  and colstable.table_name = '" + tableName.toUpperCase() + "'";
        }

        if (CodeResourceUtil.DATABASE_TYPE.equals("postgresql")) {
            this.sql = MessageFormat.format("SELECT a.attname AS  field,t.typname AS type,col_description(a.attrelid,a.attnum) as comment,null as column_precision,null as column_scale,null as Char_Length,a.attnotnull  FROM pg_class c,pg_attribute  a,pg_type t  WHERE c.relname = {0} and a.attnum > 0  and a.attrelid = c.oid and a.atttypid = t.oid  ORDER BY a.attnum ", TableConvert.getV(tableName.toLowerCase()));
        }

        if (CodeResourceUtil.DATABASE_TYPE.equals("sqlserver")) {
            this.sql = MessageFormat.format("select cast(a.name as varchar(50)) column_name,  cast(b.name as varchar(50)) data_type,  cast(e.value as varchar(200)) comment,  cast(ColumnProperty(a.object_id,a.Name,'''Precision''') as int) num_precision,  cast(ColumnProperty(a.object_id,a.Name,'''Scale''') as int) num_scale,  a.max_length,  (case when a.is_nullable=1 then '''y''' else '''n''' end) nullable   from sys.columns a left join sys.types b on a.user_type_id=b.user_type_id left join sys.objects c on a.object_id=c.object_id and c.type='''U''' left join sys.extended_properties e on e.major_id=c.object_id and e.minor_id=a.column_id and e.class=1 where c.name={0}", TableConvert.getV(tableName.toLowerCase()));
        }

        try {
            this.rs = this.stmt.executeQuery(this.sql);
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        try {
            this.rs.last();
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

        try {
            fieldNum = this.rs.getRow();
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return fieldNum > 0;
    }

    private void formatFieldClassType(Columnt columnt) {
        String fieldType = columnt.getFieldType();
        String scale = columnt.getScale();
        columnt.setClassType("inputxt");
        if ("N".equals(columnt.getNullable())) {
            columnt.setOptionType("*");
        }

        if (!"datetime".equals(fieldType) && !fieldType.contains("time")) {
            if ("date".equals(fieldType)) {
                columnt.setClassType("easyui-datebox");
            } else if (fieldType.contains("int")) {
                columnt.setOptionType("n");
            } else if ("number".equals(fieldType)) {
                if (StringUtils.isNotBlank(scale) && Integer.parseInt(scale) > 0) {
                    columnt.setOptionType("d");
                }
            } else if (!"float".equals(fieldType) && !"double".equals(fieldType) && !"decimal".equals(fieldType)) {
                if ("numeric".equals(fieldType)) {
                    columnt.setOptionType("d");
                }
            } else {
                columnt.setOptionType("d");
            }
        } else {
            columnt.setClassType("easyui-datetimebox");
        }

    }

    private String formatDataType(String dataType, String precision, String scale) {
        if (dataType.contains("char")) {
            dataType = "java.lang.String";
        } else if (dataType.contains("int")) {
            dataType = "java.lang.Integer";
        } else if (dataType.contains("float")) {
            dataType = "java.lang.Float";
        } else if (dataType.contains("double")) {
            dataType = "java.lang.Double";
        } else if (dataType.contains("number")) {
            if (StringUtils.isNotBlank(scale) && Integer.parseInt(scale) > 0) {
                dataType = "java.math.BigDecimal";
            } else if (StringUtils.isNotBlank(precision) && Integer.parseInt(precision) > 10) {
                dataType = "java.lang.Long";
            } else {
                dataType = "java.lang.Integer";
            }
        } else if (dataType.contains("decimal")) {
            dataType = "BigDecimal";
        } else if (dataType.contains("date")) {
            dataType = "java.util.Date";
        } else if (dataType.contains("time")) {
            dataType = "java.util.Date";
        } else if (dataType.contains("blob")) {
            dataType = "byte[]";
        } else if (dataType.contains("clob")) {
            dataType = "java.sql.Clob";
        } else if (dataType.contains("numeric")) {
            dataType = "BigDecimal";
        } else {
            dataType = "java.lang.Object";
        }

        return dataType;
    }
}
