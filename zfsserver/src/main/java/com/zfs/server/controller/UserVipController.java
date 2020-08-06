package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.UserVipDTO;
import com.zfs.server.service.IUserVipService;
import com.zfs.server.utils.VerifyUtil;
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
            return ResultVO.tokenInvalid();
        }
        return userVipService.validate(dto);
    }
}
