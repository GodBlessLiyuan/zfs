package com.rpa.web.mapper;

import com.rpa.web.domain.AdChannelDO;
import com.rpa.web.pojo.AdChannelPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AdChannelMapper继承基类
 */
@Mapper
public interface AdChannelMapper extends BaseDAO<AdChannelPO, Integer> {
    AdChannelDO queryByIds(Integer adId, Integer appId, Integer softChannelId);

    void update(AdChannelPO po);

    AdChannelPO queryByIds2(Integer adId, Integer appId, Integer softChannelId);

    List<Integer> querySoftChannelIdsByAdId(Integer adId);
}