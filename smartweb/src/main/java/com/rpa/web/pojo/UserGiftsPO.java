package com.rpa.web.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_user_gifts
 * @author 
 */
public class UserGiftsPO implements Serializable {
    private Integer nugId;

    private Integer comTypeId;

    private String comTypeName;

    private Integer days;

    /**
     * 1 未开启 2 开启  3 删除
     */
    private Byte status;

    private Integer aId;

    private Date createTime;

    private Date updateTime;

    private String username;

    private static final long serialVersionUID = 1L;

    public Integer getNugId() {
        return nugId;
    }

    public void setNugId(Integer nugId) {
        this.nugId = nugId;
    }

    public Integer getComTypeId() {
        return comTypeId;
    }

    public void setComTypeId(Integer comTypeId) {
        this.comTypeId = comTypeId;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        UserGiftsPO other = (UserGiftsPO) that;
        return (this.getNugId() == null ? other.getNugId() == null : this.getNugId().equals(other.getNugId()))
            && (this.getComTypeId() == null ? other.getComTypeId() == null : this.getComTypeId().equals(other.getComTypeId()))
            && (this.getComTypeName() == null ? other.getComTypeName() == null : this.getComTypeName().equals(other.getComTypeName()))
            && (this.getDays() == null ? other.getDays() == null : this.getDays().equals(other.getDays()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNugId() == null) ? 0 : getNugId().hashCode());
        result = prime * result + ((getComTypeId() == null) ? 0 : getComTypeId().hashCode());
        result = prime * result + ((getComTypeName() == null) ? 0 : getComTypeName().hashCode());
        result = prime * result + ((getDays() == null) ? 0 : getDays().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getaId() == null) ? 0 : getaId().hashCode());
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
        sb.append(", nugId=").append(nugId);
        sb.append(", comTypeId=").append(comTypeId);
        sb.append(", comTypeName=").append(comTypeName);
        sb.append(", days=").append(days);
        sb.append(", status=").append(status);
        sb.append(", aId=").append(aId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}