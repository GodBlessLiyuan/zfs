package com.rpa.web.mapper;

import com.rpa.web.pojo.VipCommodityPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * VipcommodityMapper继承基类
 */
@Mapper
public interface VipCommodityMapper extends BaseDAO<VipCommodityPO, Integer> {
}