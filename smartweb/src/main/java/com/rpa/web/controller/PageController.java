package com.rpa.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: xiahui
 * @date: Created in 2019/9/25 19:34
 * @description: 页面导航
 * @version: 1.0
 */
@Controller
public class PageController {

    /**
     * 用户信息
     *
     * @return
     */
    @RequestMapping("/userinfo")
    public String userInfo() {
        return "user_info_index";
    }

    /**
     * 产品列表
     *
     * @return
     */
    @RequestMapping("/comtype")
    public String comType() {
        return "com_type_index";
    }

    /**
     * 商品列表
     *
     * @return
     */
    @RequestMapping("/vipcommodity")
    public String commodity() {
        return "vip_commodity_index";
    }

    /**
     * 渠道信息
     *
     * @return
     */
    @RequestMapping("/softchannel")
    public String softChannel() {
        return "soft_channel_index";
    }

    /**
     * 未注册用户信息
     *
     * @return
     */
    @RequestMapping("/unregistered")
    public String unregistered() {
        return "unregistered_index";
    }

    /**
     * 其他产品
     *
     * @return
     */
    @RequestMapping("/otherapp")
    public String otherApp() {
        return "other_app_index";
    }

    /**
     * 微信白名单
     *
     * @return
     */
    @RequestMapping("/wxsupport")
    public String wxSupport() {
        return "wxsupport_index";
    }

    /**
     * 测试白名单
     *
     * @return
     */
    @RequestMapping("/whiltedevice")
    public String whilteDevice() {
        return "whilte_device_index";
    }

    /**
     * 版本更新
     *
     * @return
     */
    @RequestMapping("/appversion")
    public String app() {
        return "app_index";
    }

    /**
     * 新用户送会员
     *
     * @return
     */
    @RequestMapping("/newusergifts")
    public String userGifts() {
        return "new_user_gifts_index";
    }

    /**
     * 新用户赠送记录
     *
     * @return
     */
    @RequestMapping("/newuserrecord")
    public String newUserRecord() {
        return "new_user_record_index";
    }

    /**
     * 订单信息
     *
     * @return
     */
    @RequestMapping("/order")
    public String order() {
        return "order_index";
    }

    /**
     * 插件更新
     * @return
     */
    @RequestMapping("/plugin")
    public String plugin() {
        return "plugin_index";
    }

    /**
     * 活动赠送记录
     * @return
     */
    @RequestMapping("/useractivity")
    public String userActivity() {
        return "user_activity_index";
    }

    /**
     * 用户会员数据
     * @return
     */
    @RequestMapping("/uservip")
    public String userVip() {
        return "user_vip_index";
    }

    /**
     * 登录页面
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login_index";
    }
}
