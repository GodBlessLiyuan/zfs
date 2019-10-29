package com.rpa.front.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 9:02
 * @description: 提现记录
 * @version: 1.0
 */
@Data
public class RecordsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date cTime;
    private String account;
    private String name;
    private Byte status;
}
