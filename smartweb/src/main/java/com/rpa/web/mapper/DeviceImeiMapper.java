package com.rpa.web.mapper;

import com.rpa.web.pojo.DeviceImeiPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * DeviceImeiMapper继承基类
 */
@Mapper
public interface DeviceImeiMapper extends BaseDAO<DeviceImeiPO, String> {
}