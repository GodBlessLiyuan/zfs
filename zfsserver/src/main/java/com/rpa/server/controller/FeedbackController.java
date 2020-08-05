package com.rpa.server.controller;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.FeedbackDTO;
import com.rpa.server.service.IFeedbackService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 10:57
 * @description: 用户反馈
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class FeedbackController {
    @Autowired
    private IFeedbackService service;

    @PostMapping("feedback")
    public ResultVO feedback(@RequestBody FeedbackDTO dto) {
        if (!VerifyUtil.checkDeviceId(dto)) {
            return ResultVO.paramsError();
        }

        return service.insert(dto);
    }
}
