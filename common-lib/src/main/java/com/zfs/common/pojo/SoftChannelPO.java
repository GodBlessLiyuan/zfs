package com.zfs.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_soft_channel
 * @author 
 */
public class SoftChannelPO implements Serializable {
    private Integer softChannelId;

    private String name;

    private String extra;

    private Date createTime;

    private Date updateTime;
    private String opsName;
    private Integer opsID;
    public String getOpsName() {
        return opsName;
    }

    public void setOpsName(String opsName) {
        this.opsName = opsName;
    }

    public Integer getOpsID() {
        return opsID;
    }

    public void setOpsID(Integer opsID) {
        this.opsID = opsID;
    }

    private static final long serialVersionUID = 1L;

    public Integer getSoftChannelId() {
        return softChannelId;
    }

    public void setSoftChannelId(Integer softChannelId) {
        this.softChannelId = softChannelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
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
        SoftChannelPO other = (SoftChannelPO) that;
        return (this.getSoftChannelId() == null ? other.getSoftChannelId() == null : this.getSoftChannelId().equals(other.getSoftChannelId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSoftChannelId() == null) ? 0 : getSoftChannelId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
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
        sb.append(", softChannelId=").append(softChannelId);
        sb.append(", name=").append(name);
        sb.append(", extra=").append(extra);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}