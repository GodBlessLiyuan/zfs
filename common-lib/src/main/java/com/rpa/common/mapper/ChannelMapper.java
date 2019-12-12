package com.rpa.common.mapper;

import com.rpa.common.bo.ChannelBO;
import com.rpa.common.pojo.ChannelPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ChannelMapper继承基类
 */
@Mapper
public interface ChannelMapper extends BaseMapper<ChannelPO, Integer> {

    String queryUsernameByAid(Integer aId);

    List<ChannelBO> queryProNames();

    List<ChannelPO> queryChanNicknames();
}