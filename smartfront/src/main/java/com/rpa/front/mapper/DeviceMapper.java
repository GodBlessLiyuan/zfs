package com.rpa.front.mapper;

import com.rpa.front.pojo.DevicePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceMapper继承基类
 */
@Mapper
public interface DeviceMapper extends BaseMapper<DevicePO, Long> {
}