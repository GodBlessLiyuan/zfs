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
    /**
     * 用户未登录
     */
    public static final int USER_NOT_LOGIN = 1004;
    /**
     * 邀请链接有误，注册失败
     */
    public static final int SHARE_CODE_ERROR = 1005;
    /**
     * 手机号已注册
     */
    public static final int PHONE_REGISTERED = 1006;
    /**
     * 手机号已被邀请
     */
    public static final int PHONE_INVITED = 1007;
}
