package com.rpa.server.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.UserVipDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 18:38
 * @description: 用户会员
 * @version: 1.0
 */
public interface IUserVipService {
    /**
     * 验证
     * @param dto
     * @return
     */
    ResultVO validate(UserVipDTO dto);
}
