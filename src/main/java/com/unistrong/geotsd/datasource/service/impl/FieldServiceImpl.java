package com.unistrong.geotsd.datasource.service.impl;

import com.unistrong.geotsd.datasource.dao.DataTableDao;
import com.unistrong.geotsd.datasource.dao.FieldDao;
import com.unistrong.geotsd.datasource.data.FieldInfoEntity;
import com.unistrong.geotsd.datasource.service.FieldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zt
 * @create 2018-06-29 17:10
 **/
@Service("fieldService")
public class FieldServiceImpl implements FieldService {

    private final Logger _log = LoggerFactory.getLogger(FieldServiceImpl.class);
    @Resource
    private FieldDao fieldDao;

    @Resource
    private DataTableDao tableDao;

    /**
     * 判断字段名是否已经存在
     *
     * @param condMap
     * @return
     */
    @Override
    public boolean isFieldExist(Map<String, Object> condMap) {
        int count = fieldDao.getCountByFieldNames(condMap);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 清空指定数据表内全部字段
     *
     * @param condMap
     * @return
     */
    @Override
    public boolean clearByDtIds(Map<String, Object> condMap) {

        if (condMap == null || condMap.isEmpty()) {
            return false;
        }
        try {
            fieldDao.clearByDtIds(condMap);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 为指定数据表，批量添加字段
     *
     * @param dataTableId
     * @param fieldList
     * @return
     */
    @Override
    public List<String> addNews(String dataTableId, List<FieldInfoEntity> fieldList) {
        if (dataTableId == null
                || dataTableId.equals("")
                || fieldList == null
                || fieldList.isEmpty()) {
            return null;
        }
        List<String> fieldIdList = new ArrayList<String>();

        try {
            String dtId = dataTableId;
            String dsId = tableDao.getDsIdFromDtId(dtId);

            List<FieldInfoEntity> resultList = new ArrayList<FieldInfoEntity>();
            for (FieldInfoEntity field : fieldList){
                field.setDataTableId(dtId);
                field.setFieldId(UUID.randomUUID().toString().replace("-", ""));
                resultList.add(field);
                fieldIdList.add(field.getFieldId());
            }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("dataSourceId",dsId);
            map.put("fieldList",resultList);

            fieldDao.addNews(map);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
        return fieldIdList;
    }


    /**
     * 从指定数据表中，移除指定字段
     * @param condMap
     * @return
     */
    @Override
    public boolean remove(Map<String, Object> condMap) {
        if (condMap == null || condMap.size() < 2) {
            return false;
        }
        try{
            fieldDao.remove(condMap);
        }catch (Exception e){
            _log.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 获取指定数据表的全部字段属性信息列表
     * @param dataTableId
     * @return
     */
    @Override
    public List<FieldInfoEntity> findByDtId(String dataTableId) {
        if (dataTableId == null || dataTableId.equals("")) {
            return null;
        }
        List<FieldInfoEntity> fieldList = new ArrayList<FieldInfoEntity>();
        try {
            fieldList = fieldDao.findByDtId(dataTableId);
        } catch (Exception e) {
            _log.error(e.getMessage());
            return null;
        }
        return fieldList;
    }


    /**
     * 编辑指定字段的基本属性
     * @param
     * @return
     */
    @Override
    public boolean update(Map<String, Object> condMap) {
        if(condMap == null) {
            return false;
        }
        try{
            fieldDao.update(condMap);
        }catch (Exception e){
            _log.error(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public String getDtIdFromFieldId(String dataTableId) {
        return fieldDao.getDtIdFromFieldId(dataTableId);
    }

}