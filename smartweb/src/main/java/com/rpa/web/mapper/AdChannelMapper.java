package com.rpa.web.mapper;

import com.rpa.web.domain.AdChannelDO;
import com.rpa.web.pojo.AdChannelPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AdChannelMapper继承基类
 */
@Mapper
public interface AdChannelMapper extends BaseDAO<AdChannelDO, Integer> {
    AdChannelPO queryByIds(Integer adId, Integer appId, Integer softChannelId);

    void update(AdChannelPO po);
}