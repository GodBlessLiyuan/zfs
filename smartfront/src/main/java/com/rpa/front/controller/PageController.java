package com.rpa.front.controller;

import com.rpa.front.common.ErrorCode;
import com.rpa.front.common.ResultVO;
import com.rpa.front.constant.IncomeConstant;
import com.rpa.front.dto.IncomeDTO;
import com.rpa.front.dto.UserActivityDTO;
import com.rpa.front.service.IIncomeService;
import com.rpa.front.service.UserActivityService;
import com.rpa.front.utils.VerifyUtil;
import com.rpa.front.vo.IncomeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserActivityService userActivityService;

    @PostMapping("income")
    public String income(@RequestBody IncomeDTO dto, ModelMap map, HttpServletRequest req) {
        if (null == req.getHeader(IncomeConstant.INCOME_TOKEN) || "".equals(req.getHeader(IncomeConstant.INCOME_TOKEN))) {
            map.put(IncomeConstant.INCOME_RESULT, new ResultVO<>(ErrorCode.USER_NOT_LOGIN, new IncomeVO()));
            return "income_index";
        }

        if (!VerifyUtil.checkToken(dto, req)) {
            return null;
        }
        ResultVO vo = service.query(dto);
        map.put(IncomeConstant.INCOME_RESULT, vo);

        req.getSession().setAttribute(IncomeConstant.INCOME_SESSION, dto);

        return "income_index";
    }

    @GetMapping("withdraw")
    public String withdraw(ModelMap map, HttpServletRequest req) {
        IncomeDTO loginInfo = (IncomeDTO) req.getSession().getAttribute(IncomeConstant.INCOME_SESSION);
        if (null == loginInfo) {
            map.put(IncomeConstant.INCOME_RESULT, new ResultVO<>(ErrorCode.SESSION_TIMEOUT));
            return "withdraw";
        }

        ResultVO vo = service.query(loginInfo);
        map.put("res", vo);

        return "withdraw";
    }

    @GetMapping("records")
    public String records(ModelMap map, HttpServletRequest req) {
        IncomeDTO loginInfo = (IncomeDTO) req.getSession().getAttribute(IncomeConstant.INCOME_SESSION);
        if (null == loginInfo) {
            map.put(IncomeConstant.INCOME_RESULT, new ResultVO<>(ErrorCode.SESSION_TIMEOUT));
            return "tx_record_index";
        }
        ResultVO vo = service.queryRecords(loginInfo);
        map.put("res", vo);

        return "tx_record_index";
    }

    @GetMapping("details")
    public String details(ModelMap map, HttpServletRequest req) {
        IncomeDTO loginInfo = (IncomeDTO) req.getSession().getAttribute(IncomeConstant.INCOME_SESSION);
        if (null == loginInfo) {
            map.put(IncomeConstant.INCOME_RESULT, new ResultVO<>(ErrorCode.SESSION_TIMEOUT));
            return "invitation_details_index";
        }

        ResultVO vo = service.queryDetails(loginInfo);
        map.put(IncomeConstant.INCOME_RESULT, vo);

        return "invitation_details_index";
    }

    @GetMapping(value = "share/{shareCode}")
    public String shareClick(@PathVariable String shareCode, ModelMap map) {
        ResultVO vo = new ResultVO<>(1000, shareCode);
        map.put("res", vo);
        return "promotion_index";
    }


    /**
     * @author: dangyi
     * @param dto
     * @param map
     * @param req
     * @description：免费领取会员：根据两个ID查询状态 个人中心-免费领会员-webview的url
     */
    @PostMapping("freemember")
    public String freemember(@RequestBody UserActivityDTO dto, ModelMap map, HttpServletRequest req) {
        if (null == req.getHeader(IncomeConstant.INCOME_TOKEN) || "".equals(req.getHeader(IncomeConstant.INCOME_TOKEN))) {
            map.put(IncomeConstant.INCOME_RESULT, new ResultVO<>(ErrorCode.USER_NOT_LOGIN, new IncomeVO()));
            return "freemember_index";
        }

        if (!VerifyUtil.checkToken(dto, req)) {
            return null;
        }

        Integer status = this.userActivityService.query(dto);
        map.put("status", status);
        if (null == status) {
            map.put("msg", "期待您的参与！");
        } else if (status == 1) {
            map.put("msg", "参与成功，正在审核中！");
        }else if (status == 10 || status == 30) {
            map.put("msg", "参与成功，奖励已发放！");
        }else if (status == 20) {
            map.put("msg", "您上传的图片不符合标准，请重新上传！");
        }

        return "freemember_index";
    }
}

