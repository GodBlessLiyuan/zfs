package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.IRegisterUserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @author: liyuan
 * @data 2020-06-08 08:41
 */
@Controller
@RequestMapping("history")
public class RegisterUserHistoryController {
    @Autowired
    private IRegisterUserHistoryService service;
    @ResponseBody
    @RequestMapping("createUserHistory")
    public ResultVO<Integer> createUserHistory(){
        return service.createUserHistory();
    }

}
