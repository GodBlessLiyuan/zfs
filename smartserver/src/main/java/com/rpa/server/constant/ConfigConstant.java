package com.rpa.server.constant;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 10:55
 * @description: 获取砖助智能助手的基础信息
 * @version: 1.0
 */
public class ConfigConstant {
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
    /**
     * 会员中心-会员权益
     */
    public static final int VIP_PROTOCOL = 18;
    /**
     * 会员中心-用户服务协议
     */
    public static final int MEMBER_RIGHTS = 19;
    /**
     * 配置消息清粉后缀的短链接
     */
    public static final int CLEAR_FANS = 20;
}
