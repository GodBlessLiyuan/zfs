package com.zfs.rabbit.bo;

import com.zfs.rabbit.pojo.OrderPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/11/8 9:12
 * @description: 订单
 * @version: 1.0
 */
@Data
public class OrderBO extends OrderPO {
    private Integer comTypeId;
    private String comTypeName;
}
