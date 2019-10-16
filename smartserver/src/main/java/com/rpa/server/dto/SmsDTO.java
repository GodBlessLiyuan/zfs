package com.rpa.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 9:52
 * @description: 短信验证
 * @version: 1.0
 */
@Data
public class SmsDTO implements Serializable {
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
     * 设备注册接口返回的md5值
     */
    private String verify;
}
