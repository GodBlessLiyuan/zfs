package com.rpa.web.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * t_channel
 * @author 
 */
public class ChannelDTO implements Serializable {
    private Integer chanId;

    private String chanNickname;

    private String chanName;

    private String proName;

    private Integer proId;

    private Integer phone;

    private String extra;

    private Date createTime;

    private String operator;

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

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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
        ChannelDTO other = (ChannelDTO) that;
        return (this.getChanId() == null ? other.getChanId() == null : this.getChanId().equals(other.getChanId()))
            && (this.getChanNickname() == null ? other.getChanNickname() == null : this.getChanNickname().equals(other.getChanNickname()))
            && (this.getChanName() == null ? other.getChanName() == null : this.getChanName().equals(other.getChanName()))
            && (this.getProName() == null ? other.getProName() == null : this.getProName().equals(other.getProName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getChanId() == null) ? 0 : getChanId().hashCode());
        result = prime * result + ((getChanNickname() == null) ? 0 : getChanNickname().hashCode());
        result = prime * result + ((getChanName() == null) ? 0 : getChanName().hashCode());
        result = prime * result + ((getProName() == null) ? 0 : getProName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
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
        sb.append(", proName=").append(proName);
        sb.append(", phone=").append(phone);
        sb.append(", createTime=").append(createTime);
        sb.append(", extra=").append(extra);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}