package com.zfs.web.service.impl;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.IFileUploadService;
import com.zfs.web.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-14 15:30
 */
@Service
public class FileUploadServiceImpl implements IFileUploadService {

    @Value("${file.iconDir}")
    private String iconDir;
    @Value("${file.publicPath}")
    private String publicPath;

    @Override
    public ResultVO upload(HttpServletRequest request, MultipartFile file,String moduleName) {
        String picture = publicPath + FileUtil.uploadFile(file, iconDir, moduleName);
        return new ResultVO(1000,picture);
    }
}
