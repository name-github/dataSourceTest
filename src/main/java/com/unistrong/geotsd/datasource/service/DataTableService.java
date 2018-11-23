package com.unistrong.geotsd.datasource.service;

import com.unistrong.geotsd.datasource.data.DataTableBasicInfoEntity;
import com.unistrong.geotsd.datasource.data.DataTableInfoEntity;
import com.unistrong.geotsd.datasource.data.Paging;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zt
 * @create 2018-06-29 12:06
 **/
@Service
public interface DataTableService {


    /**
     * 数据源是否存在
     * @param dataSourceId
     * @return
     */
    boolean isDataSourceExist(String dataSourceId);

    /**
     * 数据表是否存在
     * @param dataTableIds
     * @return
     */
    boolean isDataTableExist(List<String> dataTableIds);

    /**
     * 根据数据表id查数据源id
     * @param
     * @return
     */
    String getDsIdFromDtId(String dataTableId);

    /**
     * 从指定数据源内，移除指定数据表
     * @param condMap
     * @return
     */
    boolean remove(Map<String, Object> condMap);

    /**
     * 清空指定数据源内全部数据表
     * @param condMap
     * @return
     */
    boolean clear(Map<String, Object> condMap);


    /**
     * 根据唯一标识获取指定数据表全部信息
     * @param
     * @return
     */
    DataTableInfoEntity getById(String dataTableId);

    /**
     * 获取指定数据源全部数据表基本属性（支持分页）
     * @param condMap
     * @return
     */
    Paging<DataTableBasicInfoEntity> findAllInfoByDsId(Map<String, Object> condMap);

    /**
     * 获取指定数据源全部数据表全部信息
     * @param dataSourceId
     * @return
     */
    List<DataTableInfoEntity>  findAllByDsId(String dataSourceId);

    /**
     * 获取指定数据源内，指定数据表全部信息（数据表基本信息+字段集合）
     * @param condMap
     * @return
     */
    List<DataTableInfoEntity> findAllByDsIdAndDtIds(Map<String, Object> condMap);

    /**
     * 根据关键字，获取指定数据源全部数据表基本属性（支持分页）
     * @param condMap
     * @return
     */
    Paging<DataTableBasicInfoEntity> findByKW(Map<String, Object> condMap);


    /**
     * 为指定数据源，批量添加多个数据表接口
     * @param
     * @return
     */
    List<String> addNews(String dataSourceId,List<DataTableInfoEntity> dataTableInfos);


    /**
     * 判断表名是否已经存在
     * @param condMap
     * @return
     */
    boolean isTableExist(Map<String, Object> condMap);

    /**
     * 编辑指定数据表的基本属性
     * @param
     * @return
     */
    boolean update(Map<String, Object> condMap);

}