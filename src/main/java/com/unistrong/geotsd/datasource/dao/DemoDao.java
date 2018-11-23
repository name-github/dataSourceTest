package com.unistrong.geotsd.datasource.dao;

import com.github.pagehelper.Page;
import com.unistrong.geotsd.datasource.data.DataSourceBasicInfoEntity;

import java.util.List;

/**
 *  测试
 * @author zc.shen
 * @version 1.0
 * @created 2018-06-27
 */
public interface DemoDao {
    int addNew(DataSourceBasicInfoEntity data);
    /**
     * 所有查询数据
     * @return
     */
    List<DataSourceBasicInfoEntity> findAll(String id);
    /**
     * 分页查询数据
     * @return
     */
    List<DataSourceBasicInfoEntity> findByPage();
}
