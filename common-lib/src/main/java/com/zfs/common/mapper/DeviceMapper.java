package com.zfs.common.mapper;

import com.zfs.common.pojo.DevicePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceMapper继承基类
 */
@Mapper
public interface DeviceMapper extends BaseMapper<DevicePO, Long> {
    int queryTodayNewUser();

    DevicePO queryByUUID(String uuid);
}