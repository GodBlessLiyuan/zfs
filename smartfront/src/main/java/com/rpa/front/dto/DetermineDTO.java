package com.rpa.front.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/30 9:16
 * @description: 确认提现
 * @version: 1.0
 */
@Data
public class DetermineDTO implements Serializable {
    private static final long SerialVersionUID = 1L;

    private String account;
    private String name;
    private Float money;
}
