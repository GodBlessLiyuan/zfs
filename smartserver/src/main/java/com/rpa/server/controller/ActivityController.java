package com.rpa.server.controller;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.ActivityDTO;
import com.rpa.server.service.IActivityService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 9:12
 * @description: 活动
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class ActivityController {
    @Autowired
    private IActivityService service;

    @PostMapping("checkactivity")
    public ResultVO check(@RequestBody ActivityDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkToken(dto, req)) {
            return new ResultVO(2000);
        }

        return service.check(dto);
    }

    @PostMapping("activate")
    public ResultVO activate(@RequestBody ActivityDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkToken(dto, req)) {
            return new ResultVO(2000);
        }

        return service.activate(dto);
    }
}
