package com.zfs.server.constant;

import java.util.regex.Pattern;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 10:04
 * @description: 注册/登录 常量
 * @version: 1.0
 */
public class LoginConstant {

    /**
     * 验证码缓存key前缀
     */
    public static final String VERIFY_CODE_PREFIX = "sms_";
    /**
     * 手机号长度
     */
    public static final int PHONE_LENGTH = 11;
    /**
     * 验证码长度
     */
    public static final int VERIFY_CODE_LENGTH = 6;
    /**
     * 中国手机号码
     */
    public static Pattern CHINESE_PHONE_PATTERN = Pattern.compile("((13|15|17|18|19)\\d{9})|(14[57]\\d{8})");
}
