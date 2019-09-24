package com.rpa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return "index";
    }


    @RequestMapping("/seconddddd")
    @ResponseBody
    public String get() {
        return "haha";
    }
}
