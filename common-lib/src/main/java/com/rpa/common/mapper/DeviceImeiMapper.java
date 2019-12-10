package com.rpa.common.mapper;

import com.rpa.common.pojo.DeviceImeiPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceImeiMapper继承基类
 */
@Mapper
public interface DeviceImeiMapper extends BaseMapper<DeviceImeiPO, String> {
}