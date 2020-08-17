package com.zfs.common.mapper;

import com.zfs.common.bo.PluginBO;
import com.zfs.common.pojo.PluginPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 查询大于指定版本中VersionId最大的一条记录
     *
     * @param pluginv 插件Id
     * @return
     */
    PluginPO queryMaxByPluId(@Param("pluginv") Integer pluginv,@Param("pluginpkg")String pluginpkg);
}