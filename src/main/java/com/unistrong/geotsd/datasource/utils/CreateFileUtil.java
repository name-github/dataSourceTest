package com.unistrong.geotsd.datasource.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 导出文件工具类
 *
 * @author zc.shen
 * @version 1.0
 * @created 2018-06-27
 */
public class CreateFileUtil {

    /**
     * org.slf4j.Logger
     */
    private static Logger logger = LoggerFactory.getLogger(CreateFileUtil.class);

    /**
     * 生成.json格式文件
     * @param jsonString
     * @return
     */
    public static boolean createJsonFile(String jsonString, String fileName, String filePath) {
        // 标记文件生成是否成功
        boolean flag = true;
        // 拼接文件完整路径
        String fullPath = filePath + File.separator + fileName + ".json";
        // 生成json格式文件
        try {
            // 保证创建一个新文件
            File file = new File(fullPath);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();
            // 格式化json字符串
            jsonString = JsonFormatTool.formatJson(jsonString);
            // 将格式化后的字符串写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            logger.error(e.getMessage());
        }

        // 返回是否成功的标记
        return flag;
    }

    /**
     * file转MultipartFile
     * @return
     */
    public static MultipartFile fileToMultipartFile(String filePath) {
        File file = new File(filePath);
        MultipartFile multipartFile = null;
        try {
            FileInputStream inputStream = new FileInputStream(file);
            multipartFile = new MockMultipartFile(file.getName(), inputStream);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
        return multipartFile;
    }

    /**
     * 防止中文文件名显示出错
     * @param cnName
     * @param defaultName
     * @return
     */
    public static String genAttachmentFileName(String cnName, String defaultName) {
        try {
            cnName = new String(cnName.getBytes("GBK"), "ISO-8859-1");
        } catch (Exception e) {
            cnName = defaultName;
        }
        return cnName;
    }

}
