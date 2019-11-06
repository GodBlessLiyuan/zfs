package com.rpa.front.controller;

import com.rpa.front.common.ErrorCode;
import com.rpa.front.common.ResultVO;
import com.rpa.front.constant.IncomeConstant;
import com.rpa.front.dto.IncomeDTO;
import com.rpa.front.dto.UserActivityDTO;
import com.rpa.front.service.IIncomeService;
import com.rpa.front.service.UserActivityService;
import com.rpa.front.utils.VerifyUtil;
import com.rpa.front.vo.IncomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: CHL
 * @date: Created in 2019/10/25 18:48
 * @description: 基础配置页面
 * @version: 1.0
 */
@RequestMapping("v1.0")
@Controller
public class ConfigController {

    @GetMapping("problem")
    public String problem(@RequestBody IncomeDTO dto, ModelMap map, HttpServletRequest req) {
        return "common_problem";
    }

    @GetMapping("freevip")
    public String freevip(ModelMap map, HttpServletRequest req) {
        return "freemember_index";
    }

    @GetMapping("memberrights")
    public String viprights(ModelMap map, HttpServletRequest req) {
        return "member_rights";
    }

    @GetMapping("protocol")
    public String serviceprotocol(ModelMap map, HttpServletRequest req) {
        return "member_service_protocol";
    }

    @GetMapping("viprights")
    public String freemember(@RequestBody UserActivityDTO dto, ModelMap map, HttpServletRequest req) {
        return "settings_member_service_protocol";
    }
}

