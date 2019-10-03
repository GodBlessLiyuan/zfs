package com.rpa.web.service;

import com.rpa.web.dto.NewUserRecordDTO;
import com.rpa.web.utils.DTPageInfo;

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
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param reqData
     * @return
     */
    DTPageInfo<NewUserRecordDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);
}
