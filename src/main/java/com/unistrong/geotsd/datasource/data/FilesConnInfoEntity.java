package com.unistrong.geotsd.datasource.data;

/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 文件访问信息Entity
 */
public class FilesConnInfoEntity {
    private String filePath;
    private Integer fileType;
    private String dataSourceId;

    public FilesConnInfoEntity() {
    }

    public FilesConnInfoEntity(String filePath, Integer fileType, String dataSourceId) {
        this.filePath = filePath;
        this.fileType = fileType;
        this.dataSourceId = dataSourceId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }
}
