package com.zfs.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_app_ava_ch
 * @author 
 */
public class AppAvaChPO implements Serializable {
    private Long aacId;

    private Integer appId;

    private Integer softChannelId;

    private Long avatarId;

    /**
     * 1 未发布 2 发布
     */
    private Byte status;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getAacId() {
        return aacId;
    }

    public void setAacId(Long aacId) {
        this.aacId = aacId;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getSoftChannelId() {
        return softChannelId;
    }

    public void setSoftChannelId(Integer softChannelId) {
        this.softChannelId = softChannelId;
    }

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        AppAvaChPO other = (AppAvaChPO) that;
        return (this.getAacId() == null ? other.getAacId() == null : this.getAacId().equals(other.getAacId()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getSoftChannelId() == null ? other.getSoftChannelId() == null : this.getSoftChannelId().equals(other.getSoftChannelId()))
            && (this.getAvatarId() == null ? other.getAvatarId() == null : this.getAvatarId().equals(other.getAvatarId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAacId() == null) ? 0 : getAacId().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getSoftChannelId() == null) ? 0 : getSoftChannelId().hashCode());
        result = prime * result + ((getAvatarId() == null) ? 0 : getAvatarId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", aacId=").append(aacId);
        sb.append(", appId=").append(appId);
        sb.append(", softChannelId=").append(softChannelId);
        sb.append(", avatarId=").append(avatarId);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}