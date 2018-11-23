package com.unistrong.geotsd.datasource.dao;

import com.unistrong.geotsd.datasource.data.DataSourceBasicInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * @Auther: wangshuo
 * @Date: 2018/7/4
 * @Description: 数据源基本信息DAO
 */
public interface DataSourceBasicInfoDao {

    /**
     * 保存数据源基本信息
     *
     * @param dsBasicInfo
     */
     void addnewByDsBasicInfo(DataSourceBasicInfoEntity dsBasicInfo);

    /**
     * 删除数据源基本信息
     *
     * @param dataSourceId
     */
     void removeDataSourceBasicInfoById(String dataSourceId);

    /**
     * 按主键重命名
     *
     * @param dataSourceBasicInfoEntity
     */
     void updateDataSourceBasicInfoById(DataSourceBasicInfoEntity dataSourceBasicInfoEntity);

    /**
     * 按主键查询数据源基本信息
     *
     * @param dataSourceId
     * @return
     */
     DataSourceBasicInfoEntity findDataSourceBasicInfoById(String dataSourceId);

    /**
     * 查询数据源基本信息
     * 支持分页
     *
     * @return
     */
     List<DataSourceBasicInfoEntity> findAllByDataSourceBasicInfo();

    /**
     * 按条件检索数据源基本信息
     *
     * @param map
     * @return
     */
     List<DataSourceBasicInfoEntity> findDataSourceBasicConnInfoByCond(Map<String,Object> map);

}
