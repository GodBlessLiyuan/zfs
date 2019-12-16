package com.rpa.server.bo;

import com.rpa.server.pojo.OrderPO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/24 19:29
 * @description: 订单
 * @version: 1.0
 */
@Data
public class OrderBO extends OrderPO {

    private String comName;
}
