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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
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

        return dir + fileName;
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
            String filePath = FileUtil.uploadFile(file, apkDir);
            ApkFile apkFile = new ApkFile(rootPath + filePath);
            ApkMeta apkMeta = apkFile.getApkMeta();
            apkInfo.put("pkgname", apkMeta.getPackageName());
            apkInfo.put("versioncode", apkMeta.getVersionCode());
            apkInfo.put("versionname", apkMeta.getVersionName());
            // 重命名
            String newFile = apkDir + apkMeta.getPackageName() + "_" + apkMeta.getVersionCode() +
                    "_" + System.currentTimeMillis() + ".apk";
            apkFile.close();
            new File(rootPath + filePath).renameTo(new File(rootPath + newFile));
            apkInfo.put("url", newFile);
            apkInfo.put("channel", getChannel(apkFile.getManifestXml()));


        } catch (IOException e) {
            e.printStackTrace();
        }

        return apkInfo;
    }


    /**
     * 从manifest中获取UMENG_CHANNEL属性
     *
     * @param manifestXml
     * @return
     */
    public static String getChannel(String manifestXml) {
        String pattern = "meta-data android:name=\"UMENG_CHANNEL\" android:value=\"([a-zA-Z0-9]*)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(manifestXml);
        if (m.find()) {
            String str = m.group(0);
            String channel = str.substring(str.lastIndexOf("\"") + 1);
            return channel;
        }
        return null;
    }

    @Value("${file.uploadFolder}")
    public void setPROFILE(String rootPath) {
        FileUtil.rootPath = rootPath;
    }
}
