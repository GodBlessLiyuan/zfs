package com.rpa.web.controller;

import com.rpa.web.common.Constant;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.web.dto.VipCommodityDTO;
import com.rpa.web.service.IVipCommodityService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 11:49
 * @description: 商品列表
 * @version: 1.0
 */
@RequestMapping("vipcommodity")
@RestController
public class VipCommodityController {

    @Resource
    private IVipCommodityService service;

    @RequestMapping("query")
    public DTPageInfo<VipCommodityDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                             @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                             @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "username") String username,
                                             @RequestParam(value = "comTypeId") int comTypeId,
                                             @RequestParam(value = "channelId") int channelId) {

        Map<String, Object> reqData = new HashMap<>(3);
        reqData.put("username", username);
        reqData.put("comTypeId", comTypeId);
        reqData.put("channelId", channelId);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("queryById")
    public VipCommodityDTO queryById(@RequestParam(value = "cmdyId") int cmdyId) {
        return service.queryById(cmdyId);
    }

    @RequestMapping("insert")
    public ResultVO insert(@RequestParam(value = "channelId") int channelId,
                           @RequestParam(value = "comTypeId") int comTypeId,
                           @RequestParam(value = "comName") String comName,
                           @RequestParam(value = "description") String description,
                           @RequestParam(value = "price") String price,
                           @RequestParam(value = "showDiscount") String showDiscount,
                           @RequestParam(value = "discount") float discount, HttpServletRequest req) {

        // 从Session里获取管理员Id
        AdminUserDTO admin = (AdminUserDTO) req.getSession().getAttribute(Constant.ADMIN_USER);
        if (admin == null) {
            return new ResultVO(1001);
        }

        return service.insert(channelId, comTypeId, comName, description, price, showDiscount,
                discount, admin.getaId());

    }

    @RequestMapping("update")
    public int update(@RequestParam(value = "cmdyId") int cmdyId,
                      @RequestParam(value = "comName") String comName,
                      @RequestParam(value = "description") String description,
                      @RequestParam(value = "price") String price,
                      @RequestParam(value = "showDiscount") String showDiscount,
                      @RequestParam(value = "discount") float discount) {

        return service.update(cmdyId, comName, description, price, showDiscount, discount);
    }

    @RequestMapping("updateStatus")
    public int updateStatus(@RequestParam(value = "cmdyId") int cmdyId,
                            @RequestParam(value = "status") byte status) {
        return service.updateStatus(cmdyId, status);
    }

    @RequestMapping("updateIsTop")
    public int updateIsTop(@RequestParam(value = "cmdyId") int cmdyId,
                           @RequestParam(value = "isTop") byte isTop) {
        return service.updateIsTop(cmdyId, isTop);
    }
}
