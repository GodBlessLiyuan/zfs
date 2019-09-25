package com.rpa.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 10:07
 * @description: TODO
 * @version: 1.0
 */
@Controller
public class UserController {

    @RequestMapping("/userlist")
    public String userList() {
        return "userlist";
    }
}

