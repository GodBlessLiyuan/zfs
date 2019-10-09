package com.rpa.web.mapper;

import com.rpa.web.pojo.AppPluChPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * AppPluChMapper继承基类
 */
@Mapper
public interface AppPluChMapper extends BaseDAO<AppPluChPO, Integer> {

    /**
     * 批量插入
     *
     * @param records 记录集
     * @return
     */
    int batchInsert(List<AppPluChPO> records);

    /**
     * 根据插件Id更新状态
     *
     * @param pluginId 插件Id
     * @param status   状态
     * @return
     */
    int updateStatus(int pluginId, byte status);

    /**
     * 根据插件Id删除数据
     *
     * @param pluginId 插件Id
     * @return
     */
    int deleteByAppId(int pluginId);

    /**
     * 根据PluginId 和 AppId 查询渠道Ids
     *
     * @param pluginId 插件Id
     * @param appId    应用Id
     * @return
     */
    List<AppPluChPO> queryByIds(int pluginId, int appId);

    /**
     * 批量删除
     *
     * @param apcIds 待删除记录
     * @return
     */
    int batchDelete(List<Integer> apcIds);
}