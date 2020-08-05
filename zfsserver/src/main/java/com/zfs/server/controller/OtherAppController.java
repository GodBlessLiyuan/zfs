package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.OtherAppDTO;
import com.zfs.server.service.IOtherAppService;
import com.zfs.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 11:13
 * @description: 其他应用接口
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class OtherAppController {
    @Autowired
    private IOtherAppService service;

    @PostMapping("otherapp")
    public ResultVO otherApp(@RequestBody OtherAppDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return service.query(dto);
    }
}
