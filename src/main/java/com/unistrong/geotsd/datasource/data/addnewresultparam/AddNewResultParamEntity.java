package com.unistrong.geotsd.datasource.data.addnewresultparam;

import com.unistrong.geotsd.datasource.data.commonresultparam.Response;

/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 新建数据源-返回参数
 */
public class AddNewResultParamEntity extends Response {
    private String dataSourceId;

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }
}
