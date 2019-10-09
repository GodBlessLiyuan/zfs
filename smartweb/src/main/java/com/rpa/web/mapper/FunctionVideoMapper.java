package com.rpa.web.mapper;

import com.rpa.web.pojo.FunctionVideoPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * FunctionVideoMapper继承基类
 */
@Mapper
public interface FunctionVideoMapper extends BaseDAO<FunctionVideoPO, Integer> {
}