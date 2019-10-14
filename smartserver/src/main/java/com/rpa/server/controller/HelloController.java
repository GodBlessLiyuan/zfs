package com.rpa.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/14 8:46
 * @description: TODO
 * @version: 1.0
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello word.";
    }
}
