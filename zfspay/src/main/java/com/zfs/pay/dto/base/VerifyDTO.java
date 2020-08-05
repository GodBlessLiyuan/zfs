package com.zfs.pay.dto.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 11:20
 * @description: 设备验证
 * @version: 1.0
 */
@Data
public class VerifyDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 设备唯一标识
     */
    private Long id;
    /**
     * 设备注册接口返回的md5值
     */
    private String verify;
}
