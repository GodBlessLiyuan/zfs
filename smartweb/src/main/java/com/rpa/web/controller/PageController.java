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
    public String userList() {
        return "userinfo";
    }

    /**
     * 产品列表
     *
     * @return
     */
    @RequestMapping("/comtype")
    public String comtype() {
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
    public String otherapp() {
        return "otherapp";
    }

    /**
     * 微信白名单
     *
     * @return
     */
    @RequestMapping("/wxsupport")
    public String wxsupport() {
        return "wxsupport";
    }
}
