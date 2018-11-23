package com.unistrong.geotsd.datasource.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    /**
     * org.slf4j.Logger
     */
    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * map转成对象
     * @param map
     * @param beanClass
     * @return
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass)  {
        if (map == null){
            return null;
        }
        Object obj = null;
        try {
            obj = beanClass.newInstance();
        } catch (InstantiationException e) {
            logger.error(e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error(e.getMessage());
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            try {
                field.set(obj, map.get(field.getName()));
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        }
        return obj;
    }

    /**
     * 对象转成map
     * @param obj
     * @return
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                map.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        }
        return map;
    }


    /**
     * 文件流转字符串
     * @param multipartFile
     * @return
     */
    public static String fileStreamToString(MultipartFile multipartFile){
        if (multipartFile == null || multipartFile.isEmpty()){
            return null;
        }
        String resultStr = null;
        InputStream in = null;
        FileInputStream fis = null;
        String encoding = "UTF-8";
        Long filelength = multipartFile.getSize();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            in = multipartFile.getInputStream();
            fis = (FileInputStream) in;
            fis.read(filecontent);
            fis.close();
            in.close();
            resultStr = new String(filecontent, encoding);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return resultStr;
    }
}
