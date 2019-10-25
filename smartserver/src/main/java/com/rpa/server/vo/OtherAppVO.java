package com.rpa.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 11:34
 * @description: 其他应用
 * @version: 1.0
 */
@Data
public class OtherAppVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String iconurl;
    private Byte type;
    private String appurl;
    private String extra;
}
