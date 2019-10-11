package com.rpa.web.enumeration;

import lombok.Getter;

/**
 * @author: xiahui
 * @date: Created in 2019/10/11 10:33
 * @description: 枚举异常
 * @version: 1.0
 */
@Getter
public enum ExceptionEnum {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * imei输入有误
     */
    IMEI_INPUT_ERROR(100, "输入的imei号没有对应的设备！"),

    /**
     * imei已存在
     */
    IMEI_EXIST(101, "输入的imei已在白名单中！");

    /**
     * 提示码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
