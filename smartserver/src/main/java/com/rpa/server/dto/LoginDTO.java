package com.rpa.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 10:58
 * @description: 注册/登录 DTO
 * @version: 1.0
 */
@Data
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 设备唯一标识
     */
    private Long id;
    /**
     * 手机号码
     */
    private String ph;
    /**
     * 设备唯一标识md5，设备接口返回数据
     */
    private String verify;
    /**
     * 短信验证码
     */
    private String sms;
    /**
     * 应用渠道
     */
    private String channel;
}
