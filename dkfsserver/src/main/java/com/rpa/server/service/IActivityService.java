package com.rpa.server.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.ActivityDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 9:13
 * @description: 活动
 * @version: 1.0
 */
public interface IActivityService {
    /**
     * 查看是否存在好评活动接口
     *
     * @param dto
     * @return
     */
    ResultVO check(ActivityDTO dto);

    /**
     * 活动激活接口
     *
     * @param dto
     * @return
     */
    ResultVO activate(ActivityDTO dto);
}
