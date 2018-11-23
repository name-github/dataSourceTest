package com.unistrong.geotsd.datasource.utils;


import java.util.List;
import net.sf.json.JSONArray;
/**
 * @author zt
 * @create 2018-07-05 10:14
 **/
public class JsonUtils {

    /***
     * 将JSON对象数组转换为传入类型的List
     *
     * @param
     * @param jsonArray
     * @param objectClass
     * @return
     */
    public static <T> List<T> toList(JSONArray jsonArray, Class<T> objectClass) {
        return (List<T>) JSONArray.toCollection(jsonArray, objectClass);
    }


}