package com.rpa.server.controller;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.OrderFeedbackDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/11/1 14:54
 * @description: 微信回调
 * @version: 1.0
 */
@RestController
public class OrderFeedbackController {

    @PostMapping("feedback")
    public ResultVO feedback(@RequestBody OrderFeedbackDTO dto) {
        return null;
    }
}
