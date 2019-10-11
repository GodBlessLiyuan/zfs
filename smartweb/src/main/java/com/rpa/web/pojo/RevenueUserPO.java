package com.rpa.web.pojo;

import java.io.Serializable;

/**
 * t_revenue_user
 * @author 
 */
public class RevenueUserPO implements Serializable {
    private Long userId;

    private Integer inviteCount;

    private Long registerCount;

    private Long totalRevenue;

    private Long withdraw;

    private Integer withdrawTime;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getInviteCount() {
        return inviteCount;
    }

    public void setInviteCount(Integer inviteCount) {
        this.inviteCount = inviteCount;
    }

    public Long getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Long registerCount) {
        this.registerCount = registerCount;
    }

    public Long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Long getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Long withdraw) {
        this.withdraw = withdraw;
    }

    public Integer getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(Integer withdrawTime) {
        this.withdrawTime = withdrawTime;
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
        RevenueUserPO other = (RevenueUserPO) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getInviteCount() == null ? other.getInviteCount() == null : this.getInviteCount().equals(other.getInviteCount()))
            && (this.getRegisterCount() == null ? other.getRegisterCount() == null : this.getRegisterCount().equals(other.getRegisterCount()))
            && (this.getTotalRevenue() == null ? other.getTotalRevenue() == null : this.getTotalRevenue().equals(other.getTotalRevenue()))
            && (this.getWithdraw() == null ? other.getWithdraw() == null : this.getWithdraw().equals(other.getWithdraw()))
            && (this.getWithdrawTime() == null ? other.getWithdrawTime() == null : this.getWithdrawTime().equals(other.getWithdrawTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getInviteCount() == null) ? 0 : getInviteCount().hashCode());
        result = prime * result + ((getRegisterCount() == null) ? 0 : getRegisterCount().hashCode());
        result = prime * result + ((getTotalRevenue() == null) ? 0 : getTotalRevenue().hashCode());
        result = prime * result + ((getWithdraw() == null) ? 0 : getWithdraw().hashCode());
        result = prime * result + ((getWithdrawTime() == null) ? 0 : getWithdrawTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", inviteCount=").append(inviteCount);
        sb.append(", registerCount=").append(registerCount);
        sb.append(", totalRevenue=").append(totalRevenue);
        sb.append(", withdraw=").append(withdraw);
        sb.append(", withdrawTime=").append(withdrawTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}