package com.rpa.web.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: xiahui
 * @date: Created in 2019/10/10 19:03
 * @description: 文件工具
 * @version: 1.0
 */
public class FileUtil {
    public static String FILE_PATH = "E:\\";

    /**
     * 文件上传处理
     *
     * @param file 文件信息
     * @param req
     * @return 文件路径
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest req) {
        String fileName = file.getOriginalFilename();
        File targetFile = new File(FileUtil.FILE_PATH);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        String filePath = FileUtil.FILE_PATH + fileName;
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

        return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() +
                "/file/" + fileName;
    }
}
