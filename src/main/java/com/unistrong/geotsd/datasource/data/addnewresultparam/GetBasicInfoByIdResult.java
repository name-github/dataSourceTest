package com.unistrong.geotsd.datasource.data.addnewresultparam;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.unistrong.geotsd.datasource.data.DataBaseConInfoEntity;
import com.unistrong.geotsd.datasource.data.DataSourceBasicInfoEntity;
import com.unistrong.geotsd.datasource.data.FilesConnInfoEntity;
import com.unistrong.geotsd.datasource.data.InterfaceConnInfoEntity;
import com.unistrong.geotsd.datasource.data.commonresultparam.Response;

/**
 * @Auther: wangshuo
 * @Date: 2018/7/2
 * @Description: 2.1.10	获取指定数据源基本属性信息接口 返回参数
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetBasicInfoByIdResult extends Response {

    private DataSourceBasicInfoEntity dataSourceBasicInfoEntity;
    private DataBaseConInfoEntity dataBaseConInfoEntity;
    private FilesConnInfoEntity filesConnInfoEntity;
    private InterfaceConnInfoEntity interfaceConnInfoEntity;

    public DataSourceBasicInfoEntity getDataSourceBasicInfoEntity() {
        return dataSourceBasicInfoEntity;
    }

    public void setDataSourceBasicInfoEntity(DataSourceBasicInfoEntity dataSourceBasicInfoEntity) {
        this.dataSourceBasicInfoEntity = dataSourceBasicInfoEntity;
    }

    public DataBaseConInfoEntity getDataBaseConInfoEntity() {
        return dataBaseConInfoEntity;
    }

    public void setDataBaseConInfoEntity(DataBaseConInfoEntity dataBaseConInfoEntity) {
        this.dataBaseConInfoEntity = dataBaseConInfoEntity;
    }

    public FilesConnInfoEntity getFilesConnInfoEntity() {
        return filesConnInfoEntity;
    }

    public void setFilesConnInfoEntity(FilesConnInfoEntity filesConnInfoEntity) {
        this.filesConnInfoEntity = filesConnInfoEntity;
    }

    public InterfaceConnInfoEntity getInterfaceConnInfoEntity() {
        return interfaceConnInfoEntity;
    }

    public void setInterfaceConnInfoEntity(InterfaceConnInfoEntity interfaceConnInfoEntity) {
        this.interfaceConnInfoEntity = interfaceConnInfoEntity;
    }
}
