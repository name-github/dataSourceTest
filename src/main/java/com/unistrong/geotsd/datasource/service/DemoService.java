package com.unistrong.geotsd.datasource.service;

import com.unistrong.geotsd.datasource.data.DataSourceBasicInfoEntity;
import com.unistrong.geotsd.datasource.data.Paging;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 测试
 * Created by zc.shen on 2018/7/3
 */
@Service
public interface DemoService  {
    String addNew(DataSourceBasicInfoEntity entity);
    List<DataSourceBasicInfoEntity> findAll(String id);

    /**
     * 分页查询
     * @param queryMap 查询条件
     * @return
     */
    Paging<DataSourceBasicInfoEntity> findByPage(Map<String ,Object> queryMap);

}
