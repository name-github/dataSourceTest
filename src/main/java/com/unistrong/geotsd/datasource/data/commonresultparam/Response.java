package com.unistrong.geotsd.datasource.data.commonresultparam;


import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Auther: wangshuo
 * @Date: 2018/6/29
 * @Description: 接口返回参数
 */
public class Response {
    private String resultState;
    private String resultCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ResultData resultData;

    public Response() {
    }

    public Response(String resultState, String resultCode, ResultData resultData) {
        this.resultState = resultState;
        this.resultCode = resultCode;
        this.resultData = resultData;
    }

    public String getResultState() {
        return resultState;
    }

    public void setResultState(String resultState) {
        this.resultState = resultState;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public ResultData getResultData() {
        return resultData;
    }

    public void setResultData(ResultData resultData) {
        this.resultData = resultData;
    }

}
