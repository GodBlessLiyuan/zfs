package com.rpa.common.utils;

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
     * @param resource
     * @param target
     * @throws IOException
     */
    public static void copyFile(String resource, String target) throws IOException {
        FileInputStream fis = new FileInputStream(resource);
        FileOutputStream fos = new FileOutputStream(target);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            bos.write(bytes, 0, len);
        }
        bos.flush();

        bos.close();
        fis.close();
        fos.close();
    }

    /**
     * 重新构建Apk
     *
     * @param originUrl 应用原地址
     * @param zipPath   构建后应用新地址
     * @param pkg       包名
     * @param name      应用名
     * @param pic       图标
     */
    public static void rebuildApk(String originUrl, String zipPath, String pkg, String name, String pic, String suffix) {
        logger.info("originUrl: {}, zipPath: {}", originUrl, zipPath);
        originUrl = "/data/ftp/dkfsftp/dkfsfile/test.apk";

        String xmlPath = null;
        try {
            String zipUrl = zipPath + "/zip.apk";
            FileUtil.copyFile(originUrl, zipUrl);
            ZipFile zf = new ZipFile(zipUrl);
            ZipEntry ze = zf.getEntry("AndroidManifest.xml");
            InputStream is = zf.getInputStream(ze);
            FileOutputStream fos = new FileOutputStream(xmlPath = zipPath + "/" + ze.getName());
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
            }
            is.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        modifyApkIcon(zipPath, pic, suffix);
        modifyApkName(xmlPath, name);
        modifyApkPkg(xmlPath, pkg, zipPath);

        try {
            // 压缩xml文件到zpk包中
            String[] CMD_STR = new String[]{"/bin/sh", "-c", "cd " + zipPath};
            Process process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();
            CMD_STR = new String[]{"/bin/sh", "-c", "/usr/bin/zip -m /data/ftp/dkfsftp/dkfsfile/zip.apk AndroidManifest.xml"};
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();

            // 删除apk之前的签名信息
            CMD_STR = new String[]{"/bin/sh", "-c", "/usr/bin/zip -d /data/ftp/dkfsftp/dkfsfile/zip.apk META-INF/*"};
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();

            logger.info("Zip and del complete.");

            // 重签名apk
            CMD_STR = new String[]{"/bin/sh", "-c", "jarsigner -digestalg SHA1 -sigalg MD5withRSA -verbose "
                    + "-keystore /data/project/dkfsbin/dkfsserver/godArmor.keystore -storepass 123456 -signedjar /data/ftp/dkfsftp/dkfsfile/zip_signer.apk /data/ftp/dkfsftp/dkfsfile/zip.apk godArmor.keystore"};
            logger.info("Resign cmd: {}", CMD_STR[2]);
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();
            logger.info("Resign complete.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改应用图标
     *
     * @param xmlPath
     * @param pic
     */
    private static void modifyApkIcon(String xmlPath, String pic, String suffix) {
    }

    /**
     * 修改应用包名
     *
     * @param xmlPath
     * @param pkg
     */
    private static void modifyApkPkg(String xmlPath, String pkg, String zipPath) {
        if (null == pkg || "".equals(pkg)) {
            return;
        }

        String zipXmlPath = zipPath + "/AndroidManifest.xml";
        logger.info("xmlPath: {}, zipXmlPath: {}", xmlPath, zipXmlPath);

        String[] CMD_STR = new String[]{"/bin/sh", "-c", "cd /data/project/dkfsbin/dkfsserver/"};
        try {
            Process process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();

            // 修改 package 部分
            CMD_STR = new String[]{"/bin/sh", "-c", "./ameditor a --modify manifest -d 1 -n package -t 3 -v "
                    + pkg + " -i " + xmlPath + " -o " + zipXmlPath};
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();

            // 修改 3 处 permission 部分
            CMD_STR = new String[]{"/bin/sh", "-c", "./ameditor a --modify permission -d 1 -n name -t 3 -v "
                    + pkg + ".virtual.permission.VIRTUAL_BROADCAST" + " -i " + xmlPath + " -o " + zipXmlPath};
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();

            CMD_STR = new String[]{"/bin/sh", "-c", "./ameditor a --modify permission -d 2 -n name -t 3 -v "
                    + pkg + ".permission.C2D_MESSAGE" + " -i " + xmlPath + " -o " + zipXmlPath};
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();

            CMD_STR = new String[]{"/bin/sh", "-c", "./ameditor a --modify permission -d 3 -n name -t 3 -v "
                    + pkg + ".Installing.WRITE_STATUS" + " -i " + xmlPath + " -o " + zipXmlPath};
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();

            // 修改3处 uses-permission 部分
            CMD_STR = new String[]{"/bin/sh", "-c", "./ameditor a --modify uses-permission -d 86 -n name -t 3 -v "
                    + pkg + ".virtual.permission.VIRTUAL_BROADCAST" + " -i " + xmlPath + " -o " + zipXmlPath};
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();

            CMD_STR = new String[]{"/bin/sh", "-c", "./ameditor a --modify uses-permission -d 87 -n name -t 3 -v "
                    + pkg + ".permission.C2D_MESSAGE" + " -i " + xmlPath + " -o " + zipXmlPath};
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();

            CMD_STR = new String[]{"/bin/sh", "-c", "./ameditor a --modify uses-permission -d 88 -n name -t 3 -v "
                    + pkg + ".Installing.WRITE_STATUS" + " -i " + xmlPath + " -o " + zipXmlPath};
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();

            // 修改42处 taskAffinity
            for (int i = 6; i < 48; i++) {
                CMD_STR = new String[]{"/bin/sh", "-c", "./ameditor a --modify activity -d " + i + " -n taskAffinity -t 3 -v "
                        + pkg + " -i " + xmlPath + " -o " + zipXmlPath};
                process = Runtime.getRuntime().exec(CMD_STR);
                process.waitFor();
            }

            // 修改 22 处 authorities
            CMD_STR = new String[]{"/bin/sh", "-c", "./ameditor a --modify provider -d 1 -n authorities -t 3 -v "
                    + pkg + " -i " + xmlPath + " -o " + zipXmlPath};
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();

            for (int i = 2; i < 23; i++) {
                CMD_STR = new String[]{"/bin/sh", "-c", "./ameditor a --modify provider -d " + i + " -n authorities -t 3 -v "
                        + pkg + ".rpa.robot.stub.ContentProviderProxy" + (i - 2) + " -i " + xmlPath + " -o " + zipXmlPath};
                process = Runtime.getRuntime().exec(CMD_STR);
                process.waitFor();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("Modify apk pkg complete.");
    }

    /**
     * 修改应用名称
     *
     * @param xmlPath
     * @param name
     */
    private static void modifyApkName(String xmlPath, String name) {
        if (null == name || "".equals(name)) {
            return;
        }

        Clibrary instance = Clibrary.INSTANTCE;
        instance.modifyname(name.getBytes(), name.length() * 2 + 2 + 2, xmlPath);

        logger.info("Modify apk name complete.");
    }
}
