package com.rpa.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/12/24 15:07
 * @description: 分身应用
 * @version: 1.0
 */
@Data
public class AvatarVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 更新类型
     */
    private Byte type;

    /**
     * 分身Id
     */
    private Long id;
}
