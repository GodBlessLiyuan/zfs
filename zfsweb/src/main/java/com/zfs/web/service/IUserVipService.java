package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;

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
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    ResultVO query(int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 查询用户会用详细信息
     * @param userId
     * @return
     */
    ResultVO queryDetails(long userId);
}
