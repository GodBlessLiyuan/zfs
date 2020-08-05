package com.zfs.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 9:10
 * @description: 版本更新
 * @version: 1.0
 */
@Data
public class AppVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 更新应用下载链接地址
     */
    private String url;
    /**
     * 下载应用的md5值
     */
    private String md5;
    /**
     * 更新类型
     */
    private Byte type;
    /**
     * 跟新内容
     */
    private String context;
    /**
     * 应用的版本号versioncode
     */
    private Integer code;
    /**
     * 应用的版本名称
     */
    private String versionname;
}
