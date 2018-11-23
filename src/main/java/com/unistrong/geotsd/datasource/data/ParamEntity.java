package com.unistrong.geotsd.datasource.data;


/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 新建数据源请求参数
 */
public class ParamEntity {
    private DatabaseConnInfoEntity newDataBaseConnInfo;
    private DataSourceBasicInfoEntity newDataSourceBasicInfo;
    private FilesConnInfoEntity newFilesConnInfo;
    private InterfaceConnInfoEntity newInterfaceConnInfo;

    public ParamEntity() {
    }

    public ParamEntity(DatabaseConnInfoEntity newDataBaseConnInfo, DataSourceBasicInfoEntity newDataSourceBasicInfo, FilesConnInfoEntity newFilesConnInfo, InterfaceConnInfoEntity newInterfaceConnInfo) {
        this.newDataBaseConnInfo = newDataBaseConnInfo;
        this.newDataSourceBasicInfo = newDataSourceBasicInfo;
        this.newFilesConnInfo = newFilesConnInfo;
        this.newInterfaceConnInfo = newInterfaceConnInfo;
    }

    public DatabaseConnInfoEntity getNewDataBaseConnInfo() {
        return newDataBaseConnInfo;
    }

    public void setNewDataBaseConnInfo(DatabaseConnInfoEntity newDataBaseConnInfo) {
        this.newDataBaseConnInfo = newDataBaseConnInfo;
    }

    public DataSourceBasicInfoEntity getNewDataSourceBasicInfo() {
        return newDataSourceBasicInfo;
    }

    public void setNewDataSourceBasicInfo(DataSourceBasicInfoEntity newDataSourceBasicInfo) {
        this.newDataSourceBasicInfo = newDataSourceBasicInfo;
    }

    public FilesConnInfoEntity getNewFilesConnInfo() {
        return newFilesConnInfo;
    }

    public void setNewFilesConnInfo(FilesConnInfoEntity newFilesConnInfo) {
        this.newFilesConnInfo = newFilesConnInfo;
    }

    public InterfaceConnInfoEntity getNewInterfaceConnInfo() {
        return newInterfaceConnInfo;
    }

    public void setNewInterfaceConnInfo(InterfaceConnInfoEntity newInterfaceConnInfo) {
        this.newInterfaceConnInfo = newInterfaceConnInfo;
    }
}
