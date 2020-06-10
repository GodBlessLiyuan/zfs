package com.rpa.pay.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: dangyi
 * @date: Created in 17:41 2019/11/6
 * @version: 1.0.0
 * @description:
 */
@Data
public class ResultVO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;

    private T data;
    public ResultVO(){}
    public ResultVO(Integer status) {
        this(status, null);
    }

    public ResultVO(Integer status, T data) {
        this.status = status;
        this.data = data;
    }
}
