package com.zfs.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 10:47
 * @description: 基础信息
 * @version: 1.0
 */
@Data
public class ConfigVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String index;
//    private String train;
//    private String joinqqcode;//加入qq群的链接
//    private String qq;//联系我们中的qq群信息
//    private String wx;
//    private String shareurl;
    private String problemurl;//常见问题-webviewe的url
//    private String freevip;
    private String protocol;//个人中心-设置-用户服务协议-webview的url
    private String vipprotocol;
    private String memberrights;//会员中心 - 会员服务协议
//    private String clearfans;
    private String privacyprotocol;//个人中心-设置-隐私协议-webview的url
}
