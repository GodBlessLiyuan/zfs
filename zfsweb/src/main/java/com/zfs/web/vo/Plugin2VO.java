package com.zfs.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-19 20:05
 */
@Data
public class Plugin2VO implements Serializable {
    private Integer pluginId;
    private Date publishTime;//发布时间
    private String username;//
    private Integer size;//插件大小
    private String context;//插件内容
    // 应用Id和渠道Id组合
    private List<Integer> appIDS;
    private List<String> appNameS;
    private List<Integer> softIDS;
    private List<String> softNames;
    private String appName;
    private String softName;
    private Byte status;//发布状态，1 未发布 2 发布
    private String extra;//备注
    private Byte type;//插件类型
    private Long pluginv;//插件版本
    private String pluginpkg;//插件包名
    private String file;
}
