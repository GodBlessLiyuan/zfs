package com.rpa.server.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.BatchInfoDTO;
import com.rpa.server.dto.BatchSycInfoDTO;

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
