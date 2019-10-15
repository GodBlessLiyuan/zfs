package com.rpa.web.mapper;

import com.rpa.web.pojo.VipCommodityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * VipcommodityMapper继承基类
 */
@Mapper
public interface VipCommodityMapper extends BaseDAO<VipCommodityPO, Integer> {
    Float queryRevenue();

    Float queryMonthRevenue();
}