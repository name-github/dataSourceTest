package com.unistrong.geotsd.datasource.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.unistrong.geotsd.datasource.common.Enum.DataSourceTypeEnum;
import com.unistrong.geotsd.datasource.dao.DataBaseConnInfoDao;
import com.unistrong.geotsd.datasource.dao.DataSourceBasicInfoDao;
import com.unistrong.geotsd.datasource.dao.FilesConnInfoDao;
import com.unistrong.geotsd.datasource.dao.InterfaceConnInfoDao;
import com.unistrong.geotsd.datasource.data.*;
import com.unistrong.geotsd.datasource.service.DataSourceService;
import com.unistrong.geotsd.datasource.utils.CommonUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 数据源Service实现类
 */
@Service("dataSourceService")
public class DataSourceServiceImpl implements DataSourceService {

    // 数据源基本信息DAO
    @Resource
    private DataSourceBasicInfoDao dataSourceBasicInfoDao;

    // 数据库连接信息DAO
    @Resource
    private DataBaseConnInfoDao dataBaseConnInfoDao;

    // 数据库文件信息DAO
    @Resource
    private FilesConnInfoDao filesConnInfoDao;

    // 数据库接口信息DAO
    @Resource
    private InterfaceConnInfoDao interfaceConnInfoDao;


    /**
     * 新建数据源
     *
     * @param paramEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addNew(ParamEntity paramEntity) {
        // 保存数据源基本信息
        DataSourceBasicInfoEntity dataSourceBasicInfoEntity = paramEntity.getNewDataSourceBasicInfo();
        // 设置dataSourceId
        UUID uuid = UUID.randomUUID();
        String dataSourceId = uuid.toString();
        dataSourceId = dataSourceId.replace("-", "");
        dataSourceBasicInfoEntity.setDataSourceId(dataSourceId);
        // 设置时间戳
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        dataSourceBasicInfoEntity.setModifyTime(timestamp.toString());
        dataSourceBasicInfoDao.addnewByDsBasicInfo(dataSourceBasicInfoEntity);
        // dataSourceType为1时，保存数据库连接信息
        if (paramEntity.getNewDataSourceBasicInfo().getDataSourceType().equals(DataSourceTypeEnum.DATABASE.getValue())) {
            // 保存数据库访问信息
            DatabaseConnInfoEntity dataBaseConnInfoEntity = paramEntity.getNewDataBaseConnInfo();
            dataBaseConnInfoEntity.setDataSourceId(dataSourceId);
            dataBaseConnInfoDao.addnewByDbBasicConnInfo(dataBaseConnInfoEntity);
        }
        // dataSourceType为2时，保存文件访问信息
        else if (paramEntity.getNewDataSourceBasicInfo().getDataSourceType().equals(DataSourceTypeEnum.FILE.getValue())) {
            // 保存文件访问信息
            FilesConnInfoEntity filesConnInfoEntity = paramEntity.getNewFilesConnInfo();
            filesConnInfoEntity.setDataSourceId(dataSourceId);
            filesConnInfoDao.addnewByFilesConnInfo(filesConnInfoEntity);
        }
        // dataSourceType为3时，保存接口访问信息
        else if (paramEntity.getNewDataSourceBasicInfo().getDataSourceType().equals(DataSourceTypeEnum.INTERFACE.getValue())) {
            // 保存接口访问信息
            InterfaceConnInfoEntity interfaceConnInfoEntity = paramEntity.getNewInterfaceConnInfo();
            interfaceConnInfoEntity.setDataSourceId(dataSourceId);
            interfaceConnInfoDao.addnewByInterfaceConnInfo(interfaceConnInfoEntity);
        }
        return dataSourceBasicInfoEntity.getDataSourceId();
    }


    /**
     * 删除数据源
     *
     * @param dataSourceIdList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<String> dataSourceIdList) {
        for (String dataSourceId : dataSourceIdList) {
            // 按主键查询，校验是否为空
            DataSourceBasicInfoEntity dataSourceBasicInfoEntityF = dataSourceBasicInfoDao.findDataSourceBasicInfoById(dataSourceId);
            if (dataSourceBasicInfoEntityF != null) {
                // 删除数据源基本信息
                dataSourceBasicInfoDao.removeDataSourceBasicInfoById(dataSourceId);
                if (dataSourceBasicInfoEntityF.getDataSourceType().equals(DataSourceTypeEnum.DATABASE.getValue())) {
                    // 删除数据库连接信息
                    dataBaseConnInfoDao.removeDataBaseConnInfoById(dataSourceId);
                } else if (dataSourceBasicInfoEntityF.getDataSourceType().equals(DataSourceTypeEnum.FILE.getValue())) {
                    // 删除文件连接信息
                    filesConnInfoDao.removeFilesConnInfoById(dataSourceId);
                } else if (dataSourceBasicInfoEntityF.getDataSourceType().equals(DataSourceTypeEnum.INTERFACE.getValue())) {
                    //删除接口连接信息
                    interfaceConnInfoDao.removeInterfaceConnInfoById(dataSourceId);
                }
            }
        }
    }

    /**
     * 按主键重命名数据源名称
     *
     * @param dataSourceBasicInfoEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void rename(DataSourceBasicInfoEntity dataSourceBasicInfoEntity) {
        Timestamp modifyTime = new Timestamp(System.currentTimeMillis());
        dataSourceBasicInfoEntity.setModifyTime(modifyTime.toString());
        dataSourceBasicInfoDao.updateDataSourceBasicInfoById(dataSourceBasicInfoEntity);
    }

    /**
     * 按主键更新数据库连接信息
     *
     * @param dataBaseConInfoEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDataBaseConInfo(DatabaseConnInfoEntity dataBaseConInfoEntity) {
        dataBaseConnInfoDao.updateDataBaseConnInfoById(dataBaseConInfoEntity);
    }

    /**
     * 按主键更新文件连接信息
     *
     * @param filesConnInfoEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFileConnInfo(FilesConnInfoEntity filesConnInfoEntity) {
        filesConnInfoDao.updateFilesConnInfoById(filesConnInfoEntity);
    }

    /**
     * 按主键更新接口连接信息
     *
     * @param interfaceConnInfoEntity
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateInterfaceConnInfo(InterfaceConnInfoEntity interfaceConnInfoEntity) {
        interfaceConnInfoDao.updateInterfaceConnInfoById(interfaceConnInfoEntity);
    }

    /**
     * 按主键查询单条数据源全部信息
     *
     * @param dataSourceId
     * @return
     */
    @Override
    public Map getAllInfoById(String dataSourceId) {
        Map<String, Object> resMap = null;
        Map<String, Object> connMap = null;
        DataSourceBasicInfoEntity dataSourceBasicInfoEntity = dataSourceBasicInfoDao.findDataSourceBasicInfoById(dataSourceId);
        if (dataSourceBasicInfoEntity.getDataSourceType().equals(DataSourceTypeEnum.DATABASE.getValue())) {
            connMap = CommonUtils.objectToMap(dataBaseConnInfoDao.getDataBaseConInfoById(dataSourceId));
        } else if (dataSourceBasicInfoEntity.getDataSourceType().equals(DataSourceTypeEnum.FILE.getValue())) {
            connMap = CommonUtils.objectToMap(filesConnInfoDao.getFilesConnInfoById(dataSourceId));
        } else if (dataSourceBasicInfoEntity.getDataSourceType().equals(DataSourceTypeEnum.INTERFACE.getValue())) {
            connMap = CommonUtils.objectToMap(interfaceConnInfoDao.getInterfaceConnInfoById(dataSourceId));
        }
        resMap = CommonUtils.objectToMap(dataSourceBasicInfoEntity);
        resMap.putAll(connMap);
        return resMap;
    }

