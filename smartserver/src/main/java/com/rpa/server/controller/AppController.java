package com.rpa.server.controller;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.AppDTO;
import com.rpa.server.service.IAppService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 8:51
 * @description: 应用更新
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class AppController {
    @Autowired
    private IAppService appService;

    @PostMapping("check")
    public ResultVO check(@RequestBody AppDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto.getId(), dto.getVerify())) {
            return new ResultVO(2000);
        }

        return appService.check(dto);
    }
}
