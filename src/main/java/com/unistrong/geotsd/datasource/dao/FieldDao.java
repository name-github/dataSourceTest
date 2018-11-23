package com.unistrong.geotsd.datasource.dao;

import com.unistrong.geotsd.datasource.data.FieldInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * @author zt
 * @create 2018-06-29 17:12
 **/
public interface FieldDao {

    /**
     * 根据字段id查数据表id
     * @param
     * @return
     */
    String getDtIdFromFieldId(String fieldId);

    /**
     * 获取指定数据表，指定字段名称的个数
     * @param condMap
     * @return
     */
    int getCountByFieldNames(Map<String, Object> condMap);

    /**
     * 清空指定数据表内全部字段
     * @param condMap
     */
    void clearByDtIds(Map<String, Object> condMap);

    /**
     * 清空指定数据源内全部字段
     * @param condMap
     */
    void clearByDsId(Map<String, Object> condMap);

    /**
     * 获取指定数据表全部字段属性信息
     * @param dataTableId
     * @return
     */
    List<FieldInfoEntity> findByDtId(String dataTableId);

    /**
     * 为指定数据表，批量添加字段
     * @param
     */
    void addNews(Map<String,Object> condMap);

    /**
     * 从指定数据表中，移除指定字段
     * @param condMap
     */
    void remove(Map<String,Object> condMap);


    /**
     * 编辑指定字段的基本属性
     * @param condMap
     */
    void update(Map<String,Object> condMap);
}