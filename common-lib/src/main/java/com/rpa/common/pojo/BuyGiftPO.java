package com.rpa.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_buy_gift
 * @author 
 */
public class BuyGiftPO implements Serializable {
    private Long bgId;

    private Long userId;

    /**
     * 1：微信；2：支付宝
     */
    private Byte type;

    private String cmdyName;

    private String comTypeName;

    private Date createTime;

    private Date starttime;

    private Date endtime;

    private Integer days;
    private String comName;

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    private static final long serialVersionUID = 1L;

    public Long getBgId() {
        return bgId;
    }

    public void setBgId(Long bgId) {
        this.bgId = bgId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getCmdyName() {
        return cmdyName;
    }

    public void setCmdyName(String cmdyName) {
        this.cmdyName = cmdyName;
    }

    public String getComTypeName() {
        return comTypeName;
    }

    public void setComTypeName(String comTypeName) {
        this.comTypeName = comTypeName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BuyGiftPO other = (BuyGiftPO) that;
        return (this.getBgId() == null ? other.getBgId() == null : this.getBgId().equals(other.getBgId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCmdyName() == null ? other.getCmdyName() == null : this.getCmdyName().equals(other.getCmdyName()))
            && (this.getComTypeName() == null ? other.getComTypeName() == null : this.getComTypeName().equals(other.getComTypeName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getStarttime() == null ? other.getStarttime() == null : this.getStarttime().equals(other.getStarttime()))
            && (this.getEndtime() == null ? other.getEndtime() == null : this.getEndtime().equals(other.getEndtime()))
            && (this.getDays() == null ? other.getDays() == null : this.getDays().equals(other.getDays()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBgId() == null) ? 0 : getBgId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCmdyName() == null) ? 0 : getCmdyName().hashCode());
        result = prime * result + ((getComTypeName() == null) ? 0 : getComTypeName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getStarttime() == null) ? 0 : getStarttime().hashCode());
        result = prime * result + ((getEndtime() == null) ? 0 : getEndtime().hashCode());
        result = prime * result + ((getDays() == null) ? 0 : getDays().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", bgId=").append(bgId);
        sb.append(", userId=").append(userId);
        sb.append(", type=").append(type);
        sb.append(", cmdyName=").append(cmdyName);
        sb.append(", comTypeName=").append(comTypeName);
        sb.append(", createTime=").append(createTime);
        sb.append(", starttime=").append(starttime);
        sb.append(", endtime=").append(endtime);
        sb.append(", days=").append(days);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}