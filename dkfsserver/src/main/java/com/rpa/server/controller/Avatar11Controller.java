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
 * @Description:制作多开分身时的更新分身类型
 * @author: liyuan
 * @data 2020-06-16 17:51
 */
@RequestMapping("v1.1")
@RestController
public class Avatar11Controller {
    @Autowired
    private IAvatarService service;

    @PostMapping("checkava")
    public ResultVO check(@RequestBody AvatarDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return service.check(dto);
    }
}
