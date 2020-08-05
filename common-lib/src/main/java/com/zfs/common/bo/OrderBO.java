package com.zfs.common.bo;

import com.zfs.common.pojo.OrderPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/11 11:20
 * @description: TODO
 * @version: 1.0
 */
@Data
public class OrderBO extends OrderPO {

    private String userChanName;
    private String saleChanName;
    private String comTypeName;
    private Integer days;
    private String phone;
    private String comName;
    private Float price;
    private String showDiscount;
    private Float discount;
    private Byte commAttr;
}
