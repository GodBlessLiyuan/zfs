package com.rpa.server.controller;

import com.auth0.jwt.JWT;
import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.OrderDTO;
import com.rpa.server.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        String token = req.getHeader("token");
        if (token == null) {
            return new ResultVO(2000);
        }
        List<String> audience = JWT.decode(token).getAudience();
        if (!dto.getUd().toString().equals(audience.get(0)) || !dto.getId().toString().equals(audience.get(1))
                || !dto.getUdd().toString().equals(audience.get(2))) {
            return new ResultVO(2000);
        }

        return service.getOrders(dto);
    }
}
