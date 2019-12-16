package com.rpa.server.mapper;

import com.rpa.server.pojo.DevicePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceMapper继承基类
 */
@Mapper
public interface DeviceMapper extends BaseMapper<DevicePO, Long> {
}