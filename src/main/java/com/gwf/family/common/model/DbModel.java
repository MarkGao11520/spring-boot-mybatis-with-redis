package com.gwf.family.common.model;

/**
 * Created by lcy on 17/7/4.
 *
 * 数据库模型
 */
public enum DbModel {

    MYSQL("1"),SQLSERVER("2"),ORACLE("3");

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    DbModel(String type) {
        this.type = type;
    }

    DbModel() {
    }
}
