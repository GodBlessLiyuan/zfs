package com.zfs.web.controller;

import com.zfs.web.vo.WithdrawUserVO;
import com.zfs.web.service.WithdrawUserService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 8:47 2019/10/12
 * @version: 1.0.0
 * @description：提现
 */
@RestController
@RequestMapping("withdraw")
public class WithdrawUserController {

    @Autowired
    private WithdrawUserService withdrawUserService;

    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param phone
     * @param status
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<WithdrawUserVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                            @RequestParam(value = "start", defaultValue = "1") int start,
                                            @RequestParam(value = "length", defaultValue = "10") int length,
                                            @RequestParam(value = "phone", required = false) String phone,
                                            @RequestParam(value = "status", required = false) Byte status
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<WithdrawUserVO> dTPageInfo = withdrawUserService.query(draw, start, length, phone, status);
        return dTPageInfo;
    }

    /**
     * 修改
     * @param withdrawId
     * @param status
     * @param httpSession
     * @return
     */
    @PostMapping("update")
    public ResultVO update(@RequestParam(value = "withdrawId") Integer withdrawId,
                           @RequestParam(value = "status") Byte status,
                           HttpSession httpSession) {
        return this.withdrawUserService.update(withdrawId, status, httpSession);
    }
}
