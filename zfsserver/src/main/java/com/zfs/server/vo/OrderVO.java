package com.zfs.server.vo;

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
public class OrderVO implements Serializable, Comparable<OrderVO> {
    private static final long serialVersionUID = 1L;

    private Integer type;
    private Integer paytype;
    private String comname;
    private String ordernumber;
    private Date paytime;
    private String price;

    @Override
    public int compareTo(OrderVO vo) {
        if (null == this.paytime || null == vo.getPaytime()) {
            return -1;
        }
        // 降序
        return vo.getPaytime().compareTo(this.paytime);
    }
}
