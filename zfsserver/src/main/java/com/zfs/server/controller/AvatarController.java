package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.AvatarDTO;
import com.zfs.server.service.IAvatarService;
import com.zfs.server.utils.VerifyUtil;
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
    private IAvatarService service;

    @PostMapping("checkava")
    public ResultVO check(@RequestBody AvatarDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return ResultVO.validDevice();
        }

        return service.check(dto);
    }
}
