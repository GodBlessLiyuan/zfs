package com.rpa.server.mapper;

import com.rpa.common.pojo.AdconfigPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AdConfigMapper继承基类
 */
@Mapper
public interface AdConfigMapper extends BaseMapper<AdconfigPO, Integer> {
    List<AdconfigPO> query(List<Integer> adIds);
}