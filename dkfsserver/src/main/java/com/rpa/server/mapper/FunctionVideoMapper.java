package com.rpa.server.mapper;

import com.rpa.common.pojo.FunctionvideoPO;
import org.apache.ibatis.annotations.Mapper;


/**
 * FunctionVideoMapper继承基类
 */
@Mapper
public interface FunctionVideoMapper extends BaseMapper<FunctionvideoPO, Integer> {
    String queryUrl(String function);
}