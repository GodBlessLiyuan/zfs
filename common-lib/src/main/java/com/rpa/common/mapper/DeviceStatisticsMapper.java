package com.rpa.common.mapper;

import com.rpa.common.pojo.DeviceStatisticsPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceStatisticsMapper继承基类
 */
@Mapper
public interface DeviceStatisticsMapper extends BaseMapper<DeviceStatisticsPO, Integer> {
    int queryDayActiveUser();

    int queryMonthActiveUser();
}