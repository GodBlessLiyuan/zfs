package com.rpa.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:44
 * @description: 通知消息
 * @version: 1.0
 */
@Data
public class NoticeDTO implements Serializable {
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
     * 用户唯一标识id
     */
    private Long ud;
    /**
     * 用户唯一标识id的md5
     */
    private String md;
    /**
     * 用户设备唯一标识
     */
    private Integer udd;
}
