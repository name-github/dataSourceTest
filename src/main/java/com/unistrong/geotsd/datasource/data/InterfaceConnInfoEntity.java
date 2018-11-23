package com.unistrong.geotsd.datasource.data;

/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 接口访问信息Entity
 */
public class InterfaceConnInfoEntity {
    private Integer interfaceType;
    private String interfaceAddress;
    private String dataSourceId;

    public InterfaceConnInfoEntity() {
    }

    public InterfaceConnInfoEntity(Integer interfaceType, String interfaceAddress, String dataSourceId) {
        this.interfaceType = interfaceType;
        this.interfaceAddress = interfaceAddress;
        this.dataSourceId = dataSourceId;
    }

    public Integer getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(Integer interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getInterfaceAddress() {
        return interfaceAddress;
    }

    public void setInterfaceAddress(String interfaceAddress) {
        this.interfaceAddress = interfaceAddress;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }
}
