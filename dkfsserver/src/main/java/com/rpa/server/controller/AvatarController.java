package com.rpa.server.controller;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.AvatarDTO;
import com.rpa.server.service.IAvatarService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/12/24 14:56
 * @description: 分身更新
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class AvatarController {

    @Autowired
    private IAvatarService avatarService;

    @PostMapping("checkava")
    public ResultVO check(@RequestBody AvatarDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return avatarService.check(dto);
    }

    @PostMapping("make")
    public ResultVO make(@RequestBody AvatarDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return avatarService.make(dto);
    }
}
