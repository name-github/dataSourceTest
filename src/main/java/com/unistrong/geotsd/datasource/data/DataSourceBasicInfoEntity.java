package com.unistrong.geotsd.datasource.data;

/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 数据源基本信息Entity
 */
public class DataSourceBasicInfoEntity {

    private Integer dataSourceType;
    private String dataSourceName;
    private String dataSourceId;
    private String modifyTime;
    private String remarks;

    public DataSourceBasicInfoEntity() {
    }

    public DataSourceBasicInfoEntity(Integer dataSourceType, String dataSourceName, String dataSourceId, String modifyTime, String remarks) {
        this.dataSourceType = dataSourceType;
        this.dataSourceName = dataSourceName;
        this.dataSourceId = dataSourceId;
        this.modifyTime = modifyTime;
        this.remarks = remarks;
    }

    public Integer getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(Integer dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
