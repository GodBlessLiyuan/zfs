package com.rpa.web.dto;

import com.rpa.web.domain.OrderDO;

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
    private String userChanName;
    private String saleChanName;
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

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUserChanName() {
        return userChanName;
    }

    public void setUserChanName(String userChanName) {
        this.userChanName = userChanName;
    }

    public String getSaleChanName() {
        return saleChanName;
    }

    public void setSaleChanName(String saleChanName) {
        this.saleChanName = saleChanName;
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
