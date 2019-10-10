package com.rpa.web.domain;

import com.rpa.web.pojo.UserActivityPO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 11:29
 * @description: 活动赠送记录
 * @version: 1.0
 */
public class UserActivityDO extends UserActivityPO {

    private String phone;
    private String userChanName;
    /**
     * 日卡，周卡，月卡，年卡
     */
    private String comTypeName;
    private Integer days;
    /**
     * 1 活动赠送
     */
    private Integer source;

    @Override
    public String toString() {
        return super.toString();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserChanName() {
        return userChanName;
    }

    public void setUserChanName(String userChanName) {
        this.userChanName = userChanName;
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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }
}
