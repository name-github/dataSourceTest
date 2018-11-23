package com.unistrong.geotsd.datasource.dao;


import com.unistrong.geotsd.datasource.data.DataBaseConInfoEntity;
import com.unistrong.geotsd.datasource.data.DataSourceBasicInfoEntity;
import com.unistrong.geotsd.datasource.data.FilesConnInfoEntity;
import com.unistrong.geotsd.datasource.data.InterfaceConnInfoEntity;

import java.util.List;

/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 数据源DAO
 */
public interface DataSourceDao {

    /**
     * 保存数据库访问信息
     *
     * @param dbConnInfo
     */
    public void addnewByDbBasicConnInfo(DataBaseConInfoEntity dbConnInfo);

    /**
     * 保存数据源基本信息
     *
     * @param dsBasicInfo
     */
    public void addnewByDsBasicInfo(DataSourceBasicInfoEntity dsBasicInfo);

    /**
     * 保存文件访问信息
     *
     * @param filesConnInfo
     */
    public void addnewByFilesConnInfo(FilesConnInfoEntity filesConnInfo);

    /**
     * 保存接口访问信息
     *
     * @param interfaceConnInfo
     */
    public void addnewByInterfaceConnInfo(InterfaceConnInfoEntity interfaceConnInfo);

    /**
     * 删除数据库访问信息
     *
     * @param dbConnInfo
     */
    public void removeDataBaseConnInfoById(DataBaseConInfoEntity dbConnInfo);

    /**
     * 删除数据源基本信息
     *
     * @param dsBasicInfo
     */
    public void removeDataSourceBasicInfoById(DataSourceBasicInfoEntity dsBasicInfo);

    /**
     * 删除文件访问信息
     *
     * @param filesConnInfo
     */
    public void removeFilesConnInfoById(FilesConnInfoEntity filesConnInfo);

    /**
     * 删除接口访问信息
     *
     * @param interfaceConnInfo
     */
    public void removeInterfaceConnInfoById(InterfaceConnInfoEntity interfaceConnInfo);

    /**
     * 按主键查询数据源基本信息
     *
     * @param dataSourceBasicInfoEntity
     * @return
     */
    public DataSourceBasicInfoEntity findDataSourceBasicInfoById(DataSourceBasicInfoEntity dataSourceBasicInfoEntity);

    /**
     * 按主键重命名
     *
     * @param dataSourceBasicInfoEntity
     */
    public void updateDataSourceBasicInfoById(DataSourceBasicInfoEntity dataSourceBasicInfoEntity);

    /**
     * 按主键更新数据库连接信息
     *
     * @param dbConnInfo
     */
    public void updateDataBaseConnInfoById(DataBaseConInfoEntity dbConnInfo);

    /**
     * 按主键更新文件连接信息
     *
     * @param filesConnInfoEntity
     */
    public void updateFilesConnInfoById(FilesConnInfoEntity filesConnInfoEntity);

    /**
     * 按主键更新接口信息
     *
     * @param interfaceConnInfoEntity
     */
    public void updateInterfaceConnInfoById(InterfaceConnInfoEntity interfaceConnInfoEntity);

    /**
     * 查询数据源基本信息
     * 支持分页
     *
     * @return
     */
    public List<DataSourceBasicInfoEntity> findAllByDataSourceBasicInfo();

    /**
     * 按主键查询单条数据库连接信息
     *
     * @param dataBaseConInfoEntity
     * @return
     */
    public DataBaseConInfoEntity getDataBaseConInfoById(DataBaseConInfoEntity dataBaseConInfoEntity);

    /**
     * 按主键查询单条文件信息
     *
     * @param filesConnInfoEntity
     * @return
     */
    public FilesConnInfoEntity getFilesConnInfoById(FilesConnInfoEntity filesConnInfoEntity);

    /**
     * 按主键查询单条接口信息
     *
     * @param interfaceConnInfoEntity
     * @return
     */
    public InterfaceConnInfoEntity getInterfaceConnInfoById(InterfaceConnInfoEntity interfaceConnInfoEntity);

    /**
     * 按条件检索数据源基本信息
     *
     * @param dataSourceBasicInfoEntity
     * @return
     */
    public List<DataSourceBasicInfoEntity> findDataSourceBasicConnInfoByCond(DataSourceBasicInfoEntity dataSourceBasicInfoEntity);

}
