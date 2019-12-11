package com.rpa.common.mapper;

import com.rpa.common.pojo.PluginPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * PluginMapper继承基类
 */
@Mapper
public interface PluginMapper extends BaseMapper<PluginPO, Integer> {
}