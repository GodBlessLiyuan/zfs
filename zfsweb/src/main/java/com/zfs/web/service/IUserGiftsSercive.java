package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/3 16:53
 * @description: 新用户送会员
 * @version: 1.0
 */
public interface IUserGiftsSercive {
    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    ResultVO query(Integer pageNum, Integer pageSize, Map<String, Object> reqData);

    /**
     * 插入
     *
     * @param comTypeId
     * @param aId
     * @return
     */
    ResultVO insert(int comTypeId, int aId);

    /**
     * 跟新状态
     *
     * @param nugId
     * @param status
     * @return
     */
    ResultVO updateStatus(int nugId, byte status);

    /**
     * 删除
     *
     * @param nugId
     * @return
     */
    ResultVO delete(int nugId);
}
