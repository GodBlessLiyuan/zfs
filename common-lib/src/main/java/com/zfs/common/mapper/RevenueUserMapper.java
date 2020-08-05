package com.zfs.common.mapper;

import com.zfs.common.pojo.RevenueUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * RevenueUserMapper继承基类
 */
@Mapper
public interface RevenueUserMapper extends BaseMapper<RevenueUserPO, Long> {
    String queryPhoneByUserId(Long userId);
}