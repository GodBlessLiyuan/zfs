package com.zfs.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-18 18:52
 */
@Data
public class PluginDTO implements Serializable {
    private String[] file;//文件名
    private Byte type;//插件类型
    private Integer[] appIdS;//支持版本
    private Integer[] softChannelS;//更新渠道
    private String context;//更新内容
    private String extra;//备注
    private Integer pluginId;//主键
}
