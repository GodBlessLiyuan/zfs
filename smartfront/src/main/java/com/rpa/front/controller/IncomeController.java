package com.rpa.front.controller;

import com.rpa.front.common.ResultVO;
import com.rpa.front.dto.DetermineDTO;
import com.rpa.front.dto.IncomeDTO;
import com.rpa.front.service.IIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/10/30 16:43
 * @description: 爱收益
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class IncomeController {
    @Autowired
    private IIncomeService service;

    @PostMapping("determine")
    public ResultVO determine(@RequestBody DetermineDTO dto, HttpServletRequest req) {
        IncomeDTO loginInfo = (IncomeDTO) req.getSession().getAttribute("loginInfo");

        return service.determine(dto, loginInfo);
    }

}
