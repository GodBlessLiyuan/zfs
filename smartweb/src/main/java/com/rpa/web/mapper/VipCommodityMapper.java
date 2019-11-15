package com.rpa.web.mapper;

import com.rpa.web.pojo.VipCommodityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * VipcommodityMapper继承基类
 */
@Mapper
public interface VipCommodityMapper extends BaseDAO<VipCommodityPO, Integer> {
    /**
     * 根据渠道Id和产品Id查询数据
     *
     * @param softChannelId 渠道Id
     * @param comTypeId 产品Id
     * @return
     */
    VipCommodityPO queryByChanIdAndComTypeId(int softChannelId, int comTypeId);
}