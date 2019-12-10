package com.rpa.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_channel
 * @author 
 */
public class ChannelPO implements Serializable {
    private Integer chanId;

    private String chanNickname;

    private String chanName;

    private Integer proId;

    private Integer aId;

    private Date createTime;

    private Date updateTime;

    private String extra;

    /**
     * 1 未删除  2删除
     */
    private Byte dr;

    private static final long serialVersionUID = 1L;

    public Integer getChanId() {
        return chanId;
    }

    public void setChanId(Integer chanId) {
        this.chanId = chanId;
    }

    public String getChanNickname() {
        return chanNickname;
    }

    public void setChanNickname(String chanNickname) {
        this.chanNickname = chanNickname;
    }

    public String getChanName() {
        return chanName;
    }

    public void setChanName(String chanName) {
        this.chanName = chanName;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
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

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Byte getDr() {
        return dr;
    }

    public void setDr(Byte dr) {
        this.dr = dr;
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
        ChannelPO other = (ChannelPO) that;
        return (this.getChanId() == null ? other.getChanId() == null : this.getChanId().equals(other.getChanId()))
            && (this.getChanNickname() == null ? other.getChanNickname() == null : this.getChanNickname().equals(other.getChanNickname()))
            && (this.getChanName() == null ? other.getChanName() == null : this.getChanName().equals(other.getChanName()))
            && (this.getProId() == null ? other.getProId() == null : this.getProId().equals(other.getProId()))
            && (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getDr() == null ? other.getDr() == null : this.getDr().equals(other.getDr()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getChanId() == null) ? 0 : getChanId().hashCode());
        result = prime * result + ((getChanNickname() == null) ? 0 : getChanNickname().hashCode());
        result = prime * result + ((getChanName() == null) ? 0 : getChanName().hashCode());
        result = prime * result + ((getProId() == null) ? 0 : getProId().hashCode());
        result = prime * result + ((getaId() == null) ? 0 : getaId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        result = prime * result + ((getDr() == null) ? 0 : getDr().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", chanId=").append(chanId);
        sb.append(", chanNickname=").append(chanNickname);
        sb.append(", chanName=").append(chanName);
        sb.append(", proId=").append(proId);
        sb.append(", aId=").append(aId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", extra=").append(extra);
        sb.append(", dr=").append(dr);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}