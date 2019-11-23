package com.rpa.detect.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author: xiahui
 * @date: Created in 2019/11/23 10:11
 * @description: 检测 smartweb 微服务
 * @version: 1.0
 */
public class SmartWebDetectRunnable implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(SmartWebDetectRunnable.class);

    @Override
    public void run() {
        try {
            String[] cmds = {"/bin/sh", "-c", "ps aux | grep smartweb"};
            Process pro = Runtime.getRuntime().exec(cmds);
            pro.waitFor();
            InputStream in = pro.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in));

            int res = 0;
            while (null != read.readLine()) {
                res++;
            }

            logger.info(String.valueOf(res));
            if (res != 3) {
                cmds = new String[]{"/bin/sh", "-c", "nohup java -Xms128m -Xmx512m  -Dloader.path=/data/project/lib -jar " +
                        "/data/project/bin/smartweb-1.0.jar --spring.profiles.active=dev " +
                        ">/data/project/logs/smartweb.log 2>&1 &"};
                // 重新启动
                Runtime.getRuntime().exec(cmds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
