package com.gwf.family.common.model;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lcy on 17/7/5.
 */
@Data
public class DbColumnModel implements Serializable {
    private static final long serialVersionUID = -7808860348918160912L;


    public DbColumnModel() {
    }

    private String colName;  //列名

    private String typeName; //类型名称

    private String typeNameUpper; //类型名称

    private String remarks;

    private int precision, isNull, dataType, scale; //精度,是否为空,类型,小数的位数

    private boolean isKey = false;  //是否为主键

    private String columnNameLowerCamel;  //列名转化为小写驼峰式

    private String columnNameUpperCamel;  //列名转化为大写驼峰式

    private String javaType;  //数据库属性名转化为java属性名

    public String getJavaType() {
        String typeNamel = typeName.toLowerCase();
        switch (typeNamel) {
            case "int":
                javaType = "Integer";
                break;
            case "varchar":
                javaType = "String";
                break;
            case "char":
                javaType = "Char";
                break;
            case "blob":
                javaType = "Byte[]";
                break;
            case "text":
                javaType = "String";
                break;
            case "integer":
                javaType = "Long";
                break;
            case "tinyint":
                javaType = "Integer";
                break;
            case "smallint":
                javaType = "Integer";
                break;
            case "mediumint":
                javaType = "Integer";
                break;
            case "bit":
                javaType = "Boolean";
                break;
            case "bigint":
                javaType = "long";
                break;
            case "float":
                javaType = "Float";
                break;
            case "double":
                javaType = "Double";
                break;
            case "decimal":
                javaType = "BigDecimal";
                break;
            case "id":
                javaType = "Long";
                break;
            case "date":
                javaType = "Date";
                break;
            case "year":
                javaType = "Date";
                break;
            case "time":
                javaType = "Date";
                break;
            case "datetime":
                javaType = "Date";
                break;
            case "timestamp":
                javaType = "Date";
                break;
            case "smallint unsigned":
                javaType = "short";
                break;
            case "tinyint unsigned":
                javaType = "Byte";
                break;
            case "enum":
                javaType = "String";
                break;
            case "set":
                javaType = "String";
                break;
        }
        return javaType;
    }

    public String getColumnNameLowerCamel() {
        columnNameLowerCamel = columnNameConvertLowerCamel(colName);
        return columnNameLowerCamel;
    }


    public String getColumnNameUpperCamel() {
        columnNameUpperCamel = columnNameConvertUpperCamel(colName);
        return columnNameUpperCamel;
    }

    /**
     * 将数据库属性名改为驼峰式，首字母大写
     *
     * @param columnName 列名
     * @return
     */
    private static String columnNameConvertLowerCamel(String columnName) {
        StringBuilder result = new StringBuilder();
        if (columnName != null && columnName.length() > 0) {
            columnName = columnName.toLowerCase();//兼容使用大写的表名
            boolean flag = false;
            for (int i = 0; i < columnName.length(); i++) {
                char ch = columnName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }

    /**
     * 将数据库属性名改为驼峰式，首字母大写
     *
     * @param columnName 列名
     * @return
     */
    private static String columnNameConvertUpperCamel(String columnName) {
        String camel = columnNameConvertLowerCamel(columnName);
        return camel.substring(0, 1).toUpperCase() + camel.substring(1);
    }


    public boolean getIsKey() {
        return isKey;
    }

    public String getTypeNameUpper() {
        typeNameUpper = typeName.toUpperCase();
        switch (typeNameUpper){
            case "INT":
                typeNameUpper = "INTEGER";
                break;
            case "DATETIME":
                typeNameUpper = "TIMESTAMP";
                break;
            case "SMALLINT UNSIGNED":
                typeNameUpper = "SMALLINT";
                break;
            case "TEXT":
                typeNameUpper = "LONGVARCHAR";
                break;
            case "YEAR":
                typeNameUpper = "DATE";
                break;
            case "TINYINT UNSIGNED":
                typeNameUpper = "TINYINT";
                break;
            case "ENUM":
                typeNameUpper = "CHAR";
                break;
            case "SET":
                typeNameUpper = "CHAR";
                break;
        }
        return typeNameUpper;
    }

    /**
     * 复制属性
     *
     * @param rs
     * @throws SQLException
     */
    public void copyColumnFromSqlResult(ResultSet rs) throws SQLException {
        try {
            String colName = rs.getString("COLUMN_NAME");//列名
            String typeName = rs.getString("TYPE_NAME");//类型名称
            String remarks = rs.getString("REMARKS");
            int precision = rs.getInt("COLUMN_SIZE");//精度
            int isNull = rs.getInt("NULLABLE");//是否为空
            int dataType = rs.getInt("DATA_TYPE");//类型
            int scale = rs.getInt("DECIMAL_DIGITS");// 小数的位数

            this.setColName(colName);
            this.setTypeName(typeName);
            this.setPrecision(precision);
            this.setIsNull(isNull);
            this.setDataType(dataType);
            this.setScale(scale);
            this.setRemarks(StringUtils.isEmpty(remarks)?colName:remarks);
        } catch (SQLException e) {
            throw e;
        }
    }
}
