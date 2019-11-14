package com.rpa.rabbit.mapper;

import com.rpa.rabbit.pojo.RevenueUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * RevenueUserMapper继承基类
 */
@Mapper
public interface RevenueUserMapper extends BaseMapper<RevenueUserPO, Long> {
}