package com.rpa.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_com_type
 * @author 
 */
public class ComTypePO implements Serializable {
    private Integer comTypeId;

    /**
     * 日卡，周卡，月卡，年卡
     */
    private String name;

    private Integer days;

    private String extra;

    private Integer aId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getComTypeId() {
        return comTypeId;
    }

    public void setComTypeId(Integer comTypeId) {
        this.comTypeId = comTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
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
        ComTypePO other = (ComTypePO) that;
        return (this.getComTypeId() == null ? other.getComTypeId() == null : this.getComTypeId().equals(other.getComTypeId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDays() == null ? other.getDays() == null : this.getDays().equals(other.getDays()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getComTypeId() == null) ? 0 : getComTypeId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDays() == null) ? 0 : getDays().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
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
        sb.append(", comTypeId=").append(comTypeId);
        sb.append(", name=").append(name);
        sb.append(", days=").append(days);
        sb.append(", extra=").append(extra);
        sb.append(", aId=").append(aId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}