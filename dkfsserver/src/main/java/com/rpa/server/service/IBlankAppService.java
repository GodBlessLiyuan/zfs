package com.rpa.server.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.BlankAppDTO;

/**
 * @author: xiahui
 * @date: Created in 2020/1/14 18:34
 * @description: 应用黑名单
 * @version: 1.0
 */
public interface IBlankAppService {
    /**
     * 获取黑名单包名
     *
     * @param dto
     * @return
     */
    ResultVO getBlankPkgs(BlankAppDTO dto);
}
