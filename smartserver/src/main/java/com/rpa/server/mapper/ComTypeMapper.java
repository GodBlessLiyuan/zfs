package com.rpa.server.mapper;

import com.rpa.server.pojo.ComTypePO;
import org.apache.ibatis.annotations.Mapper;

/**
 * ComTypeMapper继承基类
 */
@Mapper
public interface ComTypeMapper extends BaseMapper<ComTypePO, Integer> {
}