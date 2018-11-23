package com.unistrong.geotsd.datasource.controller;

import com.unistrong.geotsd.datasource.common.Enum.FieldDataTypeEnum;
import com.unistrong.geotsd.datasource.common.Enum.ResultStateEnum;
import com.unistrong.geotsd.datasource.data.DataTableBasicInfoEntity;
import com.unistrong.geotsd.datasource.data.DataTableInfoEntity;
import com.unistrong.geotsd.datasource.data.FieldInfoEntity;
import com.unistrong.geotsd.datasource.data.Paging;
import com.unistrong.geotsd.datasource.service.FieldService;
import com.unistrong.geotsd.datasource.service.impl.DataTableServiceImpl;
import com.unistrong.geotsd.datasource.utils.DataValidatorUtils;
import com.unistrong.geotsd.datasource.utils.JsonUtils;
import com.unistrong.geotsd.datasource.utils.ResultJsonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author
 * @create 2018-06-29 17:15
 **/
@RestController
@RequestMapping({"/api/field"})
public class FieldController {

    private final Logger _log = LoggerFactory.getLogger(FieldController.class);
    @Autowired
    private FieldService fieldService;

    /**
     * 为指定数据表，批量添加字段接口
     */
    @RequestMapping(value = {"/addnews"},method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String addNews(@RequestBody Map map) {
        if (!DataValidatorUtils.isNotempty(map.get("dataTableId").toString())
                || map.get("newFieldInfoList") == null) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }

        List<FieldInfoEntity> newFieldInfoList = JsonUtils.toList(new JSONArray().fromObject(map.get("newFieldInfoList")), FieldInfoEntity.class);
        if (newFieldInfoList == null || newFieldInfoList.isEmpty()) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }

        //校验字段名的唯一性,和字段长度校验,字段类型校验
        List<String> fieldNames = new ArrayList<>();
        for (FieldInfoEntity field : newFieldInfoList) {
            if (field.getFieldName().length() > 50
                    || field.getFieldAlias().length() > 50
                    || FieldDataTypeEnum.getName(field.getFieldType()) == null) {
                return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
            }
            fieldNames.add(field.getFieldName());
        }
        Map<String, Object> condMap = new HashMap<String, Object>();
        condMap.put("dataTableId", map.get("dataTableId"));
        condMap.put("fieldNames", fieldNames.toArray());
        if (fieldService.isFieldExist(condMap)) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "308");
        }

        List<String> dtIds = fieldService.addNews(map.get("dataTableId").toString(), newFieldInfoList);
        return ResultJsonUtils.resultInfo(dtIds, ResultStateEnum.SUCCESS.getValue(), "200");
    }

    /**
     * 从指定数据表中，移除指定字段接口（支持批量）
     */
    @RequestMapping(value = {"/remove"},method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String remove(@RequestBody Map map){
        if(map.get("dataTableId") == null
                ||map.get("dataTableId").toString().equals("")
                ||map.get("fieldIds") == null
                ||map.get("fieldIds").toString().equals("")){
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(),"301");
        }

        String[] fieldIds = map.get("fieldIds").toString().split(",");

        Map<String,Object> cond = new HashMap<String,Object>();
        cond.put("dataTableId",map.get("dataTableId"));
        cond.put("fieldIds",fieldIds);
        if(fieldService.remove(cond))
        {
            _log.info("从数据表" + map.get("dataTableId").toString() + "中成功删除" + fieldIds.length + "个字段");
            return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(),"200");
        }
        _log.info("从数据表" + map.get("dataTableId").toString() + "删除字段失败");
        return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(),"305");
    }

    /**
     * 清空指定数据表内全部字段接口
     */
    @RequestMapping(value = {"/clear"},method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String clear(@RequestBody Map map){

        if(map.get("dataTableIds") == null || map.get("dataTableIds").toString().equals("")){
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(),"301");
        }

        String[] dataTableIds = map.get("dataTableIds").toString().split(",");

        Map<String,Object> cond = new HashMap<String,Object>();
        cond.put("dataTableIds",dataTableIds);
        if(fieldService.clearByDtIds(cond))
        {
            _log.info("清空字段成功");
            return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(),"200");
        }
        _log.info("清空字段失败");
        return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(),"305");
    }

    /**
     * 获取指定数据表的全部字段属性信息列表接口
     */
    @RequestMapping(value = {"/findallbydtid"},method = RequestMethod.GET)
    @ResponseBody
    public String findAllByDtId(String dataTableId) {

        if (dataTableId == null || dataTableId.equals("")) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }
        List<FieldInfoEntity> fieldInfo = fieldService.findByDtId(dataTableId);
        _log.info("从数据表" + dataTableId + "获取字段信息成功");
        return ResultJsonUtils.resultInfo(fieldInfo, ResultStateEnum.SUCCESS.getValue(), "200");
    }

    /**
     * 编辑指定字段的基本属性接口
     */
    @RequestMapping(value = {"/update"},method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public String update(@RequestBody FieldInfoEntity newFieldInfo) {
        if (newFieldInfo == null
                || newFieldInfo.getFieldId() == null
                || newFieldInfo.getFieldId().equals("")
                || FieldDataTypeEnum.getName(newFieldInfo.getFieldType()) == null
                || newFieldInfo.getFieldName().length() > 50
                || newFieldInfo.getFieldAlias().length() > 50) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "301");
        }

        //字段名称唯一性校验
        List<String> fieldNames = new ArrayList<>();
        fieldNames.add(newFieldInfo.getFieldName());
        Map<String, Object> condMap = new HashMap<String, Object>();
        String dtId = fieldService.getDtIdFromFieldId(newFieldInfo.getFieldId());
        condMap.put("dataTableId", dtId);
        condMap.put("fieldNames", fieldNames);
        if (fieldService.isFieldExist(condMap)) {
            return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(), "308");
        }

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("field", newFieldInfo);
        if(fieldService.update(queryMap)){
            _log.info("编辑字段" + newFieldInfo.getFieldId() + "成功");
            return ResultJsonUtils.resultInfo("", ResultStateEnum.SUCCESS.getValue(), "200");
        }

        _log.info("编辑字段" + newFieldInfo.getFieldId() + "失败");
        return ResultJsonUtils.resultInfo("", ResultStateEnum.ERROR.getValue(),"305");
    }
}