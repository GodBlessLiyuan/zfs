package com.zfs.web.service;

import com.zfs.common.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/3 18:01
 * @description: 新用户赠送记录
 * @version: 1.0
 */
public interface INewUserRecordService {

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    ResultVO query(Integer pageNum, Integer pageSize, Map<String, Object> reqData);

    ResultVO export(Map<String, Object> reqData, HttpServletResponse response);
}
