package com.zfs.common.constant;

/**
 * @author: dangyi
 * @date: Created in 17:12 2019/10/9
 * @version: 1.0.0
 * @description: 常量类
 */
public class Constant {
    /**
     * 基础信息Redis Key
     */
    public static final String REDIS_KEY = "config_redis_key";

    /**
     * 广告策略：广告的展现间隔
     */
    public static final int SHOW_INTERVAL = 1;

    /**
     * 养号教程
     */
    public static final int TUTORIAL_URL = 2;

    /**
     * 用户名
     */
    public static final String ADMIN_USER = "admin_user";


    /**
     * 加密用的盐
     */
    public static final String SALT = "frank";



    /**
     * 养号教程
     */
    public static final int TRAIN = 2;
    /**
     * 此字段为索引值，每次服务器端修改下列属性都需要自增+1，保存到数据库中
     */
    public static final int INDEX = 10;
    /**
     * 加入qq群的链接
     */
    public static final int JOIN_QQ_CODE = 11;
    /**
     * 联系我们中的qq群信息
     */
    public static final int QQ = 12;
    /**
     * 联系我们中的微信公众号信息
     */
    public static final int WX = 13;
    /**
     * 我的-分享给好友的-webview的url
     */
    public static final int SHARE_URL = 14;
    /**
     * 我的-常见问题-webviewe的url
     */
    public static final int PROBLEM_URL = 15;
    /**
     * 个人中心-免费领会员-webview的url
     */
    public static final int FREE_VIP = 16;
    /**
     * 个人中心-设置-用户服务协议-webview的url
     */
    public static final int PROTOCOL = 17;

}
