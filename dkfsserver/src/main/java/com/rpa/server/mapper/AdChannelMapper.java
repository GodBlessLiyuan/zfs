package com.rpa.server.mapper;

import com.rpa.common.pojo.AdChannelPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AdChannelMapper继承基类
 */
@Mapper
public interface AdChannelMapper extends BaseMapper<AdChannelPO, AdChannelPO> {
    List<Integer> queryAdIds(int softChannelId, int appId);
}