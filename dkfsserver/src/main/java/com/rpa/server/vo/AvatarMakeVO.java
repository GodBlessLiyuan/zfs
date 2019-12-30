package com.rpa.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/12/30 10:21
 * @description: 分身制作
 * @version: 1.0
 */
@Data
public class AvatarMakeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Byte type;
    private String url;
}
