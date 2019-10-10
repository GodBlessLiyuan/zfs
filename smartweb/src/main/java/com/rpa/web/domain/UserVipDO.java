package com.rpa.web.domain;

import com.rpa.web.pojo.UserVipPO;

import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 14:28
 * @description: 用户会员数据
 * @version: 1.0
 */
public class UserVipDO extends UserVipPO {
    private String phone;
    private Date createTime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
