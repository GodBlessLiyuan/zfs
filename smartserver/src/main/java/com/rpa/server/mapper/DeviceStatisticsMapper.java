package com.rpa.server.mapper;

import com.rpa.server.pojo.DeviceStatisticsPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceStatisticsMapper继承基类
 */
@Mapper
public interface DeviceStatisticsMapper extends BaseMapper<DeviceStatisticsPO, Integer> {
}