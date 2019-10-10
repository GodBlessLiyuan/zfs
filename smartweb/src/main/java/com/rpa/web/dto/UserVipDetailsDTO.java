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

    public static final String TYPE_ORDER = "购买";
    public static final String TYPE_ACTIVITY = "好评活动赠送";
    public static final String TYPE_USER_GIFTS = "新用户赠送";
    public static final String TYPE_V = "V商神器赠送";
    public static final String TYPE_BATCH_INFO = "卡密激活";

    /** 会员获取方式 */
    private String vipType;
    /** 用户渠道名称 */
    private String softChannelName;
    /** 获得会员时间 */
    private Date createTime;
    /** 产品类型 */
    private String comTypeName;
    /** 会员天数 */
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

    public String getSoftChannelName() {
        return softChannelName;
    }

    public void setSoftChannelName(String softChannelName) {
        this.softChannelName = softChannelName;
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