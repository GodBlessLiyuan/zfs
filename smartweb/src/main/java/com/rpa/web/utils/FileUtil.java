package com.rpa.web.utils;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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

        return filePath;
    }

    /**
     * 文件上传处理
     *
     * @param srcPath    上传文件的路径
     * @param targetPath 拟存放文件的路径
     * @return 文件路径
     * @throws IOException
     */
    public static String uploadFile(String srcPath, String targetPath) {

        // 获取源文件的文件名
        String fileName = srcPath.substring(srcPath.lastIndexOf('\\') + 1);

        // 创建存放文件的路径
        File realTargetPath = new File(rootPath + targetPath);
        if (!realTargetPath.exists()) {
            realTargetPath.mkdirs();
        }

        // 复制文件
        File srcFile = new File(srcPath);
        File targetFile = new File(realTargetPath + File.separator + fileName);

        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(targetFile);
            byte[] buf = new byte[8 * 1024];
            int len = 0;
            while ((len = in.read(buf)) != -1) {
                out.write(buf, 0, len);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 返回文件所存放的路径位置
        System.out.println("/file" + targetPath + fileName);
        return "/file" + targetPath + fileName;
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
            ApkFile apkFile = new ApkFile(filePath);
            ApkMeta apkMeta = apkFile.getApkMeta();
            apkInfo.put("pkgname", apkMeta.getPackageName());
            apkInfo.put("versioncode", apkMeta.getVersionCode());
            apkInfo.put("versionname", apkMeta.getVersionName());
            // 重命名
            String newFile = rootPath + apkDir + apkMeta.getPackageName() + "_" + apkMeta.getVersionCode() +
                    "_" + System.currentTimeMillis() + ".apk";
            apkFile.close();
            new File(filePath).renameTo(new File(newFile));
            apkInfo.put("url", newFile);
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
