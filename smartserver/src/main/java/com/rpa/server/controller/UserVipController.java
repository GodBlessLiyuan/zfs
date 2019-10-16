package com.rpa.server.controller;

import com.auth0.jwt.JWT;
import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.UserVipDTO;
import com.rpa.server.service.IUserVipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 18:27
 * @description: 用户会员
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class UserVipController {
    @Autowired
    private IUserVipService userVipService;

    @PostMapping("validate")
    public ResultVO validate(@RequestBody UserVipDTO dto, HttpServletRequest req) {
        String token = req.getHeader("token");
        if (token == null) {
            return new ResultVO(2000);
        }
        List<String> audience = JWT.decode(token).getAudience();
        if (!dto.getUd().toString().equals(audience.get(0)) || !dto.getId().toString().equals(audience.get(1))
                || !dto.getUdd().toString().equals(audience.get(2))) {
            return new ResultVO(2000);
        }

        return userVipService.validate(dto);
    }
}
