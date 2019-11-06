package com.rpa.web.controller;

import com.rpa.web.dto.InviteDetailDTO;
import com.rpa.web.dto.InviteUserDTO;
import com.rpa.web.dto.RevenueUserDTO;
import com.rpa.web.service.RevenueUserService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
     * 查询：查询邀请人的收益信息
     * @param draw
     * @param start
     * @param length
     * @param phone
     * @param orderby
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<RevenueUserDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                            @RequestParam(value = "start", defaultValue = "1") int start,
                                            @RequestParam(value = "length", defaultValue = "10") int length,
                                            @RequestParam(value = "phone", required = false) String phone,
                                            @RequestParam(value = "orderby", required = false) int orderby
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<RevenueUserDTO> dTPageInfo = revenueUserService.query(draw, start, length, phone, orderby);
        return dTPageInfo;
    }


    /**
     * 查询：根据邀请人的userId，查询该邀请人名下的所有被邀请人大概信息
     * @param draw
     * @param start
     * @param length
     * @param userId
     * @param invitePhone
     * @return
     */
    @GetMapping("/query/inviteduser")
    public DTPageInfo<InviteUserDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                           @RequestParam(value = "start", defaultValue = "1") int start,
                                           @RequestParam(value = "length", defaultValue = "10") int length,
                                           @RequestParam(value = "userId") Integer userId,
                                           @RequestParam(value = "invitePhone", required = false) String invitePhone
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<InviteUserDTO> dTPageInfo = revenueUserService.queryInviteduser(draw, start, length, userId, invitePhone);
        return dTPageInfo;
    }


    /**
     * 查询：根据被邀请人的userId，查询其购买商品的详细信息
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param userId
     * @param viptypeId
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/query/inviteduser/detail")
    public DTPageInfo<InviteDetailDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                             @RequestParam(value = "start", defaultValue = "1") int start,
                                             @RequestParam(value = "length", defaultValue = "10") int length,
                                             @RequestParam(value = "userId") Integer userId,
                                             @RequestParam(value = "viptypeId", required = false) Integer viptypeId,
                                             @RequestParam(value = "startTime", required = false) Date startTime,
                                             @RequestParam(value = "endTime", required = false) Date endTime
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<InviteDetailDTO> dTPageInfo = revenueUserService.queryInviteduserDetail(draw, start, length, userId, viptypeId, startTime, endTime);
        return dTPageInfo;
    }


}
