package com.rpa.server.mapper;

import com.rpa.server.pojo.PluginPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * PluginMapper继承基类
 */
@Mapper
public interface PluginMapper extends BaseMapper<PluginPO, Integer> {
    /**
     * 查询大于指定版本中VersionId最大的一条记录
     *
     * @param pluginv 插件Id
     * @return
     */
    PluginPO queryMaxByPluId(Integer pluginv);
}