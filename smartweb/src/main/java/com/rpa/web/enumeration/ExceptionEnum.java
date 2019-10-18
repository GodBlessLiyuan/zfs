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
    IMEI_INPUT_ERROR(1000, "输入的imei号没有对应的设备！"),

    /**
     * imei已存在
     */
    IMEI_EXIST(1001, "输入的imei已在白名单中！"),

    PASSWORD_ERROR(1002, "您输入的密码有误，请重新输入！"),

    PASSWORD_UPDATE_ERROR(1003, "修改密码失败！"),

    INSERT_ERROR(1004, "新增失败！"),

    UPDATE_ERROR(1005, "修改失败！"),

    DELETE_ERROR(1006, "删除失败！"),

    QUERY_ERROR(1007, "查询失败！")

    ;



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
