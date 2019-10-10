package com.rpa.web.controller;

import com.rpa.web.dto.FeedbackDTO;
import com.rpa.web.service.FeedbackService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dangyi
 * @date: Created in 9:06 2019/10/10
 * @version: 1.0.0
 * @description:
 */
@RestController
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param userId
     * @param contact
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<FeedbackDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "startTime", required = false) String startTime,
                                         @RequestParam(value = "endTime", required = false) String endTime,
                                         @RequestParam(value = "userId", required = false) String userId,
                                         @RequestParam(value = "contact", required = false) String contact
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<FeedbackDTO> dTPageInfo = feedbackService.query(draw, pageNum, pageSize, startTime, endTime, userId, contact);
        return dTPageInfo;
    }
}