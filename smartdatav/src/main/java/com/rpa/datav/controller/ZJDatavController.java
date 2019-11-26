package com.rpa.datav.controller;

import com.rpa.datav.constant.ZJCommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("zjdatav")
@RestController
public class ZJDatavController {

    private final static Logger logger = LoggerFactory.getLogger(DatavController.class);

    @GetMapping("zjinfonumber")
    public String userNumber(@RequestParam String id) {
        try {
            int num = Integer.valueOf(id);
            if (num == 1) {
                return ZJCommonConstant.zjuserNumber1;
            } else if (num == 2) {
                return ZJCommonConstant.zjuvipNumber2;
            } else if (num == 3) {
                return ZJCommonConstant.zjhuoyueNumber3;
            } else if (num == 4) {
                return ZJCommonConstant.zjtotalhourNumber4;
            } else if (num == 5) {
                return ZJCommonConstant.zjpinciNumber5;
            }
        } catch (Exception e) {
            return ZJCommonConstant.zjuserNumber1;
        }
        return null;
    }


}
