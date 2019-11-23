package com.rpa.datav.controller;

import com.rpa.datav.constant.CommonConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/11/21 11:31
 * @description: 数据大脑
 * @version: 1.0
 */
@RequestMapping("datav")
@RestController
public class DatavController {

    @GetMapping("usernumber")
    public String userNumber(@RequestParam Integer id) {
        if (id == 1) {
            return CommonConstant.userNumber1;
        }else if (id == 2) {
            return CommonConstant.userNumber2;
        }else if (id == 6) {
            return CommonConstant.userNumber1;
        }else if (id == 7) {
            return CommonConstant.userNumber2;
        }
        return null;
    }

    @GetMapping("userfrequency")
    public String userFrequency(@RequestParam Integer id) {
        if (id == 6) {
            return CommonConstant.userFrequency1;
        }else if (id == 7) {
            return CommonConstant.userFrequency2;
        }else if (id == 1) {
            return CommonConstant.userFrequency1;
        }else if (id == 2) {
            return CommonConstant.userFrequency2;
        }
        return null;
    }

}
