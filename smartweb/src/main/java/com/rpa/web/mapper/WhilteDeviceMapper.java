package com.rpa.web.mapper;

import com.rpa.web.pojo.WhilteDevicePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 查询
     * @param deviceId 设备id
     * @return
     */
    List<WhilteDevicePO> queryByDeviceId(Long deviceId);
}