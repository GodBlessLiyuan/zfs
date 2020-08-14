package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
     *
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param phone
     * @param contact
     * @return
     */
    @PostMapping("query")
    public ResultVO query(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                          @RequestParam(value = "startTime", required = false) String startTime,
                          @RequestParam(value = "endTime", required = false) String endTime,
                          @RequestParam(value = "phone", required = false) String phone,
                          @RequestParam(value = "contact", required = false) String contact
    ) {

        // 调用业务层，返回页面结果
        return feedbackService.query(pageNum, pageSize, startTime, endTime, phone, contact);

    }
    @RequestMapping("export")
    public ResultVO export(HttpServletResponse response,
          @RequestParam(value = "startTime", required = false) String startTime,
          @RequestParam(value = "endTime", required = false) String endTime,
          @RequestParam(value = "phone", required = false) String phone,
          @RequestParam(value = "contact", required = false) String contact
    ) {
        // 调用业务层，返回页面结果
        return feedbackService.export(response,startTime, endTime, phone, contact);
    }

}
