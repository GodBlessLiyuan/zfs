package com.rpa.web.dto;

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
public class OrderDTO implements Serializable {

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
    public static OrderDTO convert(OrderBO bo) {
        OrderDTO dto = new OrderDTO();

        dto.setOrderNumber(bo.getOrderNumber());
        dto.setUserChanName(bo.getUserChanName());
        dto.setSaleChanName(bo.getSaleChanName());
        dto.setType(bo.getType());
        dto.setPhone(bo.getPhone());
        dto.setCreateTime(bo.getCreateTime());
        dto.setPayTime(bo.getPayTime());
        dto.setComName(bo.getComName());
        dto.setDays(bo.getDays());
        dto.setPrice(bo.getPrice());
        dto.setShowDiscount(bo.getShowDiscount());
        dto.setDiscount(bo.getDiscount() / 100);

        return dto;
    }

    /**
     * dos 转 dtos
     *
     * @param bos
     * @return
     */
    public static List<OrderDTO> convert(List<OrderBO> bos) {

        List<OrderDTO> dtos = new ArrayList<>();
        for (OrderBO bo : bos) {
            dtos.add(OrderDTO.convert(bo));
        }

        return dtos;
    }
}
