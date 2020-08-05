package com.zfs.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: dangyi
 * @date: Created in 11:22 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Data
public class ShareactivityVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer mid;
    private Integer type;
    private String content;
}
