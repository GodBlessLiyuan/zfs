package com.rpa.front.service;

import com.rpa.front.dto.IncomeDTO;
import com.rpa.front.vo.DetailsVO;
import com.rpa.front.vo.IncomeVO;
import com.rpa.front.vo.RecordsVO;

import java.util.List;

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
    IncomeVO query(IncomeDTO dto);

    /**
     * 提现记录
     *
     * @param userId
     * @return
     */
    List<RecordsVO> queryRecords(long userId);

    /**
     * 邀请详情
     *
     * @param userId
     * @return
     */
    DetailsVO queryDetails(long userId);
}
