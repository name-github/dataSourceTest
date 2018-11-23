package com.unistrong.geotsd.datasource.controller;

import com.unistrong.geotsd.datasource.utils.HttpRequest;

import static org.testng.Assert.*;

/**
 * @Author:
 * @Description
 * @Data:$time$ $date$
 */
public class DataSourceControllerTest {

    @org.testng.annotations.BeforeMethod
    public void setUp() {
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() {
    }

    @org.testng.annotations.Test
    public void testAddnew() {
        String url = "http://localhost:9090/geotsd/dsas/api/datasource/addnew";
        String postData= "{\"newDataBaseConnInfo\":{\"dataBaseType\":1,\"address\":\"beijing\",\"port\":3306,\"dataBaseName\":\"pg\",\"userName\":\"root\",\"password\":\"123\"}," +
                "\"newDataSourceBasicInfo\":{\"dataSourceType\":3,\"dataSourceName\":\"dsName\"},\"newFilesConnInfo\":{\"filePath\":\"home/file\",\"fileType\":0}," +
                "\"newInterfaceConnInfo\":{\"interfaceType\":2,\"interfaceAddress\":\"host\"}}";
       // System.out.println(param);
        String URL = HttpRequest.httpJson(postData,url);
    }

    @org.testng.annotations.Test
    public void testRemove() {
    }

    @org.testng.annotations.Test
    public void testRename() {
    }

    @org.testng.annotations.Test
    public void testUpdateDbConnInfo() {
    }

    @org.testng.annotations.Test
    public void testUpdateFileConnInfo() {
    }

    @org.testng.annotations.Test
    public void testUpdateInterfaceConnInfo() {
    }

    @org.testng.annotations.Test
    public void testFindAll() {
    }

    @org.testng.annotations.Test
    public void testGetBasicInfoById() {
    }
}