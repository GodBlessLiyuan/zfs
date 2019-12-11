package com.rpa.common.mapper;

import com.rpa.common.bo.PluginBO;
import com.rpa.common.pojo.PluginPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * PluginMapper继承基类
 */
@Mapper
public interface PluginMapper extends BaseMapper<PluginPO, Integer> {
    /**
     * 根据插件Id查询数据
     *
     * @param pluginId
     * @return
     */
    List<PluginBO> queryById(int pluginId);
}