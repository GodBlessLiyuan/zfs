package com.rpa.common.utils;

import com.rpa.common.constant.ModuleConstant;
import com.rpa.common.jna.Clibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author: xiahui
 * @date: Created in 2019/12/4 10:50
 * @description: 文件工具
 * @version: 1.0
 */
public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

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

    /**
     * 复制文件
     *
     * @param is
     * @param os
     * @throws IOException
     */
    public static void copyFile(InputStream is, OutputStream os) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(os);

        byte[] bytes = new byte[1024];
        int len;
        while ((len = is.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.flush();

        bos.close();
        is.close();
        os.close();
    }

    /**
     * 重新构建Apk
     *
     * @param originUrl  应用原地址
     * @param avatarPath 构建后应用新地址
     * @param pkg        包名
     * @param name       应用名
     * @param pic        图标
     */
    public static String rebuildApk(String originUrl, String avatarPath, String pkg, String name, String pic, String suffix) {
        originUrl = "/data/ftp/dkfsftp/dkfsfile/avatar/test.apk";
//        originUrl = "E:/file/dkfsfile/test.apk";

        String zipUrl = avatarPath + "avatar.apk";
        String xmlUrl = avatarPath + "AndroidManifest.xml";

        try {
            File targetFile = new File(avatarPath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }

            FileUtil.copyFile(new FileInputStream(originUrl), new FileOutputStream(zipUrl));
            ZipFile zf = new ZipFile(zipUrl);
            ZipEntry ze = zf.getEntry("AndroidManifest.xml");
            FileUtil.copyFile(zf.getInputStream(ze), new FileOutputStream(xmlUrl));

//            modifyApkIcon(xmlUrl, pic, suffix, avatarPath);
            modifyApkName(xmlUrl, name, avatarPath);
//            modifyApkPkg(xmlUrl, pkg, avatarPath);
//            modifyApkSign(zipUrl);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return avatarPath;
    }

    /**
     * 修改应用图标
     *
     * @param xmlPath
     * @param pic
     */
    private static void modifyApkIcon(String xmlPath, String pic, String suffix, String avatarPath) throws IOException, InterruptedException {
        String picName = FileUtil.genFileName(ModuleConstant.AVATAR, suffix, System.currentTimeMillis());
        String picPath = FileUtil.uploadBase64(avatarPath, "res/mipmap-xxhdpi-v4/", "x_avatar.png", pic);
        logger.info("picName: {}, picPath: {}.", picName, picPath);

        // 压缩xml文件到zpk包中
        String[] CMD_STR = new String[]{"/bin/sh", "-c", "cd " + avatarPath + "; /usr/bin/zip -m avatar.apk " + picPath};
        Process process = Runtime.getRuntime().exec(CMD_STR);
        process.waitFor();
    }

    /**
     * 修改应用名称
     *
     * @param xmlPath
     * @param name
     */
    private static void modifyApkName(String xmlPath, String name, String zipPath) throws IOException, InterruptedException {
        if (null == name || "".equals(name)) {
            return;
        }

        Clibrary instance = Clibrary.INSTANTCE;
        name = new String(name.getBytes(),"utf-16");
        instance.modifyname(name.toCharArray(), name.length() * 2 + 2 + 2, xmlPath);
//        String outXml = zipPath + "/AndroidManifest2.xml";
//        // 修改 application 的 label 部分
//        FileUtil.modifyApkPkg("application", 1, "label", new String(name.getBytes("UTF-8"), "UTF-8"), xmlPath, outXml);
    }

    /**
     * 修改应用包名
     *
     * @param xmlPath
     * @param pkg
     */
    private static void modifyApkPkg(String xmlPath, String pkg, String zipPath) throws IOException, InterruptedException {
        if (null == pkg || "".equals(pkg)) {
            return;
        }

        String outXml = zipPath + "AndroidManifest2.xml";
        // 修改 package 部分
        FileUtil.modifyApkPkg("manifest", 1, "package", pkg, xmlPath, outXml);

        // 修改 3 处 permission 部分
        FileUtil.modifyApkPkg("permission", 2, "name", pkg + ".virtual.permission.VIRTUAL_BROADCAST", xmlPath, outXml);
        FileUtil.modifyApkPkg("permission", 3, "name", pkg + ".permission.C2D_MESSAGE", xmlPath, outXml);
        FileUtil.modifyApkPkg("permission", 4, "name", pkg + ".Installing.WRITE_STATUS", xmlPath, outXml);

        // 修改3处 uses-permission 部分
        FileUtil.modifyApkPkg("uses-permission", 86, "name", pkg + ".virtual.permission.VIRTUAL_BROADCAST", xmlPath, outXml);
        FileUtil.modifyApkPkg("uses-permission", 87, "name", pkg + ".permission.C2D_MESSAGE", xmlPath, outXml);
        FileUtil.modifyApkPkg("uses-permission", 88, "name", pkg + ".Installing.WRITE_STATUS", xmlPath, outXml);

        // 修改42处 taskAffinity
        for (int i = 10; i < 17; i++) {
            FileUtil.modifyApkPkg("activity", i, "taskAffinity", pkg, xmlPath, outXml);
        }
        for (int i = 36; i < 87; i++) {
            FileUtil.modifyApkPkg("activity", i, "taskAffinity", pkg, xmlPath, outXml);
        }
        // 修改 22 处 authorities
        FileUtil.modifyApkPkg("provider", 1, "authorities", pkg, xmlPath, outXml);

        for (int i = 3; i < 24; i++) {
            FileUtil.modifyApkPkg("provider", i, "authorities", pkg + ".rpa.robot.stub.ContentProviderProxy" + (i - 3), xmlPath, outXml);
        }
    }

    /**
     * 修改 AndroidManifest.xml 文件
     *
     * @param application
     * @param index
     * @param name
     * @param value
     * @param inputXml
     * @param outXml
     * @throws IOException
     * @throws InterruptedException
     */
    private static void modifyApkPkg(String application, int index, String name, String value, String inputXml, String outXml) throws IOException, InterruptedException {
        String[] CMD_STR = new String[]{"/bin/sh", "-c", "/data/project/dkfsbin/dkfsserver/ameditor a --modify " + application + " -d " + index + " -n " + name + " -t 3 -v "
                + value + " -i " + inputXml + " -o " + outXml};
        Process process = Runtime.getRuntime().exec(CMD_STR);
        process.waitFor();
        new File(inputXml).delete();
        new File(outXml).renameTo(new File(inputXml));
    }

    /**
     * 修改签名信息
     *
     * @param zipUrl
     */
    private static void modifyApkSign(String zipUrl) throws IOException, InterruptedException {
        logger.info("zipUrl: {}", zipUrl);
        // 压缩xml文件到zpk包中
        String[] CMD_STR = new String[]{"/bin/sh", "-c", "cd /data/ftp/dkfsftp/dkfsfile/avatar_temp; /usr/bin/zip -m avatar.apk AndroidManifest.xml"};
        Process process = Runtime.getRuntime().exec(CMD_STR);
        process.waitFor();

        // 删除apk之前的签名信息
        CMD_STR = new String[]{"/bin/sh", "-c", "/usr/bin/zip -d " + zipUrl + " META-INF/*"};
        process = Runtime.getRuntime().exec(CMD_STR);
        process.waitFor();

        // 重签名apk
        CMD_STR = new String[]{"/bin/sh", "-c", "jarsigner -digestalg SHA1 -sigalg MD5withRSA -verbose "
                + "-keystore /data/project/dkfsbin/dkfsserver/godArmor.keystore -storepass 123456 -signedjar /data/ftp/dkfsftp/dkfsfile/avatar_temp/zip_signer.apk " + zipUrl + " godArmor.keystore"};
        process = Runtime.getRuntime().exec(CMD_STR);
        process.waitFor();
    }
}
