package com.unistrong.geotsd.datasource.controller;

import com.unistrong.geotsd.datasource.common.Enum.ResultStateEnum;
import com.unistrong.geotsd.datasource.common.client.FileResponseData;
import com.unistrong.geotsd.datasource.data.DataSourceBasicInfoEntity;
import com.unistrong.geotsd.datasource.data.DataTableInfoEntity;
import com.unistrong.geotsd.datasource.data.Paging;
import com.unistrong.geotsd.datasource.service.DataSourceService;
import com.unistrong.geotsd.datasource.service.DataTableService;
import com.unistrong.geotsd.datasource.service.DemoService;
import com.unistrong.geotsd.datasource.service.FileObjectService;
import com.unistrong.geotsd.datasource.utils.DataValidatorUtils;
import com.unistrong.geotsd.datasource.utils.ResultJsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试
 *
 * @author zc.shen
 * @version 1.0
 * @created 2018-06-27
 */
@RestController
@RequestMapping({"/demo"})
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private DataSourceService dataSourceService;//数据源service

    @Autowired
    private DataTableService dataTableService;//数据表service

    @Autowired
    private FileObjectService fileObjectService;//数据表service

    /**
     * 添加接口
     */
    @RequestMapping(value = {"/test"}, method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String index(String dataSourceName, Integer dataSourceType) {
        Map<String, Object> queryMap = new HashMap<>();
    /*     DataSourceBasicInfoEntity data=new DataSourceBasicInfoEntity();
        data.setDataSourceName(dataSourceName);
        data.setDataSourceType(dataSourceType);
        data.setDataSourceName("测试123");
        data.setDataSourceType(1);
        return ResultJsonUtils.resultInfo(demoService.addNew(data), ResultStateEnum.SUCCESS.getValue(),"200");*/
        //System.out.println(DatabaseTypeEnum.ORACLE.getDatabaseType());
/*        DatabaseConnInfoEntity database=new DatabaseConnInfoEntity();
        database.setAddress("192.168.251.213");
        database.setDataBaseType(1);
        database.setPort(1521);
        database.setUserName("unistrong");
        database.setPassword("admin");
        Boolean se=DatabaseConnTestUtils.isConnection(database);
        System.out.println(se);*/
        queryMap.put("pageNum", 2);
        queryMap.put("pageSize", 5);
        Paging<DataSourceBasicInfoEntity> pageInfo = demoService.findByPage(queryMap);
        return ResultJsonUtils.resultInfo(pageInfo, ResultStateEnum.SUCCESS.getValue(), "200");
    }

    @RequestMapping(value = {"/test"}, method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String indexget(String name) {
        return "获取得到的参数值：" + name;
    }

    /**
     * 导出json文件
     */
    @RequestMapping(value = {"/export"}, method = RequestMethod.GET)
    public void export(String dataSourceId, HttpServletResponse response) {
        List<DataTableInfoEntity> tableList=dataTableService.findAllByDsId(dataSourceId);
        //CreateFileUtil.createJsonFile(JSONArray.fromObject(tableList).toString(),"test");
        //CreateFileUtil.fileUpload();
    }

    /**
     * 方法说明：批量导入
     *
     * @param request
     * @param multipartFile
     * @return
     */
    // @RequestMapping("/addnewsbyjsonfile")
    @RequestMapping(value = "/addnewsbyjsonfile", method = RequestMethod.POST)
    public boolean importJsonFile(HttpServletRequest request,
                                  @RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty())
            return false;
        FileResponseData FileResponseData=fileObjectService.uploadFileSample(multipartFile,request);
/*        InputStream in = null;
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
            System.out.println(new String(filecontent, encoding));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return true;
    }

    /**
     * 根据数据源唯一标识，获取指定数据源全部信息接口
     */
    @RequestMapping(value = {"/getallinfobyid"}, method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String getAllInfoById(String dataSourceId) {
        if (DataValidatorUtils.isNotempty(dataSourceId))
            return "";
        demoService.findAll(dataSourceId);
        return "";
    }

    /**
     * 测试用map接收参数
     * @param map
     * @return
     */
    @RequestMapping(value = "/addnew", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String addnew(@RequestBody Map map) {
        System.out.println(map.get("dataBaseType"));
        return "";
    }

}
