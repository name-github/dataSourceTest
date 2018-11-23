package com.unistrong.geotsd.datasource.utils;

import com.unistrong.geotsd.datasource.common.Enum.DatabaseTypeEnum;
import com.unistrong.geotsd.datasource.data.DatabaseConnInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;

/**
 *  数据库连接测试校验工具
 * @author zc.shen
 * @version 1.0
 * @created 2018-06-27
 */
public class DatabaseConnTestUtils {

    /**
     * org.slf4j.Logger
     */
    private static Logger logger = LoggerFactory.getLogger(DatabaseConnTestUtils.class);

    /**
     * 方法说明: 判断是否连接成功
     * @param data
     * @return
     */
    public static Boolean isConnection(DatabaseConnInfoEntity data) {
        Boolean status=true;
        String driver="";
        String url="";
        String user=data.getUserName();
        String password=data.getPassword();
        // 定义连接
        if (data.getDataBaseType().equals(DatabaseTypeEnum.ORACLE.getDatabaseType())){//oracle数据库
            driver="oracle.jdbc.OracleDriver";
            url="jdbc:oracle:thin:@"+data.getAddress()+":"+data.getPort()+":ORCL";
        }
        try {
            // 加载驱动
            Class.forName(driver);
            DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            status=false;
            logger.error(e.getMessage());
        }
        return status;
    }
}
