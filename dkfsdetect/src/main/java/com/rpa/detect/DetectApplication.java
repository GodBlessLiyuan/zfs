package com.rpa.detect;

import com.rpa.detect.command.DkfsFrontDetectRunnable;
import com.rpa.detect.command.DkfsServerDetectRunnable;
import com.rpa.detect.command.DkfsWebDetectRunnable;
import com.rpa.detect.constant.DetectConstant;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: xiahui
 * @date: Created in 2019/11/23 9:16
 * @description: 状态检测
 * @version: 1.0
 */
@SpringBootApplication
public class DetectApplication {
    private static final ScheduledExecutorService service = new ScheduledThreadPoolExecutor(DetectConstant.CORE_POOL_SIZE,
            new BasicThreadFactory.Builder().namingPattern(DetectConstant.SCHEDULED_POOL_NAME).daemon(true).build());

    public static void main(String[] args) {
        SpringApplication.run(DetectApplication.class, args);
        schedule();
    }

    public static void schedule() {
        service.scheduleWithFixedDelay(new DkfsWebDetectRunnable(), 1, 1, TimeUnit.MINUTES);
        service.scheduleWithFixedDelay(new DkfsServerDetectRunnable(), 2, 1, TimeUnit.MINUTES);
        service.scheduleWithFixedDelay(new DkfsFrontDetectRunnable(), 3, 1, TimeUnit.MINUTES);
    }
}