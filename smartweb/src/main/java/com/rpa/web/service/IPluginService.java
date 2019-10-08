package com.rpa.web.service;

import com.rpa.web.dto.PluginDTO;
import com.rpa.web.utils.DTPageInfo;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 20:08
 * @description: 插件更新
 * @version: 1.0
 */
public interface IPluginService {

    /**
     * 插入
     *
     * @param url         插件地址
     * @param appId       应用Id
     * @param softChannel 渠道
     * @param context     插件内容
     * @param extra       插件备注
     * @return
     */
    int insert(String url, int appId, int[] softChannel, String context, String extra);

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
     * 状态更新
     *
     * @param pluginId
     * @param status
     * @return
     */
    int updateStatus(int pluginId, byte status);

    /**
     * 删除
     * @param pluginId
     * @return
     */
    int delete(int pluginId);
}
