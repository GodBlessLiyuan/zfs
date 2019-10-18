package com.rpa.server.service;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.FeedbackDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 10:59
 * @description: 用户反馈
 * @version: 1.0
 */
public interface IFeedbackService {
    /**
     * 插入
     * @param dto
     * @return
     */
    ResultVO insert(FeedbackDTO dto);
}
