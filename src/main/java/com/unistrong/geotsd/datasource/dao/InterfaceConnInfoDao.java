package com.unistrong.geotsd.datasource.dao;

import com.unistrong.geotsd.datasource.data.InterfaceConnInfoEntity;

/**
 * @Auther: wangshuo
 * @Date: 2018/7/4
 * @Description: 接口信息DAO
 */
public interface InterfaceConnInfoDao {

    /**
     * 保存接口访问信息
     *
     * @param interfaceConnInfo
     */
     void addnewByInterfaceConnInfo(InterfaceConnInfoEntity interfaceConnInfo);

    /**
     * 删除接口访问信息
     *
     * @param dataSourceId
     */
     void removeInterfaceConnInfoById(String dataSourceId);

    /**
     * 按主键更新接口信息
     *
     * @param interfaceConnInfoEntity
     */
     void updateInterfaceConnInfoById(InterfaceConnInfoEntity interfaceConnInfoEntity);

    /**
     * 按主键查询单条接口信息
     *
     * @param dataSourceId
     * @return
     */
     InterfaceConnInfoEntity getInterfaceConnInfoById(String dataSourceId);
}
