package com.unistrong.geotsd.datasource.controller;

import com.unistrong.geotsd.datasource.utils.ExcelData;
import com.unistrong.geotsd.datasource.utils.HttpRequest;
import org.testng.annotations.*;


import java.util.Iterator;
import java.util.Map;


public class DemoControllerTest {
    int fag = 0;    //测试用例编号
    int method = 0; //测试方法编号
    String  url = "http://localhost:9090/geotsd/dsas/api/demo/test";
    String  requestParameter = "";

/*    @BeforeTest
    public  void beforeTest(){
        method++;
        System.out.println("第" + method + "个方法开始：");

    }
    @AfterTest
    public  void afterTest(){
            System.out.println("第" + method + "个方法执行结束！\n ***************");
    }*/

    @BeforeMethod
    public void beforeMethod() {
        fag++;
        System.out.println("开始执行第" + fag + "个用例：");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("第" + fag + "个用例执行结束！");
    }

    @DataProvider(name = "indexpost")
    public Iterator<Object[]> indexpost() throws Exception {
        return (Iterator<Object[]>) new ExcelData("DemoController", "indexpost");
    }
    @Test(dataProvider = "indexpost",priority = 1)
    public void testIndexpost(Map<String, String> params) {
         //通过map.keySet()方法，获取key值,在获取到value值
            //  String param = "dataSourceName=ceshirenyuan1&dataSourceType=1";
        requestParameter = "";
        for(String param:params.keySet()){
           if(params.get(param)!= null && params.get(param).length() > 0){
              requestParameter = requestParameter + param + "=" + params.get(param) + "&";
           }
        }
/*        //该方法获取的map值与excel存储的值方式不一致
        Set<String> set=params.keySet();
        for(Iterator<String> param=set.iterator();param.hasNext();){
            if(params.get(param)!= null && params.get(param).length() > 0){
                requestParameter = requestParameter + param + "=" + params.get(param) + "&";
            }
        }*/
        requestParameter = requestParameter.substring(0,requestParameter.length()-1);
        System.out.println(requestParameter);
        //调用接口进行测试
       String URL = HttpRequest.httpPost(url, requestParameter);

    }
    @DataProvider(name = "indexget")
    public Object[][] indexget(){
        return new Object[][]{
                {"test"},{"jianguo"},{"张力"},
        };
    }

    @Test(dataProvider = "indexget",priority = 2)
    public void testIndexget(String name) {
        String param = "name=" + name;
        System.out.println(param);
        String URL = HttpRequest.httpGet(url,param);
    }


}