package com.rpa.make.controller;

import com.rpa.common.dto.MakeDTO;
import com.rpa.common.utils.VerifyUtil;
import com.rpa.common.vo.ResultVO;
import com.rpa.make.service.IMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2020/1/11 17:06
 * @description: TODO
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class MakeController {
    @Autowired
    private IMakeService service;
    @Value("${verify.config.salt}")
    private String salt;

    @PostMapping("make")
    public ResultVO make(@RequestBody MakeDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto, salt)) {
            return new ResultVO(2000);
        }

        return service.make(dto);
    }
}
