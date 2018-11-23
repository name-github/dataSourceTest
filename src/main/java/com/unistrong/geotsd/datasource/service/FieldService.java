package com.unistrong.geotsd.datasource.service;

import com.unistrong.geotsd.datasource.data.FieldInfoEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zt
 * @create 2018-06-29 17:07
 **/
@Service
public interface FieldService {


    /**
     * 判断字段名是否已经存在
     * @param condMap
     * @return
     */
    boolean isFieldExist(Map<String, Object> condMap);

    /**
     * 清空指定数据表内全部字段
     * @param condMap
     * @return
     */
    boolean clearByDtIds(Map<String, Object> condMap);


    /** 为指定数据表，批量添加字段
     * @param dataTableId
     * @param fieldList
     * @return
     */
    List<String> addNews(String dataTableId, List<FieldInfoEntity> fieldList);


    /**
     * 从指定数据表中，移除指定字段
     * @param condMap
     * @return
     */
    boolean remove(Map<String, Object> condMap);

    /**
     * 获取指定数据表的全部字段属性信息列表
     * @param dataTableId
     * @return
     */
    List<FieldInfoEntity> findByDtId(String dataTableId);

    /**
     * 编辑指定字段的基本属性
     * @param
     * @return
     */
    boolean update(Map<String, Object> condMap);


    /**
     * 根据字段id获取表id
     * @param fieldId
     * @return
     */
    String getDtIdFromFieldId(String fieldId);
}