package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.UserActivityDTO;
import com.zfs.server.service.IUserActivityService;
import com.zfs.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/11/5 18:54
 * @description: 用户活动
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class UserActivityController {
    @Autowired
    private IUserActivityService service;

    @PostMapping("uploadpic")
    public ResultVO uploadPic(@RequestBody UserActivityDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkToken(dto, req)) {
            return new ResultVO(2000);
        }
        if(!VerifyUtil.expire(dto,req)){
            return ResultVO.logOut();
        }
        return service.uploadPic(dto);
    }
}
