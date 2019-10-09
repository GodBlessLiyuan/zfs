package com.rpa.web.service;

import com.rpa.web.dto.UserVipDTO;
import com.rpa.web.utils.DTPageInfo;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 14:29
 * @description: 用户会员数据
 * @version: 1.0
 */
public interface IUserVipService {

    /**
     * 分页查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<UserVipDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);
}
