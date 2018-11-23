package com.unistrong.geotsd.datasource.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unistrong.geotsd.datasource.common.Enum.DataSourceTypeEnum;
import com.unistrong.geotsd.datasource.common.Enum.ResultStateEnum;
import com.unistrong.geotsd.datasource.data.*;
import com.unistrong.geotsd.datasource.service.DataSourceService;
import com.unistrong.geotsd.datasource.service.DataTableService;
import com.unistrong.geotsd.datasource.utils.DataValidatorUtils;
import com.unistrong.geotsd.datasource.utils.ResultJsonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 数据源Controller
 */
@RestController
@RequestMapping("/api/datasource")
public class DataSourceController {

    // 数据源service
    @Autowired
    private DataSourceService dataSourceService;

    // 数据表service
    @Autowired
    private DataTableService dataTableService;

    /**
     * 新建数据源
     *
     * @param paramEntity 请求参数
     */

    @RequestMapping(value = "/addnew", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addNew(@RequestBody ParamEntity paramEntity) {
        // 校验 请求参数不可为空
        if (paramEntity.getNewDataSourceBasicInfo() == null || paramEntity.getNewDataBaseConnInfo() == null ||
                paramEntity.getNewFilesConnInfo() == null || paramEntity.getNewInterfaceConnInfo() == null) {
            // 返回错误参数
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }
        // 返回保存成功的数据源ID
        String dataSourceId = dataSourceService.addNew(paramEntity);
        Map map = new HashMap();
        map.put("dataSourceId", dataSourceId);
        // 设置正确返回参数
        return ResultJsonUtils.resultInfo(map, ResultStateEnum.SUCCESS.getValue(), "200");
    }

    /**
     * 移除指定数据源
     * 支持批量删除
     *
     * @param dataSourceIds
     */
    @RequestMapping(value = "/remove", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String remove(@RequestBody String dataSourceIds) {
        try {
            // 校验 请求参数不可为空
            if (StringUtils.isEmpty(dataSourceIds)) {
                return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
            }
            ObjectMapper objectMapper = new ObjectMapper();
            Map map = objectMapper.readValue(dataSourceIds, Map.class);
            dataSourceIds = map.get("dataSourceIds").toString();
            String[] dataSourceIdArr = dataSourceIds.split(",");
            List<String> dataSourceIdList = Arrays.asList(dataSourceIdArr);
            dataSourceService.remove(dataSourceIdList);
            // 设置正确返回参数
            return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(), "200");
        } catch (Exception e) {
            // 设置错误返回参数
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }
    }

    /**
     * 按主键重命名
     *
     * @param dataSourceBasicInfoEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/rename", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String rename(@RequestBody DataSourceBasicInfoEntity dataSourceBasicInfoEntity) {
        String dataSourceId = dataSourceBasicInfoEntity.getDataSourceId();
        String dataSourceName = dataSourceBasicInfoEntity.getDataSourceName();
        // 校验 请求参数不可为空
        if (StringUtils.isEmpty(dataSourceId) || StringUtils.isEmpty(dataSourceName)) {
            // 设置错误返回参数
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }
        dataSourceService.rename(dataSourceBasicInfoEntity);
        // 设置正确返回参数
        return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(), "200");
    }

    /**
     * 按主键更新数据源连接信息
     *
     * @param databaseConnInfoEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatedbconninfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateDbConnInfo(@RequestBody DatabaseConnInfoEntity databaseConnInfoEntity) {
        // 校验 数据源ID不可为空
        if (databaseConnInfoEntity == null || StringUtils.isEmpty(databaseConnInfoEntity.getDataSourceId())) {
            // 设置错误返回参数
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }
        dataSourceService.updateDataBaseConInfo(databaseConnInfoEntity);
        // 设置正确返回参数
        return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(), "200");
    }

    /**
     * 按主键更新数据源文件连接信息
     *
     * @param filesConnInfoEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatefileconninfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateFileConnInfo(@RequestBody FilesConnInfoEntity filesConnInfoEntity) {
        // 校验 数据源ID不可为空
        if (StringUtils.isEmpty(filesConnInfoEntity.getDataSourceId())) {
            // 设置错误返回参数
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }
        dataSourceService.updateFileConnInfo(filesConnInfoEntity);
        //设置正确返回参数
        return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(), "200");
    }

    /**
     * 按主键更新数据源接口连接信息
     *
     * @param interfaceConnInfoEntity
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateinterfconninfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateInterfaceConnInfo(@RequestBody InterfaceConnInfoEntity interfaceConnInfoEntity) {
        // 校验 数据源ID不可为空
        if (StringUtils.isEmpty(interfaceConnInfoEntity.getDataSourceId())) {
            // 设置错误返回参数
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }
        dataSourceService.updateInterfaceConnInfo(interfaceConnInfoEntity);
        //设置正确返回参数
        return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(), "200");
    }

    /**
     * 查询数据源基本信息
     * 支持分页
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/findallindex", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String findAllIndex(@RequestBody Map<String, Integer> map) {
        Paging<DataSourceBasicInfoEntity> pageInfo = dataSourceService.findByPage(map);
        //设置正确返回参数
        return ResultJsonUtils.resultInfo(pageInfo, ResultStateEnum.SUCCESS.getValue(), "200");
    }

    /**
     * 按关键字OR数据源类型检索数据源基本信息
     * 支持分页
     *
     * @param jsonParams
     * @return
     */
    @RequestMapping(value = "/findbycond", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String findByCond(@RequestBody String jsonParams) {
        try {
            Object result = dataSourceService.findDataSourceBasicConnInfoByCond(jsonParams);
            //设置正确返回参数
            return ResultJsonUtils.resultInfo(result, ResultStateEnum.SUCCESS.getValue(), "200");
        } catch (Exception e) {
            //设置错误返回参数
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }

    }

    /**
     * 按主键查询数据源基本信息
     *
     * @param dataSourceId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getbasicinfobyid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getBasicInfoById(String dataSourceId) {
        // 校验 数据源ID不可为空
        if (StringUtils.isEmpty(dataSourceId)) {
            // 设置错误返回参数
            return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(), "301");
        }
        DataSourceBasicInfoEntity dataSourceBasicInfoEntityN = dataSourceService.getDataSourceBasicConnById(dataSourceId);
        Map<String, Object> map = new HashMap<>();
        map.put("dataSourceBasicInfo", dataSourceBasicInfoEntityN);
        if (dataSourceBasicInfoEntityN.getDataSourceType().equals(DataSourceTypeEnum.DATABASE.getValue())) {
            // 数据库连接信息
            DatabaseConnInfoEntity dataBaseConInfoEntityN = dataSourceService.getDataBaseConInfoById(dataSourceId);
            map.put("dataBaseConnInfo", dataBaseConInfoEntityN);
        } else if (dataSourceBasicInfoEntityN.getDataSourceType().equals(DataSourceTypeEnum.FILE.getValue())) {
            // 文件信息
            FilesConnInfoEntity filesConnInfoEntityN = dataSourceService.getFilesConnInfoById(dataSourceId);
            map.put("filesConnInfo", filesConnInfoEntityN);
        } else if (dataSourceBasicInfoEntityN.getDataSourceType().equals(DataSourceTypeEnum.INTERFACE.getValue())) {
            // 接口信息
            InterfaceConnInfoEntity interfaceConnInfoEntityN = dataSourceService.getInterfaceInfoById(dataSourceId);
            map.put("interfaceConnInfo", interfaceConnInfoEntityN);
        }
        // 设置正确返回参数
        return ResultJsonUtils.resultInfo(map, ResultStateEnum.SUCCESS.getValue(), "200");
    }

    /**
     * 根据数据源唯一标识，获取指定数据源全部信息接口
     */
    @RequestMapping(value = {"/getallinfobyid"}, method = RequestMethod.GET)
    public String getAllInfoById(String dataSourceId) {
        if (!DataValidatorUtils.isNotempty(dataSourceId)) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }
        Map<String, Object> resData = dataSourceService.getAllInfoById(dataSourceId);
        List<DataTableInfoEntity> tableList = dataTableService.findAllByDsId(dataSourceId);
        resData.put("tableAllInfo", tableList);
        return ResultJsonUtils.resultInfo(resData, ResultStateEnum.SUCCESS.getValue(), "200");
    }

}
