package com.rpa.web.vo;

import java.io.Serializable;

/**
 * t_revenue_user
 * @author 
 */
public class RevenueUserVO implements Serializable {
    private Long userId;

    private Integer inviteCount;

    private Integer payCount;

    private Long registerCount;

    private Double totalRevenue;

    private Double withdraw;

    private Integer withdrawTime;

    private Double remaining;

    private String invitePhone;

    private String phone;

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

    public Integer getPayCount() {
        return payCount;
    }

    public void setPayCount(Integer payCount) {
        this.payCount = payCount;
    }

    public Long getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Long registerCount) {
        this.registerCount = registerCount;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Double withdraw) {
        this.withdraw = withdraw;
    }

    public Integer getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(Integer withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

    public Double getRemaining() {
        return remaining;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }

    public String getInvitePhone() {
        return invitePhone;
    }

    public void setInvitePhone(String invitePhone) {
        this.invitePhone = invitePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        RevenueUserVO other = (RevenueUserVO) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getInviteCount() == null ? other.getInviteCount() == null : this.getInviteCount().equals(other.getInviteCount()))
            && (this.getPayCount() == null ? other.getPayCount() == null : this.getPayCount().equals(other.getPayCount()))
            && (this.getRegisterCount() == null ? other.getRegisterCount() == null : this.getRegisterCount().equals(other.getRegisterCount()))
            && (this.getTotalRevenue() == null ? other.getTotalRevenue() == null : this.getTotalRevenue().equals(other.getTotalRevenue()))
            && (this.getWithdraw() == null ? other.getWithdraw() == null : this.getWithdraw().equals(other.getWithdraw()))
            && (this.getWithdrawTime() == null ? other.getWithdrawTime() == null : this.getWithdrawTime().equals(other.getWithdrawTime()))
            && (this.getRemaining() == null ? other.getRemaining() == null : this.getRemaining().equals(other.getRemaining()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getInviteCount() == null) ? 0 : getInviteCount().hashCode());
        result = prime * result + ((getPayCount() == null) ? 0 : getPayCount().hashCode());
        result = prime * result + ((getRegisterCount() == null) ? 0 : getRegisterCount().hashCode());
        result = prime * result + ((getTotalRevenue() == null) ? 0 : getTotalRevenue().hashCode());
        result = prime * result + ((getWithdraw() == null) ? 0 : getWithdraw().hashCode());
        result = prime * result + ((getWithdrawTime() == null) ? 0 : getWithdrawTime().hashCode());
        result = prime * result + ((getRemaining() == null) ? 0 : getRemaining().hashCode());
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
        sb.append(", payCount=").append(payCount);
        sb.append(", registerCount=").append(registerCount);
        sb.append(", totalRevenue=").append(totalRevenue);
        sb.append(", withdraw=").append(withdraw);
        sb.append(", withdrawTime=").append(withdrawTime);
        sb.append(", remaining=").append(remaining);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}