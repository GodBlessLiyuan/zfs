package com.rpa.server.mapper;

import com.rpa.common.pojo.UserDevicePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserDeviceMapper继承基类
 */
@Mapper
public interface UserDeviceMapper extends BaseMapper<UserDevicePO, Integer> {
    /**
     * 登出当前设备所有在线用户
     *
     * @param deviceId
     */
    void signOutByDevId(Long deviceId);

    /**
     * 登出当前用户所有在线设备
     *
     * @param userId
     */
    void signOutByUserId(Long userId);

    /**
     * 根据设备Id和用户Id查询数据
     *
     * @param deviceId
     * @param userId
     * @return
     */
    UserDevicePO queryByDevIdAndUserId(Long deviceId, Long userId);

    int queryTodayNewUser();
}