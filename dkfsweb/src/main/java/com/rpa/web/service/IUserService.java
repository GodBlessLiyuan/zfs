package com.rpa.web.service;

import com.rpa.web.vo.UserVO;
import com.rpa.web.utils.DTPageInfo;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 10:00
 * @description: TODO
 * @version: 1.0
 */
public interface IUserService {

    /**
     * 分页查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<UserVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);
}
