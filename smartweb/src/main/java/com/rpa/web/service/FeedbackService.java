package com.rpa.web.service;

import com.rpa.web.dto.FeedbackDTO;
import com.rpa.web.utils.DTPageInfo;

/**
 * @author: dangyi
 * @date: Created in 9:16 2019/10/10
 * @version: 1.0.0
 * @description:
 */
public interface FeedbackService {
    DTPageInfo<FeedbackDTO> query(int draw, int pageNum, int pageSize, String startTime, String endTime, String userId, String contact);
}
