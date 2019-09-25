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
     * @return
     */
    @RequestMapping("/userlist")
    public String userList() {
        return "userlist";
    }

    /**
     * 产品列表
     * @return
     */
    @RequestMapping("/comtype")
    public String comtype() {
        return "comtype";
    }
}
