package com.rpa.detect.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author: xiahui
 * @date: Created in 2019/11/23 15:17
 * @description: 检测进程
 * @version: 1.0
 */
public class DetectProcessUtil {
    private static final Logger logger = LoggerFactory.getLogger(DetectProcessUtil.class);


    /**
     * 检测进程状态
     *
     * @param name 进程名称
     * @param num  进程active时的进程数量
     */
    public static void detectProStatus(String name, int num) {
        try {
            String[] cmds = {"/bin/sh", "-c", "ps aux | grep " + name};
            Process pro = Runtime.getRuntime().exec(cmds);
            pro.waitFor();
            InputStream in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));

            int res = 0;
            while (null != read.readLine()) {
                res++;
            }

            logger.info(name + "-" + Thread.currentThread().getName() + ":" + res);
            if (res != num) {
                cmds = new String[]{"/bin/sh", "-c", "service " + name + " start"};
                // 重新启动
                Runtime.getRuntime().exec(cmds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
