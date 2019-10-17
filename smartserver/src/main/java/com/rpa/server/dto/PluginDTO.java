package com.rpa.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 9:33
 * @description: 插件更新
 * @version: 1.0
 */
@Data
public class PluginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 设备唯一标识
     */
    private Long id;
    /**
     * 设备注册接口返回的md5值
     */
    private String verify;
    /**
     * 插件版本
     */
    private Integer pluginv;
}
