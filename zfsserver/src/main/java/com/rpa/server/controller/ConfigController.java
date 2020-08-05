package com.rpa.server.controller;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.ConfigDTO;
import com.rpa.server.service.IConfigService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 10:28
 * @description: 获取砖助智能助手的基础信息
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class ConfigController {
    @Autowired
    private IConfigService service;

    @PostMapping("config")
    public ResultVO config(@RequestBody ConfigDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return ResultVO.paramsError();
        }

        return service.queryConfigInfo(dto);
    }
}
