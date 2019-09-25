package com.rpa.web.mapper;

import com.rpa.web.pojo.ChannelPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ChannelMapper继承基类
 */
@Mapper
public interface ChannelMapper extends BaseDAO<ChannelPO, Integer> {
}