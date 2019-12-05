package com.rpa.web.domain;

import com.rpa.web.pojo.OrderPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 17:23
 * @description: TODO
 * @version: 1.0
 */
@Data
public class OrderDO extends OrderPO {

    private String userChanName;
    private String saleChanName;
    private String comTypeName;
    private Integer days;
    private String phone;
    private String comName;
    private Float price;
    private String showDiscount;
    private Float discount;
}
