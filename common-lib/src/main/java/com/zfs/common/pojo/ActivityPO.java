package com.zfs.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_activity
 * @author 
 */
public class ActivityPO implements Serializable {
    private Integer activityId;

    private String activityname;

    /**
     * 1 会员中心 
     */
    private Byte position;

    private Integer status;

    private Integer aId;

    private Date createTime;

    private Date updateTime;

    /**
     * 1 未删除  2删除
     */
    private Byte dr;

    private Integer comTypeId;

    private Integer days;

    /**
     * 日卡，周卡，月卡，年卡
     */
    private String comTypeName;

    /**
     * 1 活动赠送
     */
    private Integer source;

    private static final long serialVersionUID = 1L;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
    }

    public Byte getPosition() {
        return position;
    }

    public void setPosition(Byte position) {
        this.position = position;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getDr() {
        return dr;
    }

    public void setDr(Byte dr) {
        this.dr = dr;
    }

    public Integer getComTypeId() {
        return comTypeId;
    }

    public void setComTypeId(Integer comTypeId) {
        this.comTypeId = comTypeId;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getComTypeName() {
        return comTypeName;
    }

    public void setComTypeName(String comTypeName) {
        this.comTypeName = comTypeName;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
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
        ActivityPO other = (ActivityPO) that;
        return (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId()))
            && (this.getActivityname() == null ? other.getActivityname() == null : this.getActivityname().equals(other.getActivityname()))
            && (this.getPosition() == null ? other.getPosition() == null : this.getPosition().equals(other.getPosition()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDr() == null ? other.getDr() == null : this.getDr().equals(other.getDr()))
            && (this.getComTypeId() == null ? other.getComTypeId() == null : this.getComTypeId().equals(other.getComTypeId()))
            && (this.getDays() == null ? other.getDays() == null : this.getDays().equals(other.getDays()))
            && (this.getComTypeName() == null ? other.getComTypeName() == null : this.getComTypeName().equals(other.getComTypeName()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
        result = prime * result + ((getActivityname() == null) ? 0 : getActivityname().hashCode());
        result = prime * result + ((getPosition() == null) ? 0 : getPosition().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getaId() == null) ? 0 : getaId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDr() == null) ? 0 : getDr().hashCode());
        result = prime * result + ((getComTypeId() == null) ? 0 : getComTypeId().hashCode());
        result = prime * result + ((getDays() == null) ? 0 : getDays().hashCode());
        result = prime * result + ((getComTypeName() == null) ? 0 : getComTypeName().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activityId=").append(activityId);
        sb.append(", activityname=").append(activityname);
        sb.append(", position=").append(position);
        sb.append(", status=").append(status);
        sb.append(", aId=").append(aId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", dr=").append(dr);
        sb.append(", comTypeId=").append(comTypeId);
        sb.append(", days=").append(days);
        sb.append(", comTypeName=").append(comTypeName);
        sb.append(", source=").append(source);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}