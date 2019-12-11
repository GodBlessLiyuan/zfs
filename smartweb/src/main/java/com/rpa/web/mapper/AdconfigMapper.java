package com.rpa.web.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * AdconfigMapper继承基类
 */
@Mapper
public interface AdconfigMapper extends BaseDAO<AdconfigPO, Integer> {
    String queryUsernameByAid(Integer aId);
}