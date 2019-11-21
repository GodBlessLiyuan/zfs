package com.rpa.datav.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/11/21 11:31
 * @description: 数据大脑
 * @version: 1.0
 */
@RestController
public class DatevController {

    @GetMapping("xxx")
    public String hello() {
        return null;
    }
}
