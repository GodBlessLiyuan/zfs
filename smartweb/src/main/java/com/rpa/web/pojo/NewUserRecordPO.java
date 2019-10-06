package com.rpa.web.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_new_user_record
 * @author 
 */
public class NewUserRecordPO implements Serializable {
    private Integer nurId;

    /**
     * 允许为null
     */
    private Long userId;

    private Integer userDeviceId;

    /**
     * 允许为null
     */
    private Long deviceId;

    private Integer nugId;

    private Date createTime;

    private String phone;
    private String comTypeName;
    private Integer days;

    private static final long serialVersionUID = 1L;

    public Integer getNurId() {
        return nurId;
    }

    public void setNurId(Integer nurId) {
        this.nurId = nurId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getUserDeviceId() {
        return userDeviceId;
    }

    public void setUserDeviceId(Integer userDeviceId) {
        this.userDeviceId = userDeviceId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getNugId() {
        return nugId;
    }

    public void setNugId(Integer nugId) {
        this.nugId = nugId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        NewUserRecordPO other = (NewUserRecordPO) that;
        return (this.getNurId() == null ? other.getNurId() == null : this.getNurId().equals(other.getNurId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getUserDeviceId() == null ? other.getUserDeviceId() == null : this.getUserDeviceId().equals(other.getUserDeviceId()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getNugId() == null ? other.getNugId() == null : this.getNugId().equals(other.getNugId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNurId() == null) ? 0 : getNurId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getUserDeviceId() == null) ? 0 : getUserDeviceId().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getNugId() == null) ? 0 : getNugId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", nurId=").append(nurId);
        sb.append(", userId=").append(userId);
        sb.append(", userDeviceId=").append(userDeviceId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", nugId=").append(nugId);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}