package com.rpa.web.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_vipcommodity
 * @author 
 */
public class VipCommodityPO implements Serializable {
    private Integer cmdyId;

    private Integer viptypeId;

    private Integer comTypeId;

    private String comName;

    private Integer price;

    private Float discount;

    private Integer positon;

    private Date createTime;

    private Integer aId;

    private Integer softChannelId;

    private String name;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getCmdyId() {
        return cmdyId;
    }

    public void setCmdyId(Integer cmdyId) {
        this.cmdyId = cmdyId;
    }

    public Integer getViptypeId() {
        return viptypeId;
    }

    public void setViptypeId(Integer viptypeId) {
        this.viptypeId = viptypeId;
    }

    public Integer getComTypeId() {
        return comTypeId;
    }

    public void setComTypeId(Integer comTypeId) {
        this.comTypeId = comTypeId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getPositon() {
        return positon;
    }

    public void setPositon(Integer positon) {
        this.positon = positon;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Integer getSoftChannelId() {
        return softChannelId;
    }

    public void setSoftChannelId(Integer softChannelId) {
        this.softChannelId = softChannelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        VipCommodityPO other = (VipCommodityPO) that;
        return (this.getCmdyId() == null ? other.getCmdyId() == null : this.getCmdyId().equals(other.getCmdyId()))
            && (this.getViptypeId() == null ? other.getViptypeId() == null : this.getViptypeId().equals(other.getViptypeId()))
            && (this.getComTypeId() == null ? other.getComTypeId() == null : this.getComTypeId().equals(other.getComTypeId()))
            && (this.getComName() == null ? other.getComName() == null : this.getComName().equals(other.getComName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getDiscount() == null ? other.getDiscount() == null : this.getDiscount().equals(other.getDiscount()))
            && (this.getPositon() == null ? other.getPositon() == null : this.getPositon().equals(other.getPositon()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()))
            && (this.getSoftChannelId() == null ? other.getSoftChannelId() == null : this.getSoftChannelId().equals(other.getSoftChannelId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCmdyId() == null) ? 0 : getCmdyId().hashCode());
        result = prime * result + ((getViptypeId() == null) ? 0 : getViptypeId().hashCode());
        result = prime * result + ((getComTypeId() == null) ? 0 : getComTypeId().hashCode());
        result = prime * result + ((getComName() == null) ? 0 : getComName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getDiscount() == null) ? 0 : getDiscount().hashCode());
        result = prime * result + ((getPositon() == null) ? 0 : getPositon().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getaId() == null) ? 0 : getaId().hashCode());
        result = prime * result + ((getSoftChannelId() == null) ? 0 : getSoftChannelId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cmdyId=").append(cmdyId);
        sb.append(", viptypeId=").append(viptypeId);
        sb.append(", comTypeId=").append(comTypeId);
        sb.append(", comName=").append(comName);
        sb.append(", price=").append(price);
        sb.append(", discount=").append(discount);
        sb.append(", positon=").append(positon);
        sb.append(", createTime=").append(createTime);
        sb.append(", aId=").append(aId);
        sb.append(", softChannelId=").append(softChannelId);
        sb.append(", name=").append(name);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}