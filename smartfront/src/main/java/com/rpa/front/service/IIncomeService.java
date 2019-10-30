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
     * @param loginInfo
     * @return
     */
    ResultVO determine(DetermineDTO dto, IncomeDTO loginInfo);

    /**
     * 提现记录
     *
     * @param loginInfo
     * @return
     */
    ResultVO queryRecords(IncomeDTO loginInfo);

    /**
     * 邀请详情
     *
     * @param loginInfo
     * @return
     */
    ResultVO queryDetails(IncomeDTO loginInfo);
}
