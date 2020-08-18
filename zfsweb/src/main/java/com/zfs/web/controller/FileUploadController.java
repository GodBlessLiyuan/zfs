package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.IFileUploadService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
    public ResultVO upload(HttpServletRequest request, @Param("file") MultipartFile file,
                           @RequestParam(value = "projectName",required = false) String projectName) {
        return fileUploadService.upload(request, file,projectName);
    }

    @PostMapping("appDirUpload")
    public ResultVO upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        return fileUploadService.appDirUpload(file);
    }
    @RequestMapping("videoUpload")
    public ResultVO videoUpload(@RequestParam(value = "file") MultipartFile file){
        return fileUploadService.videoUpload(file);
    }

}
