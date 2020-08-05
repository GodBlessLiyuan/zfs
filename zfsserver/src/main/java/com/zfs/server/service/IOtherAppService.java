package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.OtherAppDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 11:15
 * @description: 其他应用
 * @version: 1.0
 */
public interface IOtherAppService {
    /**
     * 查询 其他应用
     *
     * @param dto
     * @return
     */
    ResultVO query(OtherAppDTO dto);
}
