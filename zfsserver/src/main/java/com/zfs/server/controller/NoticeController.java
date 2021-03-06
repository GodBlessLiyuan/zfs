package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.NoticeDTO;
import com.zfs.server.service.INoticeService;
import com.zfs.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public ResultVO notice(@RequestBody NoticeDTO dto, HttpServletRequest request){
        if (!VerifyUtil.checkDeviceId(dto)) {
            return ResultVO.varifyDevice();
        }
        return service.queryNotice(dto);
    }
}
