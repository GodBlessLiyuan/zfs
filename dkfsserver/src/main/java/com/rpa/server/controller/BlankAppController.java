package com.rpa.server.controller;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.BlankAppDTO;
import com.rpa.server.service.IBlankAppService;
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
public class BlankAppController {
    @Autowired
    private IBlankAppService service;

    @PostMapping("blankpkgs")
    public ResultVO blankPkgs(@RequestBody BlankAppDTO dto) {
        if(!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return service.getBlankPkgs(dto);
    }
}
