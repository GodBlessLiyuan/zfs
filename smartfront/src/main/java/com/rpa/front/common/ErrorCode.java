package com.rpa.front.common;

/**
 * @author: xiahui
 * @date: Created in 2019/10/30 18:57
 * @description: 错误码
 * @version: 1.0
 */
public class ErrorCode {
    /**
     * 当前存在尚未结束的提款申请
     */
    public static final int WITHDRAW_UNFINISHED = 1001;
    /**
     * 余额不足
     */
    public static final int INSUFFICIENT_BALANCE = 1002;
    /**
     * Session 超时
     */
    public static final int SESSION_TIMEOUT = 1003;
}
