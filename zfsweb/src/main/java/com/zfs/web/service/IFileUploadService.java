package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface IFileUploadService {
    ResultVO upload(HttpServletRequest request, MultipartFile file,String moduleName);

    ResultVO appDirUpload(MultipartFile file) throws IOException;

    ResultVO videoUpload(MultipartFile file);

    ResultVO pluginUpload(MultipartFile file);
}