    /**
     * 按主键查询单条数据源基本信息
     *
     * @param dataSourceId
     * @return
     */
    @Override
    public DataSourceBasicInfoEntity getDataSourceBasicConnById(String dataSourceId) {
        return dataSourceBasicInfoDao.findDataSourceBasicInfoById(dataSourceId);
    }

    /**
     * 按主键查询单条数据库连接信息
     *
     * @param dataSourceId
     * @return
     */
    @Override
    public DatabaseConnInfoEntity getDataBaseConInfoById(String dataSourceId) {
        return dataBaseConnInfoDao.getDataBaseConInfoById(dataSourceId);
    }

    /**
     * 按主键查询单条文件连接信息
     *
     * @param dataSourceId
     * @return
     */
    @Override
    public FilesConnInfoEntity getFilesConnInfoById(String dataSourceId) {
        return filesConnInfoDao.getFilesConnInfoById(dataSourceId);
    }

    /**
     * 按主键查询单条接口连接信息
     *
     * @param dataSourceId
     * @return
     */
    @Override
    public InterfaceConnInfoEntity getInterfaceInfoById(String dataSourceId) {
        return interfaceConnInfoDao.getInterfaceConnInfoById(dataSourceId);
    }

    /**
     * 按条件检索数据源基本信息
     * 支持分页
     *
     * @param jsonParams
     * @return
     */
    @Override
    public Object findDataSourceBasicConnInfoByCond(String jsonParams) {
        JSONObject jsonObject = new JSONObject(jsonParams);
        // 类型
        Integer dataSourceType = null;
        try {
            dataSourceType = jsonObject.getInt("dataSourceType");
        } catch (Exception e) {
            e.getMessage();
        }
        // 关键字数组
        JSONArray keyWordsArr = null;
        try {
            keyWordsArr = jsonObject.getJSONArray("keyWords");
        } catch (Exception e) {
            e.getMessage();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyWordsArr", keyWordsArr);
        map.put("dataSourceType", dataSourceType);
        Integer pageNum = null;
        try {
            pageNum = jsonObject.getInt("pageNum");
        } catch (Exception e) {
            e.getMessage();
        }
        Integer pageSize = null;
        try {
            pageSize = jsonObject.getInt("pageSize");
        } catch (Exception e) {
            e.getMessage();
        }
        if (pageNum != null && pageSize != null) {
            // 数据分页
            PageHelper.startPage(pageNum, pageSize);
            PageInfo<DataSourceBasicInfoEntity> pageInfo = new PageInfo<DataSourceBasicInfoEntity>(dataSourceBasicInfoDao.findDataSourceBasicConnInfoByCond(map));
            return new Paging(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
        }
        return dataSourceBasicInfoDao.findDataSourceBasicConnInfoByCond(map);
    }

    /**
     * 分页查询
     *
     * @param queryMap 查询条件
     * @return
     */
    @Override
    public Paging<DataSourceBasicInfoEntity> findByPage(Map<String, Integer> queryMap) {
        // 请求参数不为空时，进行分页
        if (queryMap.get("pageNum") != null || queryMap.get("pageSize") != null) {
            PageHelper.startPage(queryMap.get("pageNum"), queryMap.get("pageSize"));
        }
        PageInfo<DataSourceBasicInfoEntity> pageInfo = new PageInfo<DataSourceBasicInfoEntity>(dataSourceBasicInfoDao.findAllByDataSourceBasicInfo());
        return new Paging(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getList());
    }

}
