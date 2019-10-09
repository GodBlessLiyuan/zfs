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
        return "userinfo";
    }

    /**
     * 产品列表
     *
     * @return
     */
    @RequestMapping("/comtype")
    public String comType() {
        return "comtype";
    }

    /**
     * 商品列表
     *
     * @return
     */
    @RequestMapping("/vipcommodity")
    public String commodity() {
        return "vipcommodity";
    }

    /**
     * 渠道信息
     *
     * @return
     */
    @RequestMapping("/softchannel")
    public String softChannel() {
        return "softchannel";
    }

    /**
     * 未注册用户信息
     *
     * @return
     */
    @RequestMapping("/unregistered")
    public String unregistered() {
        return "unregistered";
    }

    /**
     * 其他产品
     *
     * @return
     */
    @RequestMapping("/otherapp")
    public String otherApp() {
        return "otherapp";
    }

    /**
     * 微信白名单
     *
     * @return
     */
    @RequestMapping("/wxsupport")
    public String wxSupport() {
        return "wxsupport";
    }

    /**
     * 测试白名单
     *
     * @return
     */
    @RequestMapping("/whiltedevice")
    public String whilteDevice() {
        return "whiltedevice";
    }

    /**
     * 版本更新
     *
     * @return
     */
    @RequestMapping("/appversion")
    public String app() {
        return "appversion";
    }

    /**
     * 新用户送会员
     *
     * @return
     */
    @RequestMapping("/newusergifts")
    public String userGifts() {
        return "newusergifts";
    }

    /**
     * 新用户赠送记录
     *
     * @return
     */
    @RequestMapping("/newuserrecord")
    public String newUserRecord() {
        return "newuserrecord";
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
        return "user_activity";
    }

    /**
     * 用户会员数据
     * @return
     */
    @RequestMapping("/uservip")
    public String userVip() {
        return "user_vip_index";
    }
}
