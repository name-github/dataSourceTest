package com.unistrong.geotsd.datasource.data;

import com.unistrong.geotsd.datasource.common.Enum.FieldDataTypeEnum;

import java.util.Date;

/**
 * 字段信息
 * @author zt
 * @create 2018-06-29 10:19
 **/
public class FieldInfoEntity {

    private String fieldId;
    private String dataTableId;
    private String fieldAlias;
    private String fieldName;
    private int fieldType;
    private String remarks;
    private String updateTime;

    public FieldInfoEntity(String fieldId, String dataTableId, String fieldAlias, String fieldName, int fieldType, String remarks, String updateTime) {
        this.fieldId = fieldId;
        this.dataTableId = dataTableId;
        this.fieldAlias = fieldAlias;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.remarks = remarks;
        this.updateTime = updateTime;
    }

    public FieldInfoEntity(){}

    public String getFieldId() {
        return fieldId;
    }

    public String getDataTableId() {
        return dataTableId;
    }

    public String getFieldAlias() {
        return fieldAlias;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getFieldType() {
        return fieldType;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public void setDataTableId(String dataTableId) {
        this.dataTableId = dataTableId;
    }

    public void setFieldAlias(String fieldAlias) {
        this.fieldAlias = fieldAlias;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}