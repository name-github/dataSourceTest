package com.unistrong.geotsd.datasource.data;

import java.util.List;

/**
 * 数据表信息
 * @author zt
 * @create 2018-07-02 13:29
 **/
public class DataTableInfoEntity {

    private DataTableBasicInfoEntity dataTableBasicInfo;

    private List<FieldInfoEntity> fieldList;

    public DataTableInfoEntity(DataTableBasicInfoEntity dataTableBasicInfo, List<FieldInfoEntity> fieldList) {
        this.dataTableBasicInfo = dataTableBasicInfo;
        this.fieldList = fieldList;
    }

    public DataTableInfoEntity(){}

    public DataTableBasicInfoEntity getDataTableBasicInfo() {
        return dataTableBasicInfo;
    }

    public List<FieldInfoEntity> getFieldList() {
        return fieldList;
    }

    public void setDataTableBasicInfo(DataTableBasicInfoEntity dataTableBasicInfo) {
        this.dataTableBasicInfo = dataTableBasicInfo;
    }

    public void setFieldList(List<FieldInfoEntity> fieldList) {
        this.fieldList = fieldList;
    }
}