package com.rpa.server.controller;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.FunctionvideoDTO;
import com.rpa.server.service.FunctionvideoService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dangyi
 * @date: Created in 14:30 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@RequestMapping("v1.0")
@RestController
public class FunctionvideoController {

    @Autowired
    private FunctionvideoService functionvideoService;

    @PostMapping("functionvideo")
    public ResultVO query(@RequestBody FunctionvideoDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return this.functionvideoService.query(dto);
    }
}
