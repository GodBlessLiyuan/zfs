package com.rpa.server.controller;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.BatchInfoDTO;
import com.rpa.server.service.IBatchInfoService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 9:26
 * @description: 卡密表
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class BatchInfoController {
    @Autowired
    private IBatchInfoService service;

    @PostMapping("keyactivate")
    public ResultVO keyActivate(@RequestBody BatchInfoDTO dto, HttpServletRequest req) {
        if (!VerifyUtil.checkToken(dto, req)) {
            return new ResultVO(2000);
        }

        return service.activate(dto);
    }

}
