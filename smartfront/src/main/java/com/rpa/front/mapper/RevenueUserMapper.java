package com.rpa.front.mapper;

import com.rpa.front.pojo.RevenueUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * RevenueUserMapper继承基类
 */
@Mapper
public interface RevenueUserMapper extends BaseMapper<RevenueUserPO, Long> {
}