package com.rpa.server.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 14:38
 * @description: 上传工具
 * @version: 1.0
 */
@Component
public class UploadUtil {
    private static String rootPath;
    private static String projectDir;
    private static String imageDir;

    /**
     * 图片上传
     *
     * @param base64Img 图片
     * @return 图片Url地址
     */
    public static String uploadBase64Image(String base64Img) {
        if (base64Img == null || "".equals(base64Img)) {
            return null;
        }

        File targetFile = new File(rootPath + projectDir + imageDir);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        String imgFileName = UUID.randomUUID() + ".jpg";
        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(new FileOutputStream(rootPath + projectDir + imageDir + imgFileName));
            stream.write(Base64Utils.decodeFromString(base64Img));
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

        return projectDir + imageDir + imgFileName;
    }

    @Value("${file.uploadFolder}")
    public void setPROFILE(String rootPath) {
        UploadUtil.rootPath = rootPath;
    }

    @Value("${file.projectDir}")
    public void setProjectDir(String projectDir) {
        UploadUtil.projectDir = projectDir;
    }

    @Value("${file.imageDir}")
    public void setImageDir(String imageDir) {
        UploadUtil.imageDir = imageDir;
    }
}
