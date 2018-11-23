package com.unistrong.geotsd.datasource.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据校验工具类
 * Created by zc.shen on 2018/7/3
 */
public class DataValidatorUtils {

    /**
     * 是否是非空
     * @param value
     * @return 是返回true
     */
    public static boolean isNotempty(String value){
        Pattern p=null;//正则表达式
        Matcher m=null;//操作符表达式
        boolean b=false;
        p=p.compile("^\\S+$");
        m=p.matcher(value);
        b=m.matches();
        return b;
    }

    /**
     * 判断字符串长度数
     * @param value
     * @param length 长度范围
     * @return 在范围内返回true
     */
    public static boolean isStringLength(String value,int length){
        boolean b=false;
        if (!isNotempty(value)){//非空判断
            if (value.length()<=length){
                b=true;
            }
        }
        return b;
    }

    /**
     * 是否是正整数
     * @param value
     * @return 是返回true
     */
    public static boolean isIntege1(String value){
        Pattern p=null;//正则表达式
        Matcher m=null;//操作符表达式
        boolean b=false;
        p=p.compile("^[1-9]\\d*$");
        m=p.matcher(value);
        b=m.matches();
        return b;
    }

    /**
     * 是否是正数（正整数 + 0）
     * @param value
     * @return 是返回true
     */
    public static boolean isNum1(String value){
        Pattern p=null;//正则表达式
        Matcher m=null;//操作符表达式
        boolean b=false;
        p=p.compile("^[1-9]\\d*|0$");
        m=p.matcher(value);
        b=m.matches();
        return b;
    }

}
