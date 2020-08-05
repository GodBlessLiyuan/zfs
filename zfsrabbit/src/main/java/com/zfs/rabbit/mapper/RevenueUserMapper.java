package com.zfs.rabbit.mapper;

import com.zfs.rabbit.pojo.RevenueUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * RevenueUserMapper继承基类
 */
@Mapper
public interface RevenueUserMapper extends BaseMapper<RevenueUserPO, Long> {
}