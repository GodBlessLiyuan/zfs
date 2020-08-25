package com.zfs.server.constant;

/**
 * @author: xiahui
 * @date: Created in 2019/11/6 14:33
 * @description: 卡密常量
 * @version: 1.0
 */
public class BatchInfoConstant {
    /**
     * 未激活
     */
    public static final byte INACTIVATED = 1;
    /**
     * 已激活
     */
    public static final byte ACTIVATED = 2;
    /**
     * 已冻结
     */
    public static final byte FROZEN = 3;
    /**
     * 已失效
     */
    public static final byte EXPIRED = 4;
    //结束
    public static final byte END = 5;
    //过期失效
    public static final byte OVER_EXPIRED = 6;
    //冻结失效
    public static final byte OVER_FROZEN = 7;

}
