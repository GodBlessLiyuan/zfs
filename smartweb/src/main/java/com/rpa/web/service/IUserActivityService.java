package com.rpa.web.service;

import com.rpa.web.dto.UserActivityDTO;
import com.rpa.web.utils.DTPageInfo;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 11:13
 * @description: 活动赠送记录
 * @version: 1.0
 */
public interface IUserActivityService {

    /**
     * 分页查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<UserActivityDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);
}
