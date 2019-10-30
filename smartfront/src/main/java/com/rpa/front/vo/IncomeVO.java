package com.rpa.front.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 20:00
 * @description: 爱收益
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 余额
     */
    private Long balance;
    /**
     * 成功邀请
     */
    private Integer invitenum;
    /**
     * 付款人数
     */
    private Integer paynum;
    /**
     * 累计奖励
     */
    private Long totalmny;
}
