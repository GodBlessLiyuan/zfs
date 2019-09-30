package com.rpa.web.mapper;

import com.rpa.web.pojo.WhilteDevicePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * WhilteDeviceMapper继承基类
 */
@Mapper
public interface WhilteDeviceMapper extends BaseDAO<WhilteDevicePO, WhilteDevicePO> {

    /**
     * 删除
     *
     * @param deviceId 设备id
     * @return
     */
    int deleteByDeviceId(int deviceId);
}