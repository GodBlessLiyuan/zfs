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
     * 验证设备的状态码
     * 万一还有改的，所以预留一个状态码
     * */
    public static ResultVO varifyDevice(){
        return new ResultVO(2003);
    }

    /***
     * token失效，验证不通过，
     * 比如验证和token生存时间过期。
     * */
    public static ResultVO tokenInvalid(){
        return new ResultVO(2000);
    }
    /****
     * 登出：登录退出
     * 这里仅仅用在重新获取token时候使用。
     *
     * */
    public static ResultVO logOut(){
        return new ResultVO(2001);
    }
    /***
     * 服务器内部错误
     * */
    public static ResultVO serverInnerError(){
        return new ResultVO(2002);
    }
    /**
     * web服务的增删改的失败状态
     * */
    public static ResultVO addFailure(){
        return new ResultVO(2002);
    }
    /**
     * web服务的用户名重名状态
     * */
    public static ResultVO adminUserDupliName(){
        return new ResultVO(3001);
    }
    /**
     * 渠道名重复
     * */
    public static ResultVO softNameDup(){
        return new ResultVO(3003);
    }
}
