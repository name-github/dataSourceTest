package com.unistrong.geotsd.datasource.data;

import java.util.Date;

/**
 * 数据表基本信息
 * @author zt
 * @create 2018-06-29 10:05
 **/
public class DataTableBasicInfoEntity {

    private String dataSourceId;
    private String dataTableId;
    private String dataTableAlias;
    private String dataTableName;
    private String dataTablePK;
    private String remarks;
    private String updateTime;


    public DataTableBasicInfoEntity(String dataSourceId, String dataTableId, String dataTableAlias, String dataTableName, String dataTablePK, String remarks, String updateTime) {
        this.dataSourceId = dataSourceId;
        this.dataTableId = dataTableId;
        this.dataTableAlias = dataTableAlias;
        this.dataTableName = dataTableName;
        this.dataTablePK = dataTablePK;
        this.remarks = remarks;
        this.updateTime = updateTime;
    }

    public DataTableBasicInfoEntity(){}

    public String getDataSourceId() {
        return dataSourceId;
    }

    public String getDataTableId() {
        return dataTableId;
    }

    public String getDataTableAlias() {
        return dataTableAlias;
    }

    public String getDataTableName() {
        return dataTableName;
    }

    public String getDataTablePK() {
        return dataTablePK;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public void setDataTableId(String dataTableId) {
        this.dataTableId = dataTableId;
    }

    public void setDataTableAlias(String dataTableAlias) {
        this.dataTableAlias = dataTableAlias;
    }

    public void setDataTableName(String dataTableName) {
        this.dataTableName = dataTableName;
    }

    public void setDataTablePK(String dataTablePK) {
        this.dataTablePK = dataTablePK;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
