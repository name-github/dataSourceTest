package com.unistrong.geotsd.datasource.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.unistrong.geotsd.datasource.dao.DataTableDao;
import com.unistrong.geotsd.datasource.dao.FieldDao;
import com.unistrong.geotsd.datasource.data.*;
import com.unistrong.geotsd.datasource.service.DataTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.*;

/**
 * @author zt
 * @create 2018-06-29 13:24
 **/
@Service("dataTableService")
public class DataTableServiceImpl implements DataTableService {

    private final Logger _log = LoggerFactory.getLogger(DataTableServiceImpl.class);
    @Resource
    private DataTableDao dataTableDao;
    @Resource
    private FieldDao fieldDao;


    /**
     * 数据源是否存在
     * @param dataSourceId
     * @return
     */
    @Override
    public boolean isDataSourceExist(String dataSourceId) {
        int count = dataTableDao.getCountByDsId(dataSourceId);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 数据表是否存在
     * @param dataTableIds
     * @return
     */
    @Override
    public boolean isDataTableExist(List<String> dataTableIds) {
        int count = dataTableDao.getCountByDtIds(dataTableIds);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 判断表名是否已经存在
     * @param condMap
     * @return
     */
    @Override
    public boolean isTableExist(Map<String, Object> condMap) {
        int count = dataTableDao.getCountByDtNames(condMap);
        if(count > 0){
            return true;
        }
        return false;
    }

    /**
     * 编辑指定数据表的基本属性
     * @param condMap
     * @return
     */
    @Override
    public boolean update(Map<String, Object> condMap) {
        if(condMap == null) {
            return false;
        }
        try{
            dataTableDao.update(condMap);
        }catch (Exception e){
            _log.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public String getDsIdFromDtId(String dataTableId) {
        return dataTableDao.getDsIdFromDtId(dataTableId);
    }

    /**
     * 从指定数据源内移除指定数据表
     *
     * @param condMap
     * @return
     */
    @Override
    public boolean remove(Map<String, Object> condMap) {
        if (condMap == null || condMap.size() < 2) {
            return false;
        }

        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("dataTableIds", condMap.get("dataTableIds"));
        try {
            fieldDao.clearByDtIds(fieldMap);
            dataTableDao.remove(condMap);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * 清空指定数据源全部数据表
     *
     * @param condMap
     * @return
     */
    @Override
    public boolean clear(Map<String, Object> condMap) {
        if (condMap == null || condMap.isEmpty()) {
            return false;
        }
        Map<String, Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("dataSourceId", condMap.get("dataSourceId"));
        try {
            fieldDao.clearByDsId(fieldMap);
            dataTableDao.clear(condMap);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 根据唯一标识，获取指定数据表全部信息
     *
     * @param
     * @return
     */
    @Override
    public DataTableInfoEntity getById(String dataTableId) {
        if (dataTableId == null || dataTableId.equals("")) {
            return null;
        }
        DataTableInfoEntity dataTableInfo = new DataTableInfoEntity();

        try {
            DataTableBasicInfoEntity dataTableBasicInfo = dataTableDao.getById(dataTableId);
            List<FieldInfoEntity> fieldList = fieldDao.findByDtId(dataTableId);
            dataTableInfo.setDataTableBasicInfo(dataTableBasicInfo);
            dataTableInfo.setFieldList(fieldList);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
        return dataTableInfo;
    }


    /**
     * 根据数据源id获取数据表基本信息集合
     *
     * @param condMap
     * @return
     */
    @Override
    public Paging<DataTableBasicInfoEntity> findAllInfoByDsId(Map<String, Object> condMap) {
        if (condMap == null || condMap.isEmpty()) {
            return null;
        }
        PageInfo<DataTableBasicInfoEntity> pageInfo = null;
        try {
            PageHelper.startPage(Integer.parseInt(condMap.get("pageNum").toString()), Integer.parseInt(condMap.get("pageSize").toString()));
            pageInfo = new PageInfo<DataTableBasicInfoEntity>(dataTableDao.findAllInfoByDsId(condMap));
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
        return new Paging(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
    }

    /**
     * 根据数据源id获取数据表全部信息
     *
     * @param dataSourceId
     * @return
     */
    @Override
    public List<DataTableInfoEntity>  findAllByDsId(String dataSourceId) {
        if(dataSourceId == null || dataSourceId.equals("")){
            return null;
        }

        List<DataTableInfoEntity> list = null;
        try{
            list = dataTableDao.findAllByDsId(dataSourceId);
        }catch (Exception e){
            _log.error(e.getMessage());
            return null;
        }
        return list;
    }

    /**
     * 获取指定数据源内，指定数据表全部信息（数据表基本信息+字段集合）
     * @param condMap
     * @return
     */
    @Override
    public List<DataTableInfoEntity> findAllByDsIdAndDtIds(Map<String, Object> condMap) {
        if (condMap == null || condMap.size() < 2) {
            return null;
        }

        List<DataTableInfoEntity> tableInfo = new ArrayList<DataTableInfoEntity>();
        try {
            tableInfo = dataTableDao.findAllByDsIdAndDtIds(condMap);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }

        return tableInfo;
    }


    /**
     * 根据关键字，获取数据表基本信息集合
     *
     * @param condMap
     * @return
     */
    @Override
    public Paging<DataTableBasicInfoEntity> findByKW(Map<String, Object> condMap) {
        if (condMap == null || condMap.isEmpty()) {
            return null;
        }
        PageInfo<DataTableBasicInfoEntity> pageInfo = null;
        try {
            PageHelper.startPage(Integer.parseInt(condMap.get("pageNum").toString()), Integer.parseInt(condMap.get("pageSize").toString()));
            pageInfo = new PageInfo<DataTableBasicInfoEntity>(dataTableDao.findByKW(condMap));
        }catch (Exception e){
            _log.error(e.getMessage());
            return null;
        }
        return new Paging(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
    }

    /**
     * 为指定数据源，批量添加多个数据表
     *
     * @param
     * @return
     */
    @Override
    public List<String> addNews(String dataSourceId,List<DataTableInfoEntity> dataTableInfos) {
        if (dataSourceId == null
                || dataSourceId.equals("")
                || dataTableInfos == null
                || dataTableInfos.isEmpty()) {
            return null;
        }

        List<String> dtIdList = new ArrayList<String>();
        try {
            String dsId = dataSourceId;
            List<DataTableBasicInfoEntity> basicInfoList = new ArrayList<DataTableBasicInfoEntity>();
            List<FieldInfoEntity> fieldList = new ArrayList<FieldInfoEntity>();

            for (DataTableInfoEntity table : dataTableInfos) {
                DataTableBasicInfoEntity basicInfo = table.getDataTableBasicInfo();
                basicInfo.setDataSourceId(dsId);
                String dtId = UUID.randomUUID().toString().replace("-", "");
                basicInfo.setDataTableId(dtId);
                basicInfoList.add(basicInfo);
                dtIdList.add(dtId);
                if (table.getFieldList() == null || table.getFieldList().size() < 1) {
                    continue;
                }
                for (FieldInfoEntity field : table.getFieldList()) {
                    field.setDataTableId(dtId);
                    field.setFieldId(UUID.randomUUID().toString().replace("-", ""));
                    fieldList.add(field);
                }
            }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("dataSourceId",dsId);
            map.put("fieldList",fieldList);
            dataTableDao.addNews(basicInfoList);
            fieldDao.addNews(map);
        } catch (Exception e) {
            _log.error(e.getMessage());
        }
        return dtIdList;
    }
}