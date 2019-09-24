package com.rpa.web.mapper;

import com.rpa.web.pojo.DevicePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceMapper继承基类
 */
@Mapper
public interface DeviceMapper extends BaseDAO<DevicePO, Long> {
}