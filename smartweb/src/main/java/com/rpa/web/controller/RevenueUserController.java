package com.rpa.web.controller;

import com.rpa.web.dto.RevenueUserDTO;
import com.rpa.web.service.RevenueUserService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dangyi
 * @date: Created in 10:16 2019/10/12
 * @version: 1.0.0
 * @description:
 */
@RestController
@RequestMapping("revenue")
public class RevenueUserController {

    @Autowired
    private RevenueUserService revenueUserService;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param phone
     * @param orderBy
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<RevenueUserDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                            @RequestParam(value = "phone", required = false) String phone,
                                            @RequestParam(value = "orderBy", required = false) int orderBy
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<RevenueUserDTO> dTPageInfo = revenueUserService.query(draw, pageNum, pageSize, phone, orderBy);
        return dTPageInfo;
    }
}
