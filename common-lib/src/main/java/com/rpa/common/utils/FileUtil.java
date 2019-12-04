package com.rpa.common.utils;

import org.springframework.util.Base64Utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: xiahui
 * @date: Created in 2019/12/4 10:50
 * @description: 文件工具
 * @version: 1.0
 */
public class FileUtil {

    /**
     * Base64上传
     *
     * @param root   根目录
     * @param path   路径
     * @param name   文件名
     * @param base64 base64数据
     * @return Url地址
     */
    public static String uploadBase64(String root, String path, String name, String base64) {
        if (null == base64 || "".equals(base64)) {
            return null;
        }

        File targetFile = new File(root + path);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        BufferedOutputStream stream = null;
        try {
            stream = new BufferedOutputStream(new FileOutputStream(root + path + name));
            stream.write(Base64Utils.decodeFromString(base64));
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

        return path + name;
    }

    /**
     * 生成文件路径
     *
     * @param params
     * @return
     */
    public static String genFilePath(Object... params) {
        StringBuffer sb = new StringBuffer();
        sb.append("/");
        for (Object param : params) {
            sb.append(param);
            sb.append("/");
        }
        return sb.toString();
    }

    /**
     * 生成文件名称
     *
     * @param module
     * @param suffix
     * @param params
     * @return
     */
    public static String genFileName(String module, String suffix, Object... params) {
        StringBuffer sb = new StringBuffer();
        sb.append(module);
        sb.append("_");
        for (Object param : params) {
            sb.append(param);
            sb.append("_");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(".");
        sb.append(suffix);
        return sb.toString();
    }
}
