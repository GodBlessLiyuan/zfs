package com.rpa.pay.dto.base;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 9:50
 * @description: Token 验证DTO
 * @version: 1.0
 */
@Data
public class TokenDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 设备唯一标识
     */
    private Long id;
    /**
     * 用户唯一标识id
     */
    private Long ud;
    /**
     * 用户设备唯一标识
     */
    private Integer udd;
}
