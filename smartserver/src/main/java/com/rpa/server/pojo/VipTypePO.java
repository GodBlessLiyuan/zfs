package com.rpa.server.pojo;

import java.io.Serializable;

/**
 * t_viptype
 * @author 
 */
public class VipTypePO implements Serializable {
    private Integer viptypeId;

    /**
     * 不同vip和年费vip
     */
    private String vipname;

    private static final long serialVersionUID = 1L;

    public Integer getViptypeId() {
        return viptypeId;
    }

    public void setViptypeId(Integer viptypeId) {
        this.viptypeId = viptypeId;
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
        VipTypePO other = (VipTypePO) that;
        return (this.getViptypeId() == null ? other.getViptypeId() == null : this.getViptypeId().equals(other.getViptypeId()))
            && (this.getVipname() == null ? other.getVipname() == null : this.getVipname().equals(other.getVipname()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getViptypeId() == null) ? 0 : getViptypeId().hashCode());
        result = prime * result + ((getVipname() == null) ? 0 : getVipname().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", viptypeId=").append(viptypeId);
        sb.append(", vipname=").append(vipname);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}