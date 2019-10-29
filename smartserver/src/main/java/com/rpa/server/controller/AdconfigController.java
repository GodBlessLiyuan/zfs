package com.rpa.server.controller;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.AdconfigDTO;
import com.rpa.server.service.AdconfigServcie;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dangyi
 * @date: Created in 14:53 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@RequestMapping("v1.0")
@RestController
public class AdconfigController {

    @Autowired
    private AdconfigServcie adconfigServcie;

    @PostMapping("adconfig")
    public ResultVO query(@RequestBody AdconfigDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return this.adconfigServcie.query();
    }
}
