package com.rpa.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 10:47
 * @description: 基础信息
 * @version: 1.0
 */
@Data
public class ConfigVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String index;
    private String train;
    private String qq;
    private String wx;
    private String shareurl;
    private String problemurl;
    private String freevip;
    private String protocol;
    private String joinqqcode;
}
