package com.rpa.common.mapper;

import com.rpa.common.pojo.BlankAppPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * BlankAppMapper继承基类
 */
@Mapper
public interface BlankAppMapper extends BaseMapper<BlankAppPO, Long> {
}