package com.gwf.family.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lcy on 17/7/5.
 * 表模型
 */
public class DbTableModel implements Serializable{
    private static final long serialVersionUID = 1803273420552584045L;

    public DbTableModel() {
    }
    private List<DbColumnModel> columnModels;

    public List<DbColumnModel> getColumnModels() {
        return columnModels;
    }

    public void setColumnModels(List<DbColumnModel> columnModels) {
        this.columnModels = columnModels;
    }

    private String tableName; //表名 全小写

    private String entityName;

    private String mappingPath;

    public String getEntityName() {
        StringBuffer simpleEntity = new StringBuffer();
        boolean upPower = true;
        for(int i = 0 ; i < tableName.length() ; i++){
            char tab = tableName.charAt(i);
            String tabStr = tab + "";
            //非数字 字符
            if(!tabStr.matches("[0-9a-zA-Z]*")){
                upPower = true;
                continue;
            }
            if(upPower)
                simpleEntity.append(Character.toUpperCase(tab));
            else
                simpleEntity.append(tab);
            upPower = false;
        }
        return simpleEntity.toString();
    }

    public String getMappingPath() {
        return tableNameConvertMappingPath(tableName);
    }


    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    private static String tableNameConvertMappingPath(String tableName) {
        tableName = tableName.toLowerCase();//兼容使用大写的表名
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }
}
