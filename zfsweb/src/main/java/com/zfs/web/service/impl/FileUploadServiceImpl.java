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
import java.util.ArrayList;
import java.util.List;

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
        String picture = publicPath + FileUtil.uploadFile(file, iconDir, "notice");
        return new ResultVO(1000,picture);
    }

    @Value("${file.appDir}")
    private String appDir;
    @Value("${file.videoDir}")
    private String videoDir;
    @Value("${file.pluginDir}")
    private String pluginDir;

    @Override
    public ResultVO appDirUploadGetNames(MultipartFile file) throws IOException {
        ResultVO resultVO = this.appDirUpload(file);
        String rename = (String) resultVO.getData();
        List<String> names=new ArrayList<>();
        names.add(rename);
        names.add(file.getOriginalFilename());
        resultVO.setData(names);
        return resultVO;
    }

    @Override
    public ResultVO appDirUpload(MultipartFile file) throws IOException {
        // 这是相对路径
        String filePath = FileUtil.uploadFile(file, appDir, "app");
        // 上传apk文件
        ApkFile apkFile = new ApkFile(FileUtil.rootPath+ filePath);
        ApkMeta apkMeta = apkFile.getApkMeta();
        // 重命名
        String newFile =FileUtil.projectDir + appDir + apkMeta.getPackageName() + "_" + apkMeta.getVersionCode() +
                "_" + System.currentTimeMillis() + ".apk";
        apkFile.close();
        new File(FileUtil.rootPath+ filePath).renameTo(new File(FileUtil.rootPath+  newFile));
        //返回相对路径
        return new ResultVO(1000, newFile);
    }

    @Override
    public ResultVO videoUpload(MultipartFile file) {
        String functionvideo = FileUtil.uploadFile(file, videoDir, "functionvideo");
        return new ResultVO(1000,  publicPath + functionvideo);
    }

    @Override
    public ResultVO pluginUpload(MultipartFile file) {
        List<String> list = FileUtil.uploadFileGetNames(file, pluginDir, "plugin");
        return new ResultVO(1000,list);
    }
}
