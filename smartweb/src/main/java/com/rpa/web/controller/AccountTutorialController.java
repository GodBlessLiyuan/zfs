package com.rpa.web.controller;

import com.rpa.web.dto.KeyValueDTO;
import com.rpa.web.service.AccountTutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.rpa.web.common.Constant.TUTORIAL_URL;


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
    public KeyValueDTO query( ) {
        return this.accountTutorialService.query(TUTORIAL_URL);
    }

    /**
     * 插入或修改
     * @param url
     * @return
     */
    @PostMapping("insert")
    public int insert(String url) {
        return this.accountTutorialService.insert(url);
    }
}
