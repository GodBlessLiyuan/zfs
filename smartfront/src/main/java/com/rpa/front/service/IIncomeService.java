package com.rpa.front.service;

import com.rpa.front.common.ResultVO;
import com.rpa.front.dto.DetermineDTO;
import com.rpa.front.dto.IncomeDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 19:03
 * @description: 爱收益
 * @version: 1.0
 */
public interface IIncomeService {
    /**
     * 查询
     *
     * @param dto
     * @return
     */
    ResultVO query(IncomeDTO dto);

    /**
     * 确认取款
     *
     * @param dto
     * @param userId
     * @return
     */
    ResultVO determine(DetermineDTO dto, long userId);

    /**
     * 提现记录
     *
     * @param userId
     * @return
     */
    ResultVO queryRecords(long userId);

    /**
     * 邀请详情
     *
     * @param userId
     * @return
     */
    ResultVO queryDetails(long userId);
}
