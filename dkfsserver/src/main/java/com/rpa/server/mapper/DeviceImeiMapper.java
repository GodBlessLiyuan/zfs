package com.rpa.server.mapper;

import com.rpa.common.pojo.DeviceImeiPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DeviceImeiMapper继承基类
 */
@Mapper
public interface DeviceImeiMapper extends BaseMapper<DeviceImeiPO, String> {
    List<Long> queryDevIdsByImei(List<String> imei);

    void batchInsert(List<DeviceImeiPO> devImeiPOs);
}