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
    private String projectName;
    private Byte updateType;
    private Integer[] softChannel;
    private String context;
    private String extra;
}
