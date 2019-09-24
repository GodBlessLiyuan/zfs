package com.rpa.web.mapper;

import com.rpa.web.pojo.AdconfigPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AdconfigMapper继承基类
 */
@Mapper
public interface AdconfigMapper extends BaseDAO<AdconfigPO, Integer> {
}