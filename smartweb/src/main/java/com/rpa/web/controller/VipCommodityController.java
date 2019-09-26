package com.rpa.web.controller;

import com.rpa.web.dto.VipCommodityDTO;
import com.rpa.web.pojo.AdminUserPO;
import com.rpa.web.service.IVipCommodityService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 11:49
 * @description: 商品列表
 * @version: 1.0
 */
@RestController
public class VipCommodityController {

    @Resource
    private IVipCommodityService service;

    @RequestMapping("/vipcommodity/insert")
    public void insert(@RequestParam(value = "channelName") String channelName,
                       @RequestParam(value = "comTypeName") String comTypeName,
                       @RequestParam(value = "comName") String comName,
                       @RequestParam(value = "description") String description,
                       @RequestParam(value = "price") int price,
                       @RequestParam(value = "showDiscount") String showDiscount,
                       @RequestParam(value = "discount") float discount, HttpSession session) {

        // 从Session里获取管理员Id
        AdminUserPO loginUser = (AdminUserPO) session.getAttribute("loginUser");

        service.insert(channelName, comTypeName, comName, description, price, showDiscount, discount, 1);

    }

    @RequestMapping("/vipcommodity/query")
    public DTPageInfo<VipCommodityDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                             @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                             @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "username") String username,
                                             @RequestParam(value = "comname") String comname,
                                             @RequestParam(value = "channelname") String channelname) {

        Map<String, Object> reqData = new HashMap<>(3);
        reqData.put("username", username);
        reqData.put("comname", comname);
        reqData.put("channelname", channelname);

        DTPageInfo<VipCommodityDTO> data = service.query(draw, pageNum, pageSize, reqData);
        return data;
    }
}
