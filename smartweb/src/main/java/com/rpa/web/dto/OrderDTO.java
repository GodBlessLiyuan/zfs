package com.rpa.web.dto;

import com.rpa.web.domain.OrderDO;
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
     * @param d
     * @return
     */
    public static OrderDTO convert(OrderDO d) {
        OrderDTO dto = new OrderDTO();

        dto.setOrderNumber(d.getOrderNumber());
        dto.setUserChanName(d.getUserChanName());
        dto.setSaleChanName(d.getSaleChanName());
        dto.setType(d.getType());
        dto.setPhone(d.getPhone());
        dto.setCreateTime(d.getCreateTime());
        dto.setPayTime(d.getPayTime());
        dto.setComName(d.getComName());
        dto.setDays(d.getDays());
        dto.setPrice(d.getPrice());
        dto.setShowDiscount(d.getShowDiscount());
        dto.setDiscount(d.getDiscount() / 100);

        return dto;
    }

    /**
     * dos 转 dtos
     *
     * @param dos
     * @return
     */
    public static List<OrderDTO> convert(List<OrderDO> dos) {

        List<OrderDTO> dtos = new ArrayList<>();
        for (OrderDO d : dos) {
            dtos.add(OrderDTO.convert(d));
        }

        return dtos;
    }
}
