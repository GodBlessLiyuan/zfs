package com.zfs.rabbit.service;

import java.util.Map;

/**
 * @author: dangyi
 * @date: Created in 16:47 2019/11/14
 * @version: 1.0.0
 * @description:用户设备访问信息统计
 */
public interface DeviceStatisticsService {

    void deviceStatic(Map<String, Object> deviceInfo);
}
