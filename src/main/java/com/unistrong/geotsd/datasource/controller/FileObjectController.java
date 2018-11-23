package com.unistrong.geotsd.datasource.controller;

import com.unistrong.geotsd.datasource.common.client.*;
import com.unistrong.geotsd.datasource.service.FileObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 *  文件服务器
 * @author zc.shen
 * @created 2018-06-27
 */
@RestController
@RequestMapping({"/api/fastdfs"})
public class FileObjectController {

    @Autowired
    private FileObjectService fileObjectService;//文件service

    /**
     * 以附件形式下载文件
     *
     * @param filePath 文件地址
     * @param response
     */
    @RequestMapping("/download/file")
    public void downloadFile(String filePath, HttpServletResponse response) throws FastDFSException {
        fileObjectService.downloadFile(filePath,response);
    }


}
