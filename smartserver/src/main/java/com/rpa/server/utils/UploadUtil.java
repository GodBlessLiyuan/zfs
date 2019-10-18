package com.rpa.server.utils;

import com.rpa.server.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
    private static String imageDir;

    /**
     * 图片上传
     *
     * @param base64Img 图片
     * @return 图片Url地址
     */
    public static String uploadBase64Image(String base64Img) throws Exception {
        if (base64Img == null || "".equals(base64Img)) {
            return null;
        }

        File targetFile = new File(rootPath + imageDir);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        String[] base = base64Img.split("base64,");
        if (base.length != 2) {
            throw new Exception("非法数据格式！");
        }

        String imgFileName = UUID.randomUUID() + UploadUtil.getImgSuffix(base[0]);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(rootPath + imageDir + imgFileName));
        stream.write(Base64Utils.decodeFromString(base[1]));
        stream.flush();
        stream.close();

        return imageDir + imgFileName;
    }

    /**
     * 根据Base64前缀获取图片后缀
     *
     * @param base64Prefix Base64前缀
     * @return
     * @throws Exception
     */
    private static String getImgSuffix(String base64Prefix) throws Exception {
        String suffix = "";
        if (CommonConstant.BASE64_SUFFIX_JPG.equalsIgnoreCase(base64Prefix)) {
            suffix = ".jpg";
        } else if (CommonConstant.BASE64_SUFFIX_ICO.equalsIgnoreCase(base64Prefix)) {
            suffix = ".ico";
        } else if (CommonConstant.BASE64_SUFFIX_GIF.equalsIgnoreCase(base64Prefix)) {
            suffix = ".gif";
        } else if (CommonConstant.BASE64_SUFFIX_PNG.equalsIgnoreCase(base64Prefix)) {
            suffix = ".png";
        } else {
            throw new Exception("上传图片格式不合法！");
        }
        return suffix;
    }

    @Value("${file.uploadFolder}")
    public void setPROFILE(String rootPath) {
        UploadUtil.rootPath = rootPath;
    }

    @Value("${file.imageDir}")
    public void setImageDir(String imageDir) {
        UploadUtil.imageDir = imageDir;
    }
}
