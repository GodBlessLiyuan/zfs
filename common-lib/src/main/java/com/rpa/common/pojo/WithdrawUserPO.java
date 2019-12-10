package com.rpa.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_withdraw_user
 * @author 
 */
public class WithdrawUserPO implements Serializable {
    private Integer withdrawId;

    private Date createTime;

    private Long userId;

    private Long deviceId;

    private Integer userDeviceId;

    private Long withdraw;

    private Long remaining;

    private String aliAccount;

    private String aliName;

    private Integer withdrawTime;

    private Date auditTime;

    private Date endTime;

    /**
     * 1 待审核  2 运营驳回 3 打款中  4 支付宝驳回  5 完成
     */
    private Byte status;

    private Integer aId;

    private static final long serialVersionUID = 1L;

    public Integer getWithdrawId() {
        return withdrawId;
    }

    public void setWithdrawId(Integer withdrawId) {
        this.withdrawId = withdrawId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getUserDeviceId() {
        return userDeviceId;
    }

    public void setUserDeviceId(Integer userDeviceId) {
        this.userDeviceId = userDeviceId;
    }

    public Long getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Long withdraw) {
        this.withdraw = withdraw;
    }

    public Long getRemaining() {
        return remaining;
    }

    public void setRemaining(Long remaining) {
        this.remaining = remaining;
    }

    public String getAliAccount() {
        return aliAccount;
    }

    public void setAliAccount(String aliAccount) {
        this.aliAccount = aliAccount;
    }

    public String getAliName() {
        return aliName;
    }

    public void setAliName(String aliName) {
        this.aliName = aliName;
    }

    public Integer getWithdrawTime() {
        return withdrawTime;
    }

    public void setWithdrawTime(Integer withdrawTime) {
        this.withdrawTime = withdrawTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        WithdrawUserPO other = (WithdrawUserPO) that;
        return (this.getWithdrawId() == null ? other.getWithdrawId() == null : this.getWithdrawId().equals(other.getWithdrawId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getUserDeviceId() == null ? other.getUserDeviceId() == null : this.getUserDeviceId().equals(other.getUserDeviceId()))
            && (this.getWithdraw() == null ? other.getWithdraw() == null : this.getWithdraw().equals(other.getWithdraw()))
            && (this.getRemaining() == null ? other.getRemaining() == null : this.getRemaining().equals(other.getRemaining()))
            && (this.getAliAccount() == null ? other.getAliAccount() == null : this.getAliAccount().equals(other.getAliAccount()))
            && (this.getAliName() == null ? other.getAliName() == null : this.getAliName().equals(other.getAliName()))
            && (this.getWithdrawTime() == null ? other.getWithdrawTime() == null : this.getWithdrawTime().equals(other.getWithdrawTime()))
            && (this.getAuditTime() == null ? other.getAuditTime() == null : this.getAuditTime().equals(other.getAuditTime()))
            && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWithdrawId() == null) ? 0 : getWithdrawId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getUserDeviceId() == null) ? 0 : getUserDeviceId().hashCode());
        result = prime * result + ((getWithdraw() == null) ? 0 : getWithdraw().hashCode());
        result = prime * result + ((getRemaining() == null) ? 0 : getRemaining().hashCode());
        result = prime * result + ((getAliAccount() == null) ? 0 : getAliAccount().hashCode());
        result = prime * result + ((getAliName() == null) ? 0 : getAliName().hashCode());
        result = prime * result + ((getWithdrawTime() == null) ? 0 : getWithdrawTime().hashCode());
        result = prime * result + ((getAuditTime() == null) ? 0 : getAuditTime().hashCode());
        result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getaId() == null) ? 0 : getaId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", withdrawId=").append(withdrawId);
        sb.append(", createTime=").append(createTime);
        sb.append(", userId=").append(userId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", userDeviceId=").append(userDeviceId);
        sb.append(", withdraw=").append(withdraw);
        sb.append(", remaining=").append(remaining);
        sb.append(", aliAccount=").append(aliAccount);
        sb.append(", aliName=").append(aliName);
        sb.append(", withdrawTime=").append(withdrawTime);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", status=").append(status);
        sb.append(", aId=").append(aId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}