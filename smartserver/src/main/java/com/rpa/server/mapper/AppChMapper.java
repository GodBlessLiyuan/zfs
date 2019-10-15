package com.rpa.server.mapper;

import com.rpa.server.pojo.AppChPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * AppChMapper继承基类
 */
@Mapper
public interface AppChMapper extends BaseMapper<AppChPO, Integer> {
}