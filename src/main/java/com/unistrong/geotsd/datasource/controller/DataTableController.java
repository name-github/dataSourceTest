package com.unistrong.geotsd.datasource.controller;

import com.unistrong.geotsd.datasource.common.Enum.FieldDataTypeEnum;
import com.unistrong.geotsd.datasource.common.Enum.ResultStateEnum;
import com.unistrong.geotsd.datasource.common.client.FileResponseData;
import com.unistrong.geotsd.datasource.data.*;
import com.unistrong.geotsd.datasource.service.DataTableService;
import com.unistrong.geotsd.datasource.service.FileObjectService;
import com.unistrong.geotsd.datasource.utils.*;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author
 * @create 2018-06-29 13:34
 **/
@RestController
@RequestMapping({"/api/datatable"})
public class DataTableController {

    private final Logger _log = LoggerFactory.getLogger(DataTableController.class);

    @Autowired
    private DataTableService dataTableService;

    @Autowired
    private FileObjectService fileObjectService;//数据表service
    /**
     * 从指定数据源内，移除指定数据表接口（支持批量）
     */
    @RequestMapping(value = {"/remove"},method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String remove(@RequestBody Map map){

        if(map.get("dataSourceId") == null
                ||map.get("dataSourceId").toString().equals("")
                ||map.get("dataTableIds") == null
                ||map.get("dataTableIds").toString().equals("")){
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(),"301");
        }

        String[] dataTableIds = map.get("dataTableIds").toString().split(",");

        //验证dataSourceId以及dataTableIds是否存在
        if(!dataTableService.isDataSourceExist(map.get("dataSourceId").toString())
                || !dataTableService.isDataTableExist(Arrays.asList(dataTableIds))) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "309");
        }

        Map<String,Object> cond = new HashMap<String,Object>();
        cond.put("dataSourceId",map.get("dataSourceId"));
        cond.put("dataTableIds",dataTableIds);
        if(dataTableService.remove(cond))
        {
            _log.info("从数据源" + map.get("dataSourceId").toString() + "中成功删除" + dataTableIds.length + "个数据表");
            return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(),"200");
        }
        _log.info("从数据源" + map.get("dataSourceId").toString() + "删除数据表失败");
        return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(),"305");
    }

    /**
     * 清空指定数据源内全部数据表接口
     */
    @RequestMapping(value = {"/clear"},method = RequestMethod.GET)
    @ResponseBody
    public String clear(String dataSourceId){

        if(dataSourceId == null || dataSourceId.equals("")){
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(),"301");
        }

        Map<String,Object> cond = new HashMap<String,Object>();
        cond.put("dataSourceId",dataSourceId);
        if(dataTableService.clear(cond))
        {
            _log.info("从数据源" + dataSourceId + "清空数据表成功");
            return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(),"200");
        }
        _log.info("从数据源" + dataSourceId + "清空数据表失败");
        return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(),"305");
    }

    /**
     * 根据唯一标识，获取指定数据表全部信息接口
     */
    @RequestMapping(value = {"/getbyid"},method = RequestMethod.GET)
    @ResponseBody
    public String getById(String dataTableId) {

        if (dataTableId == null || dataTableId.equals("")) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(),"301");
        }
        DataTableInfoEntity dataTableInfo = dataTableService.getById(dataTableId);

        _log.info("从数据表" + dataTableId + "获取全部信息成功");
        return ResultJsonUtils.resultInfo(dataTableInfo, ResultStateEnum.SUCCESS.getValue(),"200");
    }

    /**
     * 获取指定数据源内全部数据表基本属性列表接口（支持分页）
     */
    @RequestMapping(value = {"/findallbydsid"},method = RequestMethod.POST,produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String findAllByDsId(@RequestBody Map map) {

        if (map.get("dataSourceId") == null || map.get("dataSourceId").toString().equals("")) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("pageNum",map.get("pageNum"));
        queryMap.put("pageSize",map.get("pageSize"));
        queryMap.put("dataSourceId", map.get("dataSourceId"));
        Paging<DataTableBasicInfoEntity> dataTableInfo = dataTableService.findAllInfoByDsId(queryMap);
        _log.info("从数据源" + map.get("dataSourceId") + "获取数据表信息成功");
        return ResultJsonUtils.resultInfo(dataTableInfo, ResultStateEnum.SUCCESS.getValue(), "200");
    }

    /**
     * 根据关键字检索指定数据源内数据表接口（支持分页）
     */
    @RequestMapping(value = {"/findbykw"},method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String findByKW(@RequestBody Map map) {

        if (map.get("dataSourceId") == null || map.get("dataSourceId").toString().equals("")) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }
        Date startDate = null;
        Date endDate = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            if(map.get("startTime") != null && !map.get("startTime").equals("")) {
                startDate = df.parse(map.get("startTime").toString());
            }
            if(map.get("endTime") != null && !map.get("endTime").equals("")) {
                endDate = df.parse(map.get("endTime").toString());
            }
        }catch (Exception e){
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("pageNum", map.get("pageNum"));
        queryMap.put("pageSize", map.get("pageSize"));
        queryMap.put("dataSourceId", map.get("dataSourceId"));
        queryMap.put("keyWords",map.get("keyWords"));
        queryMap.put("startTime",startDate);
        queryMap.put("endTime",endDate);
        Paging<DataTableBasicInfoEntity> dataTableInfo = dataTableService.findByKW(queryMap);
        _log.info("从数据源" + map.get("dataSourceId") + "获取数据表信息成功");
        return ResultJsonUtils.resultInfo(dataTableInfo, ResultStateEnum.SUCCESS.getValue(), "200");
    }


    /**
     * 为指定数据源，批量添加多个数据表接口（文件形式）
     */
    @RequestMapping(value = {"/addnewsbyjsonfile"},method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String addNews(@RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        if (multipartFile == null) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }
        String dsId = null;
        String tableListJson = CommonUtils.fileStreamToString(multipartFile);
        List<DataTableInfoEntity> newDataTableInfoList = JsonUtils.toList(new JSONArray().fromObject(tableListJson), DataTableInfoEntity.class);
        if (newDataTableInfoList == null || newDataTableInfoList.isEmpty()) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }

        //校验表名和字段名的唯一性,以及长度校验
        List<String> tableNames = new ArrayList<>();
        for (DataTableInfoEntity table : newDataTableInfoList) {
            if (table.getDataTableBasicInfo().getDataSourceId() == null
                    || table.getDataTableBasicInfo().getDataSourceId().equals("")
                    || table.getDataTableBasicInfo().getDataTableName().length() > 50
                    || table.getDataTableBasicInfo().getDataTablePK().length() > 50
                    || table.getDataTableBasicInfo().getDataTableAlias().length() > 50) {
                return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
            }
            dsId = table.getDataTableBasicInfo().getDataSourceId();
            tableNames.add(table.getDataTableBasicInfo().getDataTableName());
            if (table.getFieldList() == null || table.getFieldList().size() < 1) {
                continue;
            }
            List<String> fieldNames = new ArrayList<>();
            List<FieldInfoEntity> fieldList = JsonUtils.toList(new JSONArray().fromObject(table.getFieldList()), FieldInfoEntity.class);
            table.setFieldList(fieldList);
            for (FieldInfoEntity field : fieldList) {
                if (fieldNames.contains(field.getFieldName())) {
                    return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "308");
                }
                if (FieldDataTypeEnum.getName(field.getFieldType()) == null) {
                    return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
                }
                fieldNames.add(field.getFieldName());
            }
        }
        Map<String, Object> condMap = new HashMap<String, Object>();
        condMap.put("dataSourceId", dsId);
        condMap.put("dataTableNames", tableNames.toArray());
        if (dataTableService.isTableExist(condMap)) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "308");
        }
        List<String> dtIds = dataTableService.addNews(dsId, newDataTableInfoList);
        return ResultJsonUtils.resultInfo(dtIds, ResultStateEnum.SUCCESS.getValue(), "200");
    }


    /**
     * 为指定数据源，批量添加多个数据表接口
     */
    @RequestMapping(value = {"/addnews"},method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String addNews(@RequestBody Map map) {
        if (!DataValidatorUtils.isNotempty(map.get("dataSourceId").toString())
                || map.get("newDataTableInfoList") == null) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }

        List<DataTableInfoEntity> newDataTableInfoList = JsonUtils.toList(new JSONArray().fromObject(map.get("newDataTableInfoList")),DataTableInfoEntity.class);

        //校验表名和字段名的唯一性,以及长度校验
        List<String> tableNames = new ArrayList<>();
        for (DataTableInfoEntity table : newDataTableInfoList) {
            if(table.getDataTableBasicInfo().getDataTableName().length()>50
                    ||table.getDataTableBasicInfo().getDataTablePK().length() >50
                    ||table.getDataTableBasicInfo().getDataTableAlias().length() >50){
                return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
            }
            tableNames.add(table.getDataTableBasicInfo().getDataTableName());
            if (table.getFieldList() == null || table.getFieldList().size() < 1) {
                continue;
            }
            List<String> fieldNames = new ArrayList<>();
            List<FieldInfoEntity> fieldList = JsonUtils.toList(new JSONArray().fromObject(table.getFieldList()),FieldInfoEntity.class);
            table.setFieldList(fieldList);
            for(FieldInfoEntity field : fieldList) {
                if (fieldNames.contains(field.getFieldName())){
                    return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "308");
                }
                if(FieldDataTypeEnum.getName(field.getFieldType()) == null){
                    return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
                }
                fieldNames.add(field.getFieldName());
            }
        }
        Map<String, Object> condMap = new HashMap<String, Object>();
        condMap.put("dataSourceId", map.get("dataSourceId"));
        condMap.put("dataTableNames", tableNames.toArray());
        if(dataTableService.isTableExist(condMap)){
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "308");
        }
        List<String> dtIds = dataTableService.addNews(map.get("dataSourceId").toString(),newDataTableInfoList);
        return ResultJsonUtils.resultInfo(dtIds, ResultStateEnum.SUCCESS.getValue(), "200");
    }

    /**
     * 编辑指定数据表的基本属性接口
     */
    @RequestMapping(value = {"/update"},method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String update(@RequestBody DataTableBasicInfoEntity newDataTableBasicInfo) {
        if (newDataTableBasicInfo == null
                || newDataTableBasicInfo.getDataTableId() == null
                || newDataTableBasicInfo.getDataTableId().equals("")
                || newDataTableBasicInfo.getDataTableName().length() > 50
                || newDataTableBasicInfo.getDataTableAlias().length() >50
                || newDataTableBasicInfo.getDataTablePK().length() >50) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }

        //数据表名称唯一性校验
        Map<String, Object> condMap = new HashMap<String, Object>();
        List<String> tableNames = new ArrayList<String >();
        tableNames.add( newDataTableBasicInfo.getDataTableName());
        String dsId = dataTableService.getDsIdFromDtId(newDataTableBasicInfo.getDataTableId());
        condMap.put("dataSourceId", dsId);
        condMap.put("dataTableNames", tableNames);
        if(dataTableService.isTableExist(condMap)){
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "308");
        }

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("dataTable", newDataTableBasicInfo);
        if(dataTableService.update(queryMap)){
            _log.info("编辑数据表" + newDataTableBasicInfo.getDataTableId() + "成功");
            return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(), "200");
        }

        _log.info("编辑数据表" + newDataTableBasicInfo.getDataTableId() + "失败");
        return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(),"305");
    }

    /**
     * 将指定数据源内，指定数据表集合生成为json格式文件并上传接口
     */
    @RequestMapping(value = {"/upload"},method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String upload(@RequestBody Map map, HttpServletRequest request) {
        if (map.get("dataSourceId") == null
                || map.get("dataSourceId").toString().equals("")
                || map.get("dataTableIds") == null
                || map.get("dataTableIds").toString().equals("")) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }

        String[] dataTableIds = map.get("dataTableIds").toString().split(",");

        Map<String,Object> condMap = new HashMap();
        condMap.put("dataSourceId",map.get("dataSourceId"));
        condMap.put("dataTableIds",dataTableIds);
        List<DataTableInfoEntity> tableList = dataTableService.findAllByDsIdAndDtIds(condMap);
        String filePath = "";
        String fileName = "temp";
        String split = "\\";
        String suffix = ".json";

        try {
            filePath = new File("").getCanonicalPath();
        } catch (IOException e) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "305");
        }

        if(CreateFileUtil.createJsonFile(JSONArray.fromObject(tableList).toString(), fileName,filePath)){
            String fullPath = filePath + split + fileName + suffix;
            MultipartFile multipartFile = CreateFileUtil.fileToMultipartFile(fullPath);
            FileResponseData fileResponseData=fileObjectService.uploadFileSample(multipartFile,request);
            return ResultJsonUtils.resultInfo(fileResponseData.getHttpUrl(), ResultStateEnum.SUCCESS.getValue(),"200");
        }
        return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "305");
    }
}