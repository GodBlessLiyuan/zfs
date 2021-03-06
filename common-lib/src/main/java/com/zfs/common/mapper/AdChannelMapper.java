package com.zfs.common.mapper;

import com.zfs.common.bo.AdChannelBO;
import com.zfs.common.pojo.AdChannelPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * AdChannelMapper继承基类
 */
@Mapper
public interface AdChannelMapper extends BaseMapper<AdChannelPO, Integer> {
    AdChannelBO queryByIds(@Param("adId")Integer adId, @Param("appId")Integer appId, @Param("softChannelId")Integer softChannelId);

    int update(AdChannelPO po);

    int update2(@Param("adId")Integer adId, @Param("status")Byte status);

    AdChannelPO queryByIds2(@Param("adId")Integer adId, @Param("appId")Integer appId, @Param("softChannelId")Integer softChannelId);

    List<Integer> queryAdIds(@Param("softChannelId")int softChannelId, @Param("appId")int appId);

    List<AdChannelPO> queryByAppId(Integer appId);

    void batchUpdate(List<AdChannelPO> adChannelPOS);

    void batchInsert(List<AdChannelPO> insertAdChannelPOS);
}