package com.unistrong.geotsd.datasource.dao;

import com.unistrong.geotsd.datasource.data.DatabaseConnInfoEntity;

/**
 * @Auther: wangshuo
 * @Date: 2018/7/4
 * @Description: 数据库连接信息DAO
 */
public interface DataBaseConnInfoDao {


    /**
     * 保存数据库访问信息
     *
     * @param dbConnInfo
     */
    void addnewByDbBasicConnInfo(DatabaseConnInfoEntity dbConnInfo);

    /**
     * 按主键更新数据库连接信息
     *
     * @param dbConnInfo
     */
     void updateDataBaseConnInfoById(DatabaseConnInfoEntity dbConnInfo);

    /**
     * 删除数据库访问信息
     *
     * @param dataSourceId
     */
     void removeDataBaseConnInfoById(String dataSourceId);

    /**
     * 按主键查询单条数据库连接信息
     *
     * @param dataSourceId
     * @return
     */
     DatabaseConnInfoEntity getDataBaseConInfoById(String dataSourceId);
}
