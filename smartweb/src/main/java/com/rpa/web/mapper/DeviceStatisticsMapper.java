package com.rpa.web.mapper;

import com.rpa.web.pojo.DeviceStatisticsPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceStatisticsMapper继承基类
 */
@Mapper
public interface DeviceStatisticsMapper extends BaseDAO<DeviceStatisticsPO, Integer> {
    int queryDayActiveUser();

    int queryMonthActiveUser();
}