package com.rpa.server.mapper;

import com.rpa.server.pojo.PluginPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * PluginMapper继承基类
 */
@Mapper
public interface PluginMapper extends BaseMapper<PluginPO, Integer> {
}