package com.rpa.server.mapper;

import com.rpa.server.pojo.ExceptionPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ExceptionMapper继承基类
 */
@Mapper
public interface ExceptionMapper extends BaseMapper<ExceptionPO, Integer> {
}