package com.rpa.consumer.mapper;

import com.rpa.consumer.pojo.VipCommodityPO;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

/**
 * VipCommodityMapper继承基类
 */
@Mapping
public interface VipCommodityMapper extends BaseMapper<VipCommodityPO, Integer> {
}