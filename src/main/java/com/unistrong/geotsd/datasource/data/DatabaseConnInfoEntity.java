package com.unistrong.geotsd.datasource.data;

/**
 * 数据源数据库访问信息实体类
 *
 * @author zc.shen
 * @version 1.0
 * @created 2018-06-27
 */
public class DatabaseConnInfoEntity {

    private String dataSourceId;
    private Integer dataBaseType;
    private String address;
    private Integer port;
    private String dataBaseName;
    private String userName;
    private String password;

    public DatabaseConnInfoEntity() {

    }

    public DatabaseConnInfoEntity(String dataSourceId, Integer dataBaseType, String address, Integer port, String dataBaseName, String userName, String password) {
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

    public Integer getDataBaseType() {
        return dataBaseType;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPort() {
        return port;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public void setDataBaseType(Integer dataBaseType) {
        this.dataBaseType = dataBaseType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}