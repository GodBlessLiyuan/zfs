package com.zfs.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-08-18 08:30
 */
@Data
public class AppVersionDTO implements Serializable {
    //主键
    private Integer appId;
    private String[] projectName;
    private String origName;
    private Byte updateType;
    private Integer[] softChannel;
    private String context;
    private String extra;
}
