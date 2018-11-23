package com.unistrong.geotsd.datasource.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.unistrong.geotsd.datasource.dao.DemoDao;
import com.unistrong.geotsd.datasource.data.DataSourceBasicInfoEntity;
import com.unistrong.geotsd.datasource.data.Paging;
import com.unistrong.geotsd.datasource.service.DemoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 测试
 * Created by zc.shen on 2018/5/10.
 */
@Service("demoService")
public class DemoServiceImpl implements  DemoService {

    @Resource
    private DemoDao dao;

    @Override
    public String addNew(DataSourceBasicInfoEntity data) {
        data.setDataSourceId(UUID.randomUUID().toString().replace("-",""));
//        dao.addNew(data);
        return data.getDataSourceId();
    }

    @Override
    public List<DataSourceBasicInfoEntity> findAll(String id) {

        return dao.findAll(id);
    }

    @Override
    public Paging<DataSourceBasicInfoEntity> findByPage(Map<String ,Object> queryMap) {
        PageHelper.startPage(Integer.parseInt(queryMap.get("pageNum").toString()),Integer.parseInt(queryMap.get("pageSize").toString()));
        PageInfo<DataSourceBasicInfoEntity> pageInfo = new PageInfo<DataSourceBasicInfoEntity>(dao.findByPage());
        return new Paging(pageInfo.getPageNum(),pageInfo.getPageSize(),pageInfo.getTotal(),pageInfo.getPages(),pageInfo.getList());
    }
}
