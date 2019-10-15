package com.rpa.server.mapper;

import com.rpa.server.pojo.VipCommodityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * VipcommodityMapper继承基类
 */
@Mapper
public interface VipCommodityMapper extends BaseMapper<VipCommodityPO, Integer> {
}