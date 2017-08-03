package com.gwf.family.business.code.model;

import java.io.Serializable;

/**
 * Created by lcy on 17/7/1.
 *
 * code table
 */
public class CodeTable implements Serializable {


    private static final long serialVersionUID = 1154645476624841358L;



    public CodeTable() {
    }

    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
