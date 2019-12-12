package com.rpa.web.vo;

import com.rpa.common.bo.OrderBO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 14:00
 * @description: 订单信息
 * @version: 1.0
 */
@Data
public class OrderVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderNumber;
    private String userChanName;
    private String saleChanName;
    private Integer type;
    private String phone;
    private Date createTime;
    private Date payTime;
    private String comName;
    private Integer days;
    private Float price;
    private String showDiscount;
    private Float discount;

    /**
     * do 转 dto
     *
     * @param bo
     * @return
     */
    public static OrderVO convert(OrderBO bo) {
        OrderVO vo = new OrderVO();

        vo.setOrderNumber(bo.getOrderNumber());
        vo.setUserChanName(bo.getUserChanName());
        vo.setSaleChanName(bo.getSaleChanName());
        vo.setType(bo.getType());
        vo.setPhone(bo.getPhone());
        vo.setCreateTime(bo.getCreateTime());
        vo.setPayTime(bo.getPayTime());
        vo.setComName(bo.getComName());
        vo.setDays(bo.getDays());
        vo.setPrice(bo.getPrice());
        vo.setShowDiscount(bo.getShowDiscount());
        vo.setDiscount(bo.getDiscount() / 100);

        return vo;
    }

    /**
     * dos 转 dtos
     *
     * @param bos
     * @return
     */
    public static List<OrderVO> convert(List<OrderBO> bos) {

        List<OrderVO> vos = new ArrayList<>();
        for (OrderBO bo : bos) {
            vos.add(OrderVO.convert(bo));
        }

        return vos;
    }
}
