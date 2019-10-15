package com.rpa.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/15 15:44
 * @description: 返回结果
 * @version: 1.0
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private T data;

    public ResultVO(Integer code, T data) {
        this.code = code;
        this.data = data;
    }
}
