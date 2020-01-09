package com.rpa.server.controller;

import com.rpa.common.dto.VerifyDTO;
import com.rpa.common.vo.ResultVO;
import com.rpa.server.service.IPhoneModelService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2020/1/9 14:47
 * @description: 手机型号
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class PhoneModelController {
    @Autowired
    private IPhoneModelService service;

    @PostMapping("phonemodel")
    public ResultVO checkPlugin(@RequestBody VerifyDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return service.phoneModel();
    }
}
