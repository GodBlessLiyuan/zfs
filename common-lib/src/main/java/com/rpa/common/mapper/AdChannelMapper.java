package com.rpa.common.mapper;

import com.rpa.common.bo.AdChannelBO;
import com.rpa.common.pojo.AdChannelPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AdChannelMapper继承基类
 */
@Mapper
public interface AdChannelMapper extends BaseMapper<AdChannelPO, Integer> {
    AdChannelBO queryByIds(Integer adId, Integer appId, Integer softChannelId);

    void update(AdChannelPO po);

    void update2(Integer adId, Byte status);

    AdChannelPO queryByIds2(Integer adId, Integer appId, Integer softChannelId);

    List<Integer> querySoftChannelIdsByAdId(Integer adId);
}