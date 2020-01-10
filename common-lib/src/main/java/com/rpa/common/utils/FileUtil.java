package com.rpa.common.utils;

import com.rpa.common.constant.ModuleConstant;
import com.rpa.common.dto.AvatarMakeDTO;
import com.rpa.common.jna.Clibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;

import java.io.*;
import java.util.Arrays;
import java.util.UUID;
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
     * @param templatePath 构建后应用新地址
     * @param pkg        包名
     * @param name       应用名
     * @param pic        图标
     */
    private static final String TEMPLATE_APK_NAME = "template.apk";
    private static final String ANDROID_MANIFEST_NAME = "AndroidManifest.xml";

    public static String rebuildApk(String avatarUrl, String rootDir, String templatePath, AvatarMakeDTO dto) throws IOException, InterruptedException {
        String random = UUID.randomUUID().toString().replace("-", "");
        String tempFilePath = FileUtil.genFilePath(rootDir, templatePath, random);
        String tempApkUrl = tempFilePath + TEMPLATE_APK_NAME;
        String tempXmlUrl = tempFilePath + ANDROID_MANIFEST_NAME;

        File targetFile = new File(tempFilePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        FileUtil.copyFile(new FileInputStream(avatarUrl), new FileOutputStream(tempApkUrl));
        ZipFile zf = new ZipFile(tempApkUrl);
        ZipEntry ze = zf.getEntry(ANDROID_MANIFEST_NAME);
        FileUtil.copyFile(zf.getInputStream(ze), new FileOutputStream(tempXmlUrl));

        modifyApkIcon(tempFilePath, dto.getPic(), dto.getSuffix());
        boolean isModApkName = modifyApkName(tempXmlUrl, dto.getName());
        boolean isModApkPkg = modifyApkPkg(tempXmlUrl, dto.getPkg(), tempFilePath);
        String templateUrl = templatePath + ModuleConstant.AVATAR + random + ".apk";
        modifyApkSign(rootDir + templateUrl, tempFilePath, isModApkName || isModApkPkg);

        // 清除临时文件夹
        String[] CMD_STR = new String[]{"/bin/sh", "-c", "cd " + rootDir + templatePath + "; rm -rf " + random};
        Process process = Runtime.getRuntime().exec(CMD_STR);
        process.waitFor();

        logger.info("rm random: {}", CMD_STR[2]);
        return templateUrl;
    }

    /**
     * 修改应用图标
     *
     * @param tempFilePath
     * @param pic
     */
    private static void modifyApkIcon(String tempFilePath, String pic, String suffix) throws IOException, InterruptedException {
        String picPath = FileUtil.uploadBase64(tempFilePath, "res/mipmap-xxhdpi-v4/", "x_avatar." + suffix, pic);

        // 压缩xml文件到apk包中
        String[] CMD_STR = new String[]{"/bin/sh", "-c", "cd " + tempFilePath + "; /usr/bin/zip -m " + TEMPLATE_APK_NAME + " " + picPath};
        Process process = Runtime.getRuntime().exec(CMD_STR);
        process.waitFor();
    }

    /**
     * 修改应用名称
     *
     * @param xmlPath
     * @param name
     */
    private static boolean modifyApkName(String xmlPath, String name) {
        if (null == name || "".equals(name)) {
            return false;
        }

        Clibrary instance = Clibrary.INSTANTCE;
        instance.modifyname(str2CharArray(name), name.length() * 2 + 2 + 2, xmlPath);

        return true;
    }


    /**
     * Java String 转 C++ unsigned char []
     *
     * @param name
     * @return
     */
    private static char[] str2CharArray(String name) {
        char[] temp = new char[name.length() * 2 + 2 + 2];
        Arrays.fill(temp, (char) 0);

        temp[0] = (char) name.length();
        for (int i = 0; i < name.length(); i++) {
            temp[i + 1] = name.charAt(i);
        }

        return temp;
    }

    /**
     * 修改应用包名
     *
     * @param xmlPath
     * @param pkg
     */
    private static boolean modifyApkPkg(String xmlPath, String pkg, String zipPath) throws IOException, InterruptedException {
        if (null == pkg || "".equals(pkg)) {
            return false;
        }

        String outXml = zipPath + "AndroidManifest2.xml";
        // 修改 package 部分
        FileUtil.modifyApkPkg("manifest", 1, "package", pkg, xmlPath, outXml);

        // 修改 3 处 permission 部分
        FileUtil.modifyApkPkg("permission", 1, "name", pkg + ".virtual.permission.VIRTUAL_BROADCAST", xmlPath, outXml);
        FileUtil.modifyApkPkg("permission", 2, "name", pkg + ".permission.C2D_MESSAGE", xmlPath, outXml);
        FileUtil.modifyApkPkg("permission", 3, "name", pkg + ".Installing.WRITE_STATUS", xmlPath, outXml);

        // 修改3处 uses-permission 部分
        FileUtil.modifyApkPkg("uses-permission", 86, "name", pkg + ".virtual.permission.VIRTUAL_BROADCAST", xmlPath, outXml);
        FileUtil.modifyApkPkg("uses-permission", 87, "name", pkg + ".permission.C2D_MESSAGE", xmlPath, outXml);
        FileUtil.modifyApkPkg("uses-permission", 88, "name", pkg + ".Installing.WRITE_STATUS", xmlPath, outXml);

        // 修改42处 taskAffinity
        for (int i = 1; i <= 42; i++) {
            FileUtil.modifyApkPkg("activity", i, "taskAffinity", pkg, xmlPath, outXml);
        }
        // 修改 22 处 authorities
        FileUtil.modifyApkPkg("provider", 1, "authorities", pkg, xmlPath, outXml);

        for (int i = 2; i <= 22; i++) {
            FileUtil.modifyApkPkg("provider", i, "authorities", pkg + ".rpa.robot.stub.ContentProviderProxy" + (i - 2), xmlPath, outXml);
        }

        // 修改2处 meta-data
        FileUtil.modifyApkPkg("meta-data", 1, "value", "1111111111111111", xmlPath, outXml);
        FileUtil.modifyApkPkg("meta-data", 2, "value", "2222222222222222", xmlPath, outXml);

        return true;
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
        String[] CMD_STR = new String[]{"/bin/sh", "-c", "/data/project/dkfsbin/dkfsserver/tools/ameditor a --modify " + application + " -d " + index + " -n " + name + " -t 3 -v "
                + value + " -i " + inputXml + " -o " + outXml};
        Process process = Runtime.getRuntime().exec(CMD_STR);
        process.waitFor();

        new File(inputXml).delete();
        new File(outXml).renameTo(new File(inputXml));
    }

    /**
     * 修改签名信息
     *
     * @param signApkUrl   签名生成的文件
     * @param tempFilePath 临时文件路径
     * @param isModXml     之前有无修改 Android Xml 文件
     * @throws IOException
     * @throws InterruptedException
     */
    private static String modifyApkSign(String signApkUrl, String tempFilePath, boolean isModXml) throws IOException, InterruptedException {
        // 压缩xml文件到zpk包中
        String[] CMD_STR;
        Process process;

        if (isModXml) {
            CMD_STR = new String[]{"/bin/sh", "-c", "cd " + tempFilePath + "; /usr/bin/zip -m " + TEMPLATE_APK_NAME + " " + ANDROID_MANIFEST_NAME};
            process = Runtime.getRuntime().exec(CMD_STR);
            process.waitFor();
        }

        // 删除apk之前的签名信息
        CMD_STR = new String[]{"/bin/sh", "-c", "/usr/bin/zip -d " + tempFilePath + TEMPLATE_APK_NAME + " META-INF/*"};
        process = Runtime.getRuntime().exec(CMD_STR);
        process.waitFor();

        // 重签名apk
        CMD_STR = new String[]{"/bin/sh", "-c", "jarsigner -digestalg SHA1 -sigalg MD5withRSA -verbose "
                + "-keystore /data/project/dkfsbin/dkfsserver/tools/godArmor.keystore -storepass 123456 -signedjar " + signApkUrl + " " + tempFilePath + TEMPLATE_APK_NAME + " godArmor.keystore"};
        process = Runtime.getRuntime().exec(CMD_STR);
        process.waitFor();

        return signApkUrl;
    }
}
