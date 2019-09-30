package com.rpa.web.mapper;

import com.rpa.web.pojo.PluginPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * PluginMapper继承基类
 */
@Mapper
public interface PluginMapper extends BaseDAO<PluginPO, Integer> {
}