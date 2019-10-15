package com.rpa.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.rpa.web.common.Constant.ADMIN_USER;

@Controller
public class MainController {
    @GetMapping("main")
    public String main(HttpSession session){
        if(session==null || session.getAttribute(ADMIN_USER) == null){
            return "redirect:/";
        }
        return "main";
    }

    @RequestMapping("/login")
    public String index(Map<String, Object> result){
        return "index";
    }


}
