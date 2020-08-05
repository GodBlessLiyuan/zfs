package com.zfs.common.vo;

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

    public ResultVO(){
        this(1000);
    }

    public ResultVO(Integer status) {
        this(status, null);
    }

    public ResultVO(Integer status, T data) {
        this.status = status;
        this.data = data;
    }
    /**
     * 参数
     * */
    public static ResultVO paramsError(){
        return new ResultVO(1003);
    }
    /***
     * token失效
     * */
    public static ResultVO logOut(){
        return new ResultVO(2001);
    }
}
