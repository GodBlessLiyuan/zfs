package com.rpa.server.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.ExceptionDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 8:50
 * @description: 上传异常log
 * @version: 1.0
 */
public interface IExceptionService {
    /**
     * 插入
     * @param dto
     * @return
     */
    ResultVO insert(ExceptionDTO dto);
}
