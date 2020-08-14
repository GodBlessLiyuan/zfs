package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: dangyi
 * @date: Created in 9:16 2019/10/10
 * @version: 1.0.0
 * @description:
 */
public interface FeedbackService {
    ResultVO query(Integer start, Integer length, String startTime, String endTime, String phone, String contact);

    ResultVO export(HttpServletResponse response, String startTime, String endTime, String phone, String contact);
}
