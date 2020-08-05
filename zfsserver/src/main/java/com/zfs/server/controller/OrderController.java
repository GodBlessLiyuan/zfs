package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.OrderDTO;
import com.zfs.server.service.IOrderService;
import com.zfs.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/10/24 16:39
 * @description: 订单
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class OrderController {

    @Autowired
    private IOrderService service;

    @PostMapping("order")
    public ResultVO order(@RequestBody OrderDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkToken(dto, req)) {
            return ResultVO.paramsError();
        }
        if(!VerifyUtil.expire(dto,req)){
            return ResultVO.logOut();
        }
        return service.getOrders(dto,0);
    }
}
