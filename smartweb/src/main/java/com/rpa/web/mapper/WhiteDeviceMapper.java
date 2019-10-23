package com.rpa.web.mapper;

import com.rpa.web.pojo.WhiteDevicePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * WhiteDeviceMapper继承基类
 */
@Mapper
public interface WhiteDeviceMapper extends BaseDAO<WhiteDevicePO, WhiteDevicePO> {


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
}