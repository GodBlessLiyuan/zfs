package com.rpa.server.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("v1.0")
@Controller
public class PageController {

    @GetMapping("userAgreement")
    public String userAgreement() {
        return "user_agreement_index";
    }

    @GetMapping("member_right")
    public String memberRight() {
        return "member_right_index";
    }

    @GetMapping("free_membership_index")
    public String freeMemberShip() {
        return "free_membership_index";
    }


}
