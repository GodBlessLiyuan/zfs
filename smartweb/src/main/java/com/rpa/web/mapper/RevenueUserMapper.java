package com.rpa.web.mapper;

import com.rpa.web.pojo.RevenueUserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * RevenueUserMapper继承基类
 */
@Mapper
public interface RevenueUserMapper extends BaseDAO<RevenueUserPO, Long> {
    String queryPhoneByUserId(Long userId);
}