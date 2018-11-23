package com.unistrong.geotsd.datasource.data;

/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 数据库连接信息Entity
 */
public class DataBaseConInfoEntity {

    private String dataSourceId;
    private Integer dataBaseType;
    private String address;
    private Integer port;
    private String dataBaseName;
    private String userName;
    private String password;

    public DataBaseConInfoEntity() {
    }

    public DataBaseConInfoEntity(String dataSourceId, Integer dataBaseType, String address, Integer port, String dataBaseName, String userName, String password) {
        this.dataSourceId = dataSourceId;
        this.dataBaseType = dataBaseType;
        this.address = address;
        this.port = port;
        this.dataBaseName = dataBaseName;
        this.userName = userName;
        this.password = password;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public Integer getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(Integer dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
