package com.rpa.server.mapper;

import com.rpa.server.pojo.FunctionVideoPO;
import org.apache.ibatis.annotations.Mapper;


/**
 * FunctionVideoMapper继承基类
 */
@Mapper
public interface FunctionVideoMapper extends BaseMapper<FunctionVideoPO, Integer> {
    String queryUrl(String function);
}