package com.rpa.server.controller;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.BlackAppDTO;
import com.rpa.server.service.IBlackAppService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2020/1/14 18:34
 * @description: 应用黑名单
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class BlackAppController {
    @Autowired
    private IBlackAppService service;

    @PostMapping("blackpkgs")
    public ResultVO blankPkgs(@RequestBody BlackAppDTO dto) {
        if(!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return service.getBlankPkgs(dto);
    }
}
