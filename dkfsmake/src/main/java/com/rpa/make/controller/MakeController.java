package com.rpa.make.controller;

import com.rpa.common.utils.VerifyUtil;
import com.rpa.common.vo.ResultVO;
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

    @PostMapping("make")
    public ResultVO make(@RequestBody AvatarMakeDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return service.make(dto);
    }
}
