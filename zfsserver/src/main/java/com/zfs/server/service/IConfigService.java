package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.ConfigDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 10:33
 * @description: 获取砖助智能助手的基础信息
 * @version: 1.0
 */
public interface IConfigService {
    /**
     * 查询基础信息
     * @param dto
     * @return
     */
    ResultVO queryConfigInfo(ConfigDTO dto);
}
