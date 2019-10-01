package com.rpa.web.mapper;

import com.rpa.web.pojo.DeviceImeiPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * DeviceImeiMapper继承基类
 */
@Mapper
public interface DeviceImeiMapper extends BaseDAO<DeviceImeiPO, String> {

    /**
     * 查询
     *
     * @param imei
     * @return
     */
    List<DeviceImeiPO> queryByImei(String imei);
}