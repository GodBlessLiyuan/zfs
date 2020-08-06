package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;

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
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    ResultVO query(int pageNum, int pageSize, Map<String, Object> reqData);
}
