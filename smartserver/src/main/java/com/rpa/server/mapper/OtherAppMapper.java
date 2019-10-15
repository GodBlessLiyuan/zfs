package com.rpa.server.mapper;

import com.rpa.server.pojo.OtherAppPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * OtherAppMapper继承基类
 */
@Mapper
public interface OtherAppMapper extends BaseMapper<OtherAppPO, Integer> {
}