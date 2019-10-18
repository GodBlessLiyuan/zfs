package com.rpa.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 15:10
 * @description: 注册/登录
 * @version: 1.0
 */
@Data
public class LoginVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private Long ud;
    /**
     * 用户唯一标识的变种md5
     */
    private String um;
    /**
     * 用户设备唯一标识
     */
    private Integer udd;
    /**
     * 用户签名验证
     */
    private String token;
}
