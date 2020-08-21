package com.zfs.server.service;


import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.BatchInfoDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 9:27
 * @description: 卡密表
 * @version: 1.0
 */
public interface IBatchInfoService {
    /**
     * 卡密激活
     *
     * @param dto
     * @return
     */
    ResultVO activate(BatchInfoDTO dto);

}
