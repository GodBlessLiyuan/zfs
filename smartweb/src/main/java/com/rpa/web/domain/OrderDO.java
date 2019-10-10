package com.rpa.web.domain;

import com.rpa.web.pojo.OrderPO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 17:23
 * @description: TODO
 * @version: 1.0
 */
public class OrderDO extends OrderPO {

    private String userChanName;
    private String saleChanName;
    private String comTypeName;
    private Integer days;
    private String phone;
    private String comName;
    private Integer price;
    private String showDiscount;
    private Float discount;

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

    public String getComTypeName() {
        return comTypeName;
    }

    public void setComTypeName(String comTypeName) {
        this.comTypeName = comTypeName;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
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
