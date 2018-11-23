package com.unistrong.geotsd.datasource.data.commonresultparam;


import java.util.List;

/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 接口返回值 resultData
 */
public class ResultData {

    private Integer startRow;
    private Integer endRow;
    private List<?> pageData;
    private Integer pageNum;
    private Integer pageSize;
    private Integer pages;
    private Integer total;

    public ResultData() {
    }

    public ResultData(Integer startRow, Integer endRow, List<?> pageData, Integer pageNum, Integer pageSize, Integer pages, Integer total) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.pageData = pageData;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pages = pages;
        this.total = total;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public List<?> getPageData() {
        return pageData;
    }

    public void setPageData(List<?> pageData) {
        this.pageData = pageData;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
