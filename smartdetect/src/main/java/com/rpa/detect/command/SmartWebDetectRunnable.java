package com.rpa.detect.command;

import com.rpa.detect.utils.DetectProcessUtil;

/**
 * @author: xiahui
 * @date: Created in 2019/11/23 10:11
 * @description: 检测 smartweb 微服务
 * @version: 1.0
 */
public class SmartWebDetectRunnable implements Runnable {
    @Override
    public void run() {
        DetectProcessUtil.detectProStatus("smartweb", 3);
    }
}
