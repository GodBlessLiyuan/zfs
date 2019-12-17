package com.rpa.server.mapper;

import com.rpa.common.pojo.RevenueUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * RevenueUserMapper继承基类
 */
@Mapper
public interface RevenueUserMapper extends BaseMapper<RevenueUserPO, Long> {
}