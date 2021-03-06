package com.zfs.web.controller;

import com.zfs.web.vo.InviteDetailVO;
import com.zfs.web.vo.InviteUserVO;
import com.zfs.web.vo.RevenueUserVO;
import com.zfs.web.service.RevenueUserService;
import com.zfs.web.utils.DTPageInfo;
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
     * 查询：查询邀请人的收益信息
     * @param draw
     * @param start
     * @param length
     * @param phone
     * @param orderby
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<RevenueUserVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                           @RequestParam(value = "start", defaultValue = "1") int start,
                                           @RequestParam(value = "length", defaultValue = "10") int length,
                                           @RequestParam(value = "phone", required = false) String phone,
                                           @RequestParam(value = "orderby", required = false) int orderby
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<RevenueUserVO> dTPageInfo = revenueUserService.query(draw, start, length, phone, orderby);
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
    public DTPageInfo<InviteUserVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                          @RequestParam(value = "start", defaultValue = "1") int start,
                                          @RequestParam(value = "length", defaultValue = "10") int length,
                                          @RequestParam(value = "userId") Integer userId,
                                          @RequestParam(value = "invitePhone", required = false) String invitePhone
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<InviteUserVO> dTPageInfo = revenueUserService.queryInviteduser(draw, start, length, userId, invitePhone);
        return dTPageInfo;
    }


    /**
     * 查询：根据被邀请人的userId，查询其购买商品的详细信息
     * @param draw
     * @param start
     * @param length
     * @param userId
     * @param viptypeId
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/query/inviteduser/detail")
    public DTPageInfo<InviteDetailVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                            @RequestParam(value = "start", defaultValue = "1") int start,
                                            @RequestParam(value = "length", defaultValue = "10") int length,
                                            @RequestParam(value = "userId") Integer userId,
                                            @RequestParam(value = "viptypeId", required = false) Integer viptypeId,
                                            @RequestParam(value = "startTime", required = false) String startTime,
                                            @RequestParam(value = "endTime", required = false) String endTime
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<InviteDetailVO> dTPageInfo = revenueUserService.queryInviteduserDetail(draw, start, length, userId, viptypeId, startTime, endTime);
        return dTPageInfo;
    }


}
