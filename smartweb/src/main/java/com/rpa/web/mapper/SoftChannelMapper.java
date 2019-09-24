package com.rpa.web.mapper;

import com.rpa.web.pojo.SoftChannelPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * SoftChannelMapper继承基类
 */
@Mapper
public interface SoftChannelMapper extends BaseDAO<SoftChannelPO, Integer> {
}