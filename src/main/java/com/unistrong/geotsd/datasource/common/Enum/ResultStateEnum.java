package com.unistrong.geotsd.datasource.common.Enum;

/**
 * 常量枚举值
 *
 * @author zc.shen
 * @created 2018-06-27
 */
public enum ResultStateEnum {
    SUCCESS("success"),//成功
    ERROR("error");//失败
    private String resultState;//返回结果声明

    private ResultStateEnum(String resultState) {
        this.resultState=resultState;
    }
    public String getValue()
    {
        return resultState;
    }
}
