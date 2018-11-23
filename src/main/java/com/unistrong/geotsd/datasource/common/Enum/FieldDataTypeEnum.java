package com.unistrong.geotsd.datasource.common.Enum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zt
 * @create 2018-06-29 10:35
 **/
public enum FieldDataTypeEnum {
    NUMBER("number",1),
    STRING("string",2),
    TIMES("times",3),
    POINT("point",4);

    // 字段类型名称
    private String typeName;
    // 字段类型索引
    private int typeIndex;


    public static String getName(int index) {
        for (FieldDataTypeEnum c : FieldDataTypeEnum.values()) {
            if (c.getTypeIndex() == index) {
                return c.typeName;
            }
        }
        return null;
    }

    FieldDataTypeEnum( String typeName,int typeIndex) {
        this.typeName = typeName;
        this.typeIndex = typeIndex;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getTypeIndex() {
        return typeIndex;
    }
}