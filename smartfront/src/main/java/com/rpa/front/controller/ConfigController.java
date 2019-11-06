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

    @GetMapping("problem")
    public String problem() {
        return "common_problem";
    }

    @GetMapping("freevip")
    public String freevip() {
        return "freemember_index";
    }

    @GetMapping("memberrights")
    public String viprights() {
        return "member_rights";
    }

    @GetMapping("protocol")
    public String serviceprotocol() {
        return "member_service_protocol";
    }

    @GetMapping("viprights")
    public String freemember() {
        return "settings_member_service_protocol";
    }
}

