package com.rpa.web.mapper;

import com.rpa.web.pojo.VipCommodityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * VipCommodityMapper继承基类
 */
@Mapper
public interface VipCommodityMapper extends BaseDAO<VipCommodityPO, Integer> {
}