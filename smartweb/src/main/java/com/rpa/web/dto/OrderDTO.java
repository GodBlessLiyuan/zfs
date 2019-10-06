package com.rpa.web.dto;

import com.rpa.web.pojo.OrderPO;

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
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderNumber;
    private String name;
    private Integer type;
    private String phone;
    private Date createTime;
    private Date payTime;
    private String comName;
    private Integer days;
    private Integer price;
    private String showDiscount;
    private Float discount;

    /**
     * po 转 dto
     *
     * @param po
     * @return
     */
    public static OrderDTO convert(OrderPO po) {
        OrderDTO dto = new OrderDTO();

        dto.setOrderNumber(po.getOrderNumber());
        dto.setName(po.getName());
        dto.setType(po.getType());
        dto.setPhone(po.getPhone());
        dto.setCreateTime(po.getCreateTime());
        dto.setPayTime(po.getPayTime());
        dto.setComName(po.getComName());
        dto.setDays(po.getDays());
        dto.setPrice(po.getPrice());
        dto.setShowDiscount(po.getShowDiscount());
        dto.setDiscount(po.getDiscount());

        return dto;
    }

    /**
     * pos 转 dtos
     *
     * @param pos
     * @return
     */
    public static List<OrderDTO> convert(List<OrderPO> pos) {

        List<OrderDTO> dtos = new ArrayList<>();
        for (OrderPO po : pos) {
            dtos.add(OrderDTO.convert(po));
        }

        return dtos;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getShowDiscount() {
        return showDiscount;
    }

    public void setShowDiscount(String showDiscount) {
        this.showDiscount = showDiscount;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }
}
