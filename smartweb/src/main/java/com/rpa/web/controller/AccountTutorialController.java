package com.rpa.web.controller;

import com.rpa.web.service.AccountTutorialService;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.rpa.common.constant.Constant.TUTORIAL_URL;


/**
 * @author: dangyi
 * @date: Created in 16:32 2019/10/10
 * @version: 1.0.0
 * @description: 养号教程
 */
@RestController
@RequestMapping("tutorial")
public class AccountTutorialController {

    @Autowired
    private AccountTutorialService accountTutorialService;

    /**
     * 查询
     * @return
     */
    @GetMapping("query")
    public ResultVO query( ) {
        return this.accountTutorialService.query(TUTORIAL_URL);
    }

    /**
     * 插入或修改
     * @param url
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(@RequestParam(value = "url") String url) {
        return this.accountTutorialService.insert(url);
    }
}
