package com.zfs.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 14:22
 * @description: 插件更新
 * @version: 1.0
 */
@Data
public class PluginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 插件的版本id
     */
    private Long pluginv;
    /**
     * 更新应用下载链接地址
     */
    private String url;
    /**
     * 插件的md5值
     */
    private String md5;
}
