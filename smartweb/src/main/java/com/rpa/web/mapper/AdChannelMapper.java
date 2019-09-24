package com.rpa.web.mapper;

import com.rpa.web.pojo.AdChannelPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AdChannelMapper继承基类
 */
@Mapper
public interface AdChannelMapper extends BaseDAO<AdChannelPO, AdChannelPO> {
}