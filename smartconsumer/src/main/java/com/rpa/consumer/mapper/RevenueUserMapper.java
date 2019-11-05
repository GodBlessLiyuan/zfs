package com.rpa.consumer.mapper;

import com.rpa.consumer.pojo.RevenueUserPO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

/**
 * RevenueUserMapper继承基类
 */
@Mapping
public interface RevenueUserMapper extends BaseMapper<RevenueUserPO, Long> {
}