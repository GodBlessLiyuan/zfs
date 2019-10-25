package com.rpa.front.controller;

import com.rpa.front.dto.IncomeDTO;
import com.rpa.front.service.IIncomeService;
import com.rpa.front.utils.VerifyUtil;
import com.rpa.front.vo.IncomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 18:48
 * @description: 爱收益页面
 * @version: 1.0
 */
@Controller
public class PageController {
    @Autowired
    private IIncomeService service;

    @PostMapping("income")
    public String income(@RequestBody IncomeDTO dto, ModelMap map, HttpServletRequest req) {
        if (!VerifyUtil.checkToken(dto, req)) {
            return null;
        }
        IncomeVO vo = service.query(dto);
        map.put("res", vo);

        return "income_index";
    }
}
