package com.zfs.web.service.impl;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.IFileUploadService;
import com.zfs.web.utils.FileUtil;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

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

    @Value("${file.appDir}")
    private String appDir;
    @Override
    public ResultVO appDirUpload(MultipartFile file,String moduleName) throws IOException {
        // 上传apk文件,都是绝对路径
        String filePath = FileUtil.uploadFile(file, appDir, moduleName);
        // 上传apk文件
        ApkFile apkFile = new ApkFile( filePath);
        ApkMeta apkMeta = apkFile.getApkMeta();
        // 重命名
        String newFile =FileUtil.projectDir + appDir + apkMeta.getPackageName() + "_" + apkMeta.getVersionCode() +
                "_" + System.currentTimeMillis() + ".apk";
        apkFile.close();
        new File(filePath).renameTo(new File(FileUtil.rootPath+  newFile));
        //返回相对路径
        return new ResultVO(1000, newFile);
    }
}
