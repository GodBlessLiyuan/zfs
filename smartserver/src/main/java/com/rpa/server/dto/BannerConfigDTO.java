package com.rpa.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:06
 * @description: banner广告信息
 * @version: 1.0
 */
@Data
public class BannerConfigDTO implements Serializable {
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
     * 应用版本
     */
    private Integer softv;
    /**
     * 应用渠道
     */
    private String channel;
}
