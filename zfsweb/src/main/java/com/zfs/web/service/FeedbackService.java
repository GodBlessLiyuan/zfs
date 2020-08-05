package com.zfs.web.service;

import com.zfs.web.vo.FeedbackVO;
import com.zfs.web.utils.DTPageInfo;

/**
 * @author: dangyi
 * @date: Created in 9:16 2019/10/10
 * @version: 1.0.0
 * @description:
 */
public interface FeedbackService {
    DTPageInfo<FeedbackVO> query(int draw, int start, int length, String startTime, String endTime, String userId, String contact);
}
