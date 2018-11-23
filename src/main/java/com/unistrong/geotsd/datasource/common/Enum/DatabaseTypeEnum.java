package com.unistrong.geotsd.datasource.common.Enum;

/**
 *  数据库类型枚举
 * @author zc.shen
 * @created 2018-06-27
 */
public enum DatabaseTypeEnum {
    ORACLE("orcle",1),
    MYSQL("mysql",2);
    private String databaseName;
    private Integer databaseType;

    DatabaseTypeEnum(String databaseName, int databaseType) {
        this.databaseName = databaseName;
        this.databaseType = databaseType;

    }

    public String getDatabaseName() {
        return databaseName;
    }

    public Integer getDatabaseType() {
        return databaseType;
    }
}
