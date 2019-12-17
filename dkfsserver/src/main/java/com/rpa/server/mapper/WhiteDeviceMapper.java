package com.rpa.server.mapper;

import com.rpa.common.pojo.WhiteDevicePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * WhiteDeviceMapper继承基类
 */
@Mapper
public interface WhiteDeviceMapper extends BaseMapper<WhiteDevicePO, WhiteDevicePO> {
    /**
     * 查询所有白名单设备Id
     * @return
     */
    Set<String> queryAllDevId();
}