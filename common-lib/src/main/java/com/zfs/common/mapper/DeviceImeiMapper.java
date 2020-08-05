package com.zfs.common.mapper;

import com.zfs.common.pojo.DeviceImeiPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DeviceImeiMapper继承基类
 */
@Mapper
public interface DeviceImeiMapper extends BaseMapper<DeviceImeiPO, String> {
    /**
     * 查询
     *
     * @param imei
     * @return
     */
    List<DeviceImeiPO> queryByImei(String imei);

    List<Long> queryDevIdsByImei(List<String> imei);

    int batchInsert(List<DeviceImeiPO> devImeiPOs);
}