package com.rpa.server.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 8:48
 * @description: 上传异常log
 * @version: 1.0
 */
@Data
public class ExceptionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String verify;
    private Byte osv;
    private Integer softv;
    private String model;
    private String exception;
    private String pkg;
}
