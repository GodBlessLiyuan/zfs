package com.rpa.server.controller;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.NoticeDTO;
import com.rpa.server.service.INoticeService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:35
 * @description: 通知消息
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class NoticeController {
    @Autowired
    private INoticeService service;

    @PostMapping("notice")
    public ResultVO notice(@RequestBody NoticeDTO dto){
        if (!VerifyUtil.checkDeviceId(dto)) {
            return new ResultVO(2000);
        }

        return service.queryNotice(dto);
    }
}
