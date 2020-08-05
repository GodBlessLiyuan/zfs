package com.zfs.common.mapper;

import com.zfs.common.pojo.WhiteDevicePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * WhiteDeviceMapper继承基类
 */
@Mapper
public interface WhiteDeviceMapper extends BaseMapper<WhiteDevicePO, WhiteDevicePO> {
    /**
     * 删除
     *
     * @param deviceId 设备id
     * @return
     */
    int deleteByDeviceId(int deviceId);

    /**
     * 查询
     *
     * @param deviceId 设备id
     * @return
     */
    List<WhiteDevicePO> queryByDeviceId(Long deviceId);

    /**
     * 查询所有白名单设备Id
     *
     * @return
     */
    Set<String> queryAllDevId();
}