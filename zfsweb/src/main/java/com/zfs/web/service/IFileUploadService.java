package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IFileUploadService {
    ResultVO upload(HttpServletRequest request, MultipartFile file,String moduleName);
}
