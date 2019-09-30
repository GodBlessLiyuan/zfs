package com.rpa.web.mapper;

import com.rpa.web.pojo.WhilteDevicePO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * WhilteDeviceMapper继承基类
 */
@Mapper
public interface WhilteDeviceMapper extends BaseDAO<WhilteDevicePO, WhilteDevicePO> {
}