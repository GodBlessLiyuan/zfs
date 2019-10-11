package com.rpa.web.utils;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/10 19:03
 * @description: 文件工具
 * @version: 1.0
 */
@Component
public class FileUtil {

    public static String rootPath;

    /**
     * 文件上传处理
     *
     * @param file 文件信息
     * @param dir  file存放文件目录
     * @return 文件路径
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, String dir) {
        String fileName = file.getOriginalFilename();
        File targetFile = new File(rootPath + dir);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        String filePath = rootPath + dir + fileName;
        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(new FileOutputStream(filePath));
            stream.write(file.getBytes());
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "/file/" + dir + fileName;
    }

    /**
     * 解析apk信息
     *
     * @param file
     * @param apkDir
     * @return
     */
    public static Map<String, Object> resolveApk(MultipartFile file, String apkDir) {
        Map<String, Object> apkInfo = new HashMap<>(8);

        try {
            // 上传apk文件
            apkInfo.put("url", uploadFile(file, apkDir));
            ApkFile apkFile = new ApkFile(rootPath + apkDir + file.getOriginalFilename());
            ApkMeta apkMeta = apkFile.getApkMeta();
            apkInfo.put("pkgname", apkMeta.getPackageName());
            apkInfo.put("versioncode", apkMeta.getVersionCode());
            apkInfo.put("versionname", apkMeta.getVersionName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return apkInfo;
    }

    @Value("${file.uploadFolder}")
    public void setPROFILE(String rootPath) {
        FileUtil.rootPath = rootPath;
    }
}
