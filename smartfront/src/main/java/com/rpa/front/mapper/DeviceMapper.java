package com.rpa.front.mapper;

import com.rpa.front.pojo.DevicePO;
import org.springframework.stereotype.Repository;

/**
 * DeviceMapper继承基类
 */
@Repository
public interface DeviceMapper extends BaseMapper<DevicePO, Long> {
}