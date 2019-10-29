package com.rpa.front.controller;

import com.rpa.front.dto.IncomeDTO;
import com.rpa.front.service.IIncomeService;
import com.rpa.front.utils.VerifyUtil;
import com.rpa.front.vo.DetailsVO;
import com.rpa.front.vo.IncomeVO;
import com.rpa.front.vo.RecordsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        if (!VerifyUtil.checkToken(dto, req)) {
            return null;
        }
        IncomeVO vo = service.query(dto);
        map.put("res", vo);

        req.getSession().setAttribute("userId", dto.getUd());

        return "income_index";
    }

    @PostMapping("withdraw")
    public String withdraw(ModelMap map, HttpServletRequest req) {
        long userId = (long) req.getSession().getAttribute("userId");

        IncomeDTO dto = new IncomeDTO();
        dto.setUd(userId);

        IncomeVO vo = service.query(dto);
        map.put("res", vo);

        return "income_index";
    }

    @PostMapping("records")
    public String records(ModelMap map, HttpServletRequest req){
        long userId = (long) req.getSession().getAttribute("userId");

        List<RecordsVO> vos = service.queryRecords(userId);
        map.put("res", vos);

        return "income_index";
    }

    @PostMapping("invation_details")
    public String details(ModelMap map, HttpServletRequest req) {
        long userId = (long) req.getSession().getAttribute("userId");

        DetailsVO vo = service.queryDetails(userId);
        map.put("res", vo);

        return "invation_details_index";
    }
}

