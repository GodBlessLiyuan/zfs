package com.rpa.web.pojo;

import java.io.Serializable;

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
            && (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()));
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}