package com.rpa.server.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/24 16:48
 * @description: 订单
 * @version: 1.0
 */
@Data
public class OrderVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer type;
    private Integer paytype;
    private String comname;
    private String ordernumber;
    private Date paytime;
    private String price;
}
