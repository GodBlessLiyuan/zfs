package com.rpa.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: CHL
 * @date: Created in 2019/10/25 18:48
 * @description: 基础配置页面
 * @version: 1.0
 */
@RequestMapping("v1.0")
@Controller
public class ConfigController {

    /**
     * 帮助
     *
     * @return
     */
    @GetMapping("problem")
    public String problem() {
        return "common_problem";
    }

    /**
     * 会员免费领取
     *
     * @return
     */
    @GetMapping("freevip")
    public String freevip() {
        return "freemember_index";
    }

    /**
     * 会员中心——会员权益
     *
     * @return
     */
    @GetMapping("memberrights")
    public String viprights() {
        return "member_rights";
    }

    /**
     * 会员中心——会员服务协议
     *
     * @return
     */
    @GetMapping("protocol")
    public String serviceprotocol() {
        return "member_service_protocol";
    }

    /**
     * 设置-砖助智能助手服务使用协议
     *
     * @return
     */
    @GetMapping("memberprotocol")
    public String freemember() {
        return "settings_member_service_protocol";
    }
}

