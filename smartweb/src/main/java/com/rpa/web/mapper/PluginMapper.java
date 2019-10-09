package com.rpa.web.mapper;

import com.rpa.web.pojo.PluginPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * PluginMapper继承基类
 */
@Mapper
public interface PluginMapper extends BaseDAO<PluginPO, Integer> {

    /**
     * 根据插件Id查询数据
     *
     * @param pluginId
     * @return
     */
    List<PluginPO> queryById(int pluginId);
}