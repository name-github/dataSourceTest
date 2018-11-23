package com.unistrong.geotsd.datasource.service;

import com.unistrong.geotsd.datasource.data.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 数据源Service接口
 */
public interface DataSourceService {

    /**
     * 新建数据源
     *
     * @param paramEntity
     * @return
     */
    String addNew(ParamEntity paramEntity);

    /**
     * 删除数据源
     *
     * @param dataSourceIdList
     */
    void remove(List<String> dataSourceIdList);

    /**
     * 按主键重命名
     *
     * @param dataSourceBasicInfoEntity
     */
    void rename(DataSourceBasicInfoEntity dataSourceBasicInfoEntity);

    /**
     * 按主键更新数据库连接信息
     *
     * @param dataBaseConInfoEntity
     */
    void updateDataBaseConInfo(DatabaseConnInfoEntity dataBaseConInfoEntity);

    /**
     * 按主键更新文件连接信息
     *
     * @param filesConnInfoEntity
     * @throws Exception
     */
    void updateFileConnInfo(FilesConnInfoEntity filesConnInfoEntity);

    /**
     * 按主键更新接口连接信息
     *
     * @param interfaceConnInfoEntity
     */
    void updateInterfaceConnInfo(InterfaceConnInfoEntity interfaceConnInfoEntity);

    /**
     * 按主键查询单条数据源全部信息
     *
     * @param dataSourceId
     * @return
     */
    Map getAllInfoById(String dataSourceId);

    /**
     * 按住键查询单条数据源基本信息
     *
     * @param dataSourceId
     * @return
     */
    DataSourceBasicInfoEntity getDataSourceBasicConnById(String dataSourceId);

    /**
     * 按主键查询单条数据库连接信息
     *
     * @param dataSourceId
     * @return
     */
    DatabaseConnInfoEntity getDataBaseConInfoById(String dataSourceId);

    /**
     * 按主键查询单条文件连接信息
     *
     * @param dataSourceId
     * @return
     */
    FilesConnInfoEntity getFilesConnInfoById(String dataSourceId);

    /**
     * 按主键查询单条接口连接信息
     *
     * @param dataSourceId
     * @return
     */
    InterfaceConnInfoEntity getInterfaceInfoById(String dataSourceId);

    /**
     * 按条件检索数据源基本信息
     * 支持分页
     *
     * @param jsonParams
     * @return
     */
    Object findDataSourceBasicConnInfoByCond(String jsonParams);

    /**
     * 分页查询
     *
     * @param queryMap 查询条件
     * @return
     */
    Paging<DataSourceBasicInfoEntity> findByPage(Map<String, Integer> queryMap);

}
