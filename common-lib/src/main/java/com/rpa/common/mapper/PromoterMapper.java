package com.rpa.common.mapper;

import com.rpa.common.pojo.PromoterPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * PromoterMapper继承基类
 */
@Mapper
public interface PromoterMapper extends BaseMapper<PromoterPO, Integer> {
    List<PromoterPO> queryAllProNames();
}