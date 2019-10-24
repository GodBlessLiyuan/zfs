package com.rpa.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/24 16:40
 * @description: 订单
 * @version: 1.0
 */
@Data
public class OrderDTO implements Serializable {
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
