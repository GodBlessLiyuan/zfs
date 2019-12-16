package com.rpa.server.mapper;

import com.rpa.server.pojo.VipCommodityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * VipcommodityMapper继承基类
 */
@Mapper
public interface VipCommodityMapper extends BaseMapper<VipCommodityPO, Integer> {
    /**
     * 根据渠道Id查询数据
     *
     * @param chanDef
     * @return
     */
    List<VipCommodityPO> queryByChanId(int chanDef);
}