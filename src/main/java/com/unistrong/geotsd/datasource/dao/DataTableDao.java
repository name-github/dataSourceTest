package com.unistrong.geotsd.datasource.dao;

import com.unistrong.geotsd.datasource.data.DataTableBasicInfoEntity;
import com.unistrong.geotsd.datasource.data.DataTableInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * @author zt
 * @create 2018-06-29 11:11
 **/
public interface DataTableDao {


    /**
     * 根据数据源id获取指定数据源个数
     * @param dataSourceId
     * @return 等于0则不存在
     */
    int getCountByDsId(String dataSourceId);

    /**
     * 根据数据表id集合,获取数据表个数
     * @param dataTableIds
     * @return
     */
    int getCountByDtIds(List<String> dataTableIds);

    /**
     * 根据数据表id查数据源id
     * @param
     * @return
     */
    String getDsIdFromDtId(String dataTableId);

    /**
     * 获取指定数据源，指定数据表名称的个数
     * @param condMap
     * @return
     */
    int getCountByDtNames(Map<String, Object> condMap);

    /**
     * 从指定数据源内，移除指定数据表
     *
     * @param condMap
     */
    void remove(Map<String, Object> condMap);

    /**
     * 清空指定数据源内全部数据表
     *
     * @param condMap
     */
    void clear(Map<String, Object> condMap);

    /**
     * 根据唯一标识获取指定数据表全部信息
     *
     * @param
     * @return
     */
    DataTableBasicInfoEntity getById(String dataTableId);

    /**
     * 获取指定数据源全部数据表基本属性
     * @param condMap
     * @return
     */
    List<DataTableBasicInfoEntity> findAllInfoByDsId(Map<String, Object> condMap);

    /**
     * 获取指定数据源全部数据表全部信息
     * @param dataSourceId
     * @return
     */
    List<DataTableInfoEntity> findAllByDsId(String dataSourceId);

    /**
     * 获取指定数据源内，指定数据表全部信息（数据表基本信息+字段集合）
     * @param condMap
     * @return
     */
    List<DataTableInfoEntity> findAllByDsIdAndDtIds(Map<String, Object> condMap);

    /**
     * 根据关键字，获取指定数据源全部数据表基本属性
     * @param condMap
     * @return
     */
    List<DataTableBasicInfoEntity> findByKW(Map<String, Object> condMap);


    /**
     * 为指定数据源，批量添加多个数据表接口
     * @param
     * @return
     */
    void addNews(List<DataTableBasicInfoEntity> dataTableList);

    /**
     * 编辑指定数据表的基本属性
     * @param condMap
     */
    void update(Map<String,Object> condMap);
}