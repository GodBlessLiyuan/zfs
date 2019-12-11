package com.rpa.common.mapper;

import com.rpa.common.pojo.VipcommodityPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * VipcommodityMapper继承基类
 */
@Mapper
public interface VipcommodityMapper extends BaseMapper<VipcommodityPO, Integer> {
    /**
     * 根据渠道Id和产品Id查询数据
     *
     * @param softChannelId 渠道Id
     * @param comTypeId     产品Id
     * @return
     */
    VipcommodityPO queryByChanIdAndComTypeId(int softChannelId, int comTypeId);
}