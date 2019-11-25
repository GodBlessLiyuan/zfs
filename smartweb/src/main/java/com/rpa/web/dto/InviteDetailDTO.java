package com.rpa.web.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * t_invite_detail
 * @author 
 */
public class InviteDetailDTO implements Serializable {
    private Long indeId;

    private Integer orderId;

    private Integer comTypeId;

    private String comTypeName;

    private Double pay;

    private Double earnings;

    private String proportion;

    private Long inviteId;

    private Long inviteeId;

    private Integer viptypeId;

    private Date payTime;

    private String vipname;

    private static final long serialVersionUID = 1L;

    public Long getIndeId() {
        return indeId;
    }

    public void setIndeId(Long indeId) {
        this.indeId = indeId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Double getEarnings() {
        return earnings;
    }

    public void setEarnings(Double earnings) {
        this.earnings = earnings;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public Long getInviteeId() {
        return inviteeId;
    }

    public void setInviteeId(Long inviteeId) {
        this.inviteeId = inviteeId;
    }

    public Integer getViptypeId() {
        return viptypeId;
    }

    public void setViptypeId(Integer viptypeId) {
        this.viptypeId = viptypeId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getVipname() {
        return vipname;
    }

    public void setVipname(String vipname) {
        this.vipname = vipname;
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
        InviteDetailDTO other = (InviteDetailDTO) that;
        return (this.getIndeId() == null ? other.getIndeId() == null : this.getIndeId().equals(other.getIndeId()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getComTypeId() == null ? other.getComTypeId() == null : this.getComTypeId().equals(other.getComTypeId()))
            && (this.getComTypeName() == null ? other.getComTypeName() == null : this.getComTypeName().equals(other.getComTypeName()))
            && (this.getPay() == null ? other.getPay() == null : this.getPay().equals(other.getPay()))
            && (this.getEarnings() == null ? other.getEarnings() == null : this.getEarnings().equals(other.getEarnings()))
            && (this.getProportion() == null ? other.getProportion() == null : this.getProportion().equals(other.getProportion()))
            && (this.getInviteId() == null ? other.getInviteId() == null : this.getInviteId().equals(other.getInviteId()))
            && (this.getInviteeId() == null ? other.getInviteeId() == null : this.getInviteeId().equals(other.getInviteeId()))
            && (this.getViptypeId() == null ? other.getViptypeId() == null : this.getViptypeId().equals(other.getViptypeId()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getIndeId() == null) ? 0 : getIndeId().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getComTypeId() == null) ? 0 : getComTypeId().hashCode());
        result = prime * result + ((getComTypeName() == null) ? 0 : getComTypeName().hashCode());
        result = prime * result + ((getPay() == null) ? 0 : getPay().hashCode());
        result = prime * result + ((getEarnings() == null) ? 0 : getEarnings().hashCode());
        result = prime * result + ((getProportion() == null) ? 0 : getProportion().hashCode());
        result = prime * result + ((getInviteId() == null) ? 0 : getInviteId().hashCode());
        result = prime * result + ((getInviteeId() == null) ? 0 : getInviteeId().hashCode());
        result = prime * result + ((getViptypeId() == null) ? 0 : getViptypeId().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", indeId=").append(indeId);
        sb.append(", orderId=").append(orderId);
        sb.append(", comTypeId=").append(comTypeId);
        sb.append(", comTypeName=").append(comTypeName);
        sb.append(", pay=").append(pay);
        sb.append(", earnings=").append(earnings);
        sb.append(", proportion=").append(proportion);
        sb.append(", inviteId=").append(inviteId);
        sb.append(", inviteeId=").append(inviteeId);
        sb.append(", viptypeId=").append(viptypeId);
        sb.append(", payTime=").append(payTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}