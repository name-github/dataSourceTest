package com.unistrong.geotsd.datasource.common.Enum;

/**
 * @Auther: wangshuo
 * @Date: 2018/7/4
 * @Description: 数据源类型
 */
public enum DataSourceTypeEnum {

    DATABASE("数据库", 1), FILE("文件", 2), INTERFACE("接口", 3);

    private final String name;
    private final Integer value;

    private DataSourceTypeEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
