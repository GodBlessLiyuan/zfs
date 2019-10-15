package com.rpa.server.mapper;

import com.rpa.server.pojo.DeviceImeiPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceImeiMapper继承基类
 */
@Mapper
public interface DeviceImeiMapper extends BaseMapper<DeviceImeiPO, String> {
}