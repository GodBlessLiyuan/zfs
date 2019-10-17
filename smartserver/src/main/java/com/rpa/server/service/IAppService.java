package com.rpa.server.service;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.AppDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 8:59
 * @description: 版本更新
 * @version: 1.0
 */
public interface IAppService {
    /**
     * 校验
     *
     * @param dto
     * @return
     */
    ResultVO check(AppDTO dto);
}
