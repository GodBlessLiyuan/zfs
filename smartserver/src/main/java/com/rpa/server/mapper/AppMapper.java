package com.rpa.server.mapper;

import com.rpa.server.pojo.AppPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AppMapper继承基类
 */
@Mapper
public interface AppMapper extends BaseMapper<AppPO, Integer> {
}