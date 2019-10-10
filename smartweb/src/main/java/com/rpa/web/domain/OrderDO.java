package com.rpa.web.domain;

import com.rpa.web.pojo.OrderPO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 17:23
 * @description: TODO
 * @version: 1.0
 */
public class OrderDO extends OrderPO {

    private String softChannelName;
    private String comTypeName;
    private Integer days;

    public String getSoftChannelName() {
        return softChannelName;
    }

    public void setSoftChannelName(String softChannelName) {
        this.softChannelName = softChannelName;
    }

    public String getComTypeName() {
        return comTypeName;
    }

    public void setComTypeName(String comTypeName) {
        this.comTypeName = comTypeName;
    }

    @Override
    public Integer getDays() {
        return days;
    }

    @Override
    public void setDays(Integer days) {
        this.days = days;
    }
}
