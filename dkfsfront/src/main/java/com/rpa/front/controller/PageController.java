package com.rpa.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 18:48
 * @description: 多开分身
 * @version: 1.0
 */
@RequestMapping("v1.0")
@Controller
public class PageController {
    private static final Logger logger = LoggerFactory.getLogger(PageController.class);



    @GetMapping("useragreement")
    public String userAgreement() {
        return "user_register_agreement_index";
    }

    @GetMapping("memberright")
    public String memberRight() {
        return "member_right_index";
    }

    @GetMapping("freemembership")
    public String freeMemberShip() {
        return "free_membership_index";
    }

    @GetMapping("settingprotocol")
    public String settingProtocol() {
        return "use_service_protocol_index";
    }

    @GetMapping("memberserviceportocol")
    public String memberProtocol() {
        return "member_service_protocol";
    }



}

