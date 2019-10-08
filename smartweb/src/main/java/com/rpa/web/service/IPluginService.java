package com.rpa.web.service;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 20:08
 * @description: 插件更新
 * @version: 1.0
 */
public interface IPluginService {

    /**
     * 插入
     * @param url
     * @param appId
     * @param softChannel
     * @param context
     * @param extra
     * @return
     */
    int insert(String url, int appId, int[] softChannel, String context, String extra);
}
