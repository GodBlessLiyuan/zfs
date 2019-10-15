package com.rpa.server.mapper;

import com.rpa.server.pojo.PromoterPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * PromoterMapper继承基类
 */
@Mapper
public interface PromoterMapper extends BaseMapper<PromoterPO, Integer> {
}