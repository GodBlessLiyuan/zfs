package com.rpa.server.mapper;

import com.rpa.common.pojo.ChannelPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ChannelMapper继承基类
 */
@Mapper
public interface ChannelMapper extends BaseMapper<ChannelPO, Integer> {
}