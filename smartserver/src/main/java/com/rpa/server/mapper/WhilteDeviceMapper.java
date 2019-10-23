package com.rpa.server.mapper;

import com.rpa.server.pojo.WhilteDevicePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * WhilteDeviceMapper继承基类
 */
@Mapper
public interface WhilteDeviceMapper extends BaseMapper<WhilteDevicePO, WhilteDevicePO> {
    /**
     * 查询所有白名单设备Id
     * @return
     */
    Set<String> queryAllDevId();
}