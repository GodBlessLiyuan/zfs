package com.rpa.web.service;

import com.rpa.web.vo.UserVipVO;
import com.rpa.web.dto.UserVipDetailsDTO;
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
    DTPageInfo<UserVipVO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 分页查询用户会用详细信息
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    DTPageInfo<UserVipDetailsDTO> queryDetails(int draw, int pageNum, int pageSize, int userId);
}
