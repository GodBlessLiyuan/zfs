package com.rpa.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: xiahui
 * @date: Created in 2019/9/25 9:49
 * @description: 会员中心-产品列表-页面显示
 * @version: 1.0
 */
@Controller
public class ComTypeController {

    @RequestMapping("/comtype")
    public String comtype() {
        return "comtype";
    }
}
