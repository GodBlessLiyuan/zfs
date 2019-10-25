package com.rpa.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 18:48
 * @description: TODO
 * @version: 1.0
 */
@Controller
public class PageController {

    @RequestMapping("income")
    public String income() {
        return "income_index";
    }
}
