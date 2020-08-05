package com.rpa.server.controller;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.UserVipDTO;
import com.rpa.server.service.IUserVipService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
        if(!VerifyUtil.checkToken(dto, req)) {
            return ResultVO.paramsError();
        }
        if(!VerifyUtil.expire(dto,req)){
            return ResultVO.logOut();
        }
        return userVipService.validate(dto);
    }
}
