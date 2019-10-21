package com.rpa.web.mapper;

import com.rpa.web.pojo.ChannelPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ChannelMapper继承基类
 */
@Mapper
public interface ChannelMapper extends BaseDAO<ChannelPO, Integer> {
    String queryUsernameByAid(Integer aId);

    List<ChannelPO> queryProNames();
}