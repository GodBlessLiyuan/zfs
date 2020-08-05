package com.zfs.common.mapper;

import com.zfs.common.bo.ChannelBO;
import com.zfs.common.pojo.ChannelPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ChannelMapper继承基类
 */
@Mapper
public interface ChannelMapper extends BaseMapper<ChannelPO, Integer> {

    String queryUsernameByAid(Integer aId);

    List<ChannelBO> queryProNames();

    List<ChannelPO> queryChanNicknames();
}