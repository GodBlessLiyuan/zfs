package com.rpa.server.mapper;

import com.rpa.common.pojo.VipcommodityPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * VipcommodityMapper继承基类
 */
@Mapper
public interface VipCommodityMapper extends BaseMapper<VipcommodityPO, Integer> {
    /**
     * 根据渠道Id查询数据
     *
     * @param chanDef
     * @return
     */
    List<VipcommodityPO> queryByChanId(int chanDef);
}