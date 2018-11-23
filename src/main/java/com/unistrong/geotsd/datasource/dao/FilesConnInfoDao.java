package com.unistrong.geotsd.datasource.dao;

import com.unistrong.geotsd.datasource.data.FilesConnInfoEntity;

/**
 * @Auther: wangshuo
 * @Date: 2018/7/4
 * @Description: 文件信息DAO
 */
public interface FilesConnInfoDao {

    /**
     * 保存文件访问信息
     *
     * @param filesConnInfo
     */
     void addnewByFilesConnInfo(FilesConnInfoEntity filesConnInfo);

    /**
     * 删除文件访问信息
     *
     * @param dataSourceId
     */
     void removeFilesConnInfoById(String dataSourceId);

    /**
     * 按主键更新文件连接信息
     *
     * @param filesConnInfoEntity
     */
     void updateFilesConnInfoById(FilesConnInfoEntity filesConnInfoEntity);

    /**
     * 按主键查询单条文件信息
     *
     * @param dataSourceId
     * @return
     */
     FilesConnInfoEntity getFilesConnInfoById(String dataSourceId);
}
