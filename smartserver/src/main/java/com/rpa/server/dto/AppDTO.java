package com.rpa.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 8:52
 * @description: 应用更新请求参数
 * @version: 1.0
 */
@Data
public class AppDTO implements Serializable {
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
}
