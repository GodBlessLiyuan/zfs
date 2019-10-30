package com.rpa.front.controller;

import com.rpa.front.common.ResultVO;
import com.rpa.front.dto.DetermineDTO;
import com.rpa.front.dto.IncomeDTO;
import com.rpa.front.service.IIncomeService;
import com.rpa.front.utils.VerifyUtil;
import com.rpa.front.vo.IncomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 18:48
 * @description: 爱收益页面
 * @version: 1.0
 */
@RequestMapping("v1.0")
@Controller
public class PageController {
    @Autowired
    private IIncomeService service;

    @PostMapping("income")
    public String income(@RequestBody IncomeDTO dto, ModelMap map, HttpServletRequest req) {
        if (null == req.getHeader("token") || "".equals(req.getHeader("token"))) {
            map.put("res", new ResultVO<>(1000, new IncomeVO(0L,0,0,0L)));
            return "income_index";
        }

        if (!VerifyUtil.checkToken(dto, req)) {
            return null;
        }
        ResultVO vo = service.query(dto);
        map.put("res", vo);

        req.getSession().setAttribute("loginInfo", dto);

        return "income_index";
    }

    @PostMapping("withdraw")
    public String withdraw(ModelMap map, HttpServletRequest req) {
        IncomeDTO loginInfo = (IncomeDTO) req.getSession().getAttribute("loginInfo");

        ResultVO vo = service.query(loginInfo);
        map.put("res", vo);

        return "withdraw";
    }

    @PostMapping("records")
    public String records(ModelMap map, HttpServletRequest req) {
        IncomeDTO loginInfo = (IncomeDTO) req.getSession().getAttribute("loginInfo");

        ResultVO vo = service.queryRecords(loginInfo);
        map.put("res", vo);

        return "tx_record_index";
    }

    @PostMapping("details")
    public String details(ModelMap map, HttpServletRequest req) {
        IncomeDTO loginInfo = (IncomeDTO) req.getSession().getAttribute("loginInfo");

        ResultVO vo = service.queryDetails(loginInfo);
        map.put("res", vo);

        return "invitation_details_index";
    }
}

