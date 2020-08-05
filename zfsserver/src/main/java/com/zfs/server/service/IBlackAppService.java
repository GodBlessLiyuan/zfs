package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.BlackAppDTO;

/**
 * @author: xiahui
 * @date: Created in 2020/1/14 18:34
 * @description: 应用黑名单
 * @version: 1.0
 */
public interface IBlackAppService {
    /**
     * 获取黑名单包名
     *
     * @param dto
     * @return
     */
    ResultVO getBlankPkgs(BlackAppDTO dto);
}
