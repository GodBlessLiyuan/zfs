package com.zfs.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/15 15:37
 * @description: 设备请求返回结果
 * @version: 1.0
 */
@Data
public class DeviceVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 设备唯一标识
     */
    private Long id;
    /**
     * 设备唯一标识的md5值
     */
    private String verify;
}
