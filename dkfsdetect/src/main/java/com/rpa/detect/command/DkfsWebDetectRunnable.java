package com.rpa.detect.command;

import com.rpa.detect.utils.DetectProcessUtil;

/**
 * @author: xiahui
 * @date: Created in 2019/11/23 10:11
 * @description: 检测 dkfsweb 微服务
 * @version: 1.0
 */
public class DkfsWebDetectRunnable implements Runnable {
    @Override
    public void run() {
        DetectProcessUtil.detectProStatus("dkfsweb", 3);
    }
}
