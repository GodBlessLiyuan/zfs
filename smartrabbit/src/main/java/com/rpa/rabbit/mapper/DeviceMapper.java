package com.rpa.rabbit.mapper;

import com.rpa.rabbit.pojo.DevicePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceMapper继承基类
 */
@Mapper
public interface DeviceMapper extends BaseMapper<DevicePO, Long> {
}