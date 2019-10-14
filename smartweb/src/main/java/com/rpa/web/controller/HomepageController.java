package com.rpa.web.controller;

import com.rpa.web.service.HomepageService;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dangyi
 * @date: Created in 11:18 2019/10/14
 * @version: 1.0.0
 * @description:
 */
@RestController
@RequestMapping("homepage")
public class HomepageController {

    @Autowired
    private HomepageService homepageService;

    /**
     * 主页查询
     * @return
     */
    @GetMapping("query")
    public ResultVO query() {
        return this.homepageService.query();
    }
}
