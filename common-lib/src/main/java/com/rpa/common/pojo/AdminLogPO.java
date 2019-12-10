package com.rpa.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_admin_log
 * @author 
 */
public class AdminLogPO implements Serializable {
    private Integer vId;

    private Integer aId;

    private String action;

    private Date actionTime;

    private static final long serialVersionUID = 1L;

    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
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
        AdminLogPO other = (AdminLogPO) that;
        return (this.getvId() == null ? other.getvId() == null : this.getvId().equals(other.getvId()))
            && (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
            && (this.getActionTime() == null ? other.getActionTime() == null : this.getActionTime().equals(other.getActionTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getvId() == null) ? 0 : getvId().hashCode());
        result = prime * result + ((getaId() == null) ? 0 : getaId().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getActionTime() == null) ? 0 : getActionTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", vId=").append(vId);
        sb.append(", aId=").append(aId);
        sb.append(", action=").append(action);
        sb.append(", actionTime=").append(actionTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}