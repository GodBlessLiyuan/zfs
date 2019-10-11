package com.rpa.web.service;

import com.rpa.web.dto.PluginDTO;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 20:08
 * @description: 插件更新
 * @version: 1.0
 */
public interface IPluginService {

    /**
     * 分页查询
     *
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<PluginDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 根据插件Id 查询数据
     *
     * @param pluginId 插件Id
     * @return
     */
    List<PluginDTO> queryById(int pluginId);

    /**
     * 根据PluginId 和 AppId 查询渠道Ids
     *
     * @param pluginId
     * @param appId
     * @return
     */
    List<Integer> querySoftChannelByIds(int pluginId, int appId);


    /**
     * 插入
     *
     * @param file        插件地址
     * @param appId       应用Id
     * @param softChannel 渠道
     * @param context     插件内容
     * @param extra       插件备注
     * @return
     */
    int insert(MultipartFile file, int appId, int[] softChannel, String context, String extra);

    /**
     * 状态更新
     *
     * @param pluginId
     * @param status
     * @return
     */
    int updateStatus(int pluginId, byte status);

    /**
     * 删除
     *
     * @param pluginId
     * @return
     */
    int delete(int pluginId);

    /**
     * 更新
     *
     * @param pluginId    插件Id
     * @param file        apk地址
     * @param appId       应用Id
     * @param softChannel 渠道
     * @param context     内容
     * @param extra       备注
     * @return
     */
    int update(int pluginId, MultipartFile file, int appId, int[] softChannel, String context, String extra);
}
