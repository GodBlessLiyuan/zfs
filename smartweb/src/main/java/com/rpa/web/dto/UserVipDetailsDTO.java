package com.rpa.web.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 16:58
 * @description: 用户会员详细信息
 * @version: 1.0
 */
public class UserVipDetailsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 会员获取方式
     */
    private String vipType;
    /**
     * 用户渠道名称
     */
    private String userChanName;
    /**
     * 销售渠道名称
     */
    private String saleChanName;
    /**
     * 获得会员时间
     */
    private Date createTime;
    /**
     * 产品类型
     */
    private String comTypeName;
    /**
     * 会员天数
     */
    private Integer days;
    /**
     * 支付方式：1-微信；2-支付宝
     */
    private Integer type;

    public String getVipType() {
        return vipType;
    }

    public void setVipType(String vipType) {
        this.vipType = vipType;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
