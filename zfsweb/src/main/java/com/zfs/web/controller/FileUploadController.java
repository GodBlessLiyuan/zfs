package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.IFileUploadService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-14 15:26
 */
@RestController
@RequestMapping("fileUpload")
public class FileUploadController {
    @Autowired
    private IFileUploadService fileUploadService;
    @RequestMapping("upload")
    public ResultVO upload(HttpServletRequest request, @Param("file") MultipartFile file,@RequestParam("projectName") String projectName) {
        return fileUploadService.upload(request, file,projectName);
    }
}
