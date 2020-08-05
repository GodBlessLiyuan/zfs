package com.zfs.rabbit.mapper;

import com.zfs.rabbit.pojo.DevicePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceMapper继承基类
 */
@Mapper
public interface DeviceMapper extends BaseMapper<DevicePO, Long> {
    int queryTodayNewUser();
}