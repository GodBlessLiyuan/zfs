package com.rpa.server.mapper;

import com.rpa.server.pojo.UserDevicePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDeviceMapper继承基类
 */
@Mapper
public interface UserDeviceMapper extends BaseMapper<UserDevicePO, Integer> {
}