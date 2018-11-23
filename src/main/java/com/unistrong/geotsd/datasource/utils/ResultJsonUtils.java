package com.unistrong.geotsd.datasource.utils;


import com.unistrong.geotsd.datasource.data.Paging;
import net.sf.json.JSONObject;

import java.util.List;

/**
 *  返回数据结果集
 * @author zc.shen
 * @created 2018-06-27
 */
public class ResultJsonUtils {

    /**
     * 方法说明:查询单条信息
     * @param resultData 主键
     * @param resultState 状态
     * @param resultCode 状态码
     * @return
     */
    public static String resultInfo(Object resultData,String resultState,String resultCode){
        JSONObject result=new JSONObject();
        result.put("resultData",resultData);
        result.put("resultState",resultState);
        result.put("resultCode",resultCode);
        return result.toString();
    }
}
