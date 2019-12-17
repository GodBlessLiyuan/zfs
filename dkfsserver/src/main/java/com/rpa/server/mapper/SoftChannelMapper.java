package com.rpa.server.mapper;

import com.rpa.common.pojo.SoftChannelPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * SoftChannelMapper继承基类
 */
@Mapper
public interface SoftChannelMapper extends BaseMapper<SoftChannelPO, Integer> {
    Integer queryIdbyName(String chanName);
}