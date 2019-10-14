package com.rpa.web.mapper;

import com.rpa.web.pojo.UserDevicePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDeviceMapper继承基类
 */
@Mapper
public interface UserDeviceMapper extends BaseDAO<UserDevicePO, Integer> {
    int queryNewUser();
}