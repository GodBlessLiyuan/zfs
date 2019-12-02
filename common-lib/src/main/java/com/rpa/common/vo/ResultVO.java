package com.rpa.common.vo;

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

    private Integer status;

    private T data;

    public ResultVO(Integer status) {
        this(status, null);
    }

    public ResultVO(Integer status, T data) {
        this.status = status;
        this.data = data;
    }
}
