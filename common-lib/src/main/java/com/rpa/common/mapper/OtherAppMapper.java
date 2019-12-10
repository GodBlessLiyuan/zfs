package com.rpa.common.mapper;

import com.rpa.common.pojo.OtherAppPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * OtherAppMapper继承基类
 */
@Mapper
public interface OtherAppMapper extends BaseMapper<OtherAppPO, Integer> {
}