package com.rpa.web.pojo;

import java.io.Serializable;

/**
 * t_batch_info
 * @author 
 */
public class BatchInfoPO implements Serializable {
    private Integer id;

    private String vipkey;

    private Integer batchId;

    private Byte status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVipkey() {
        return vipkey;
    }

    public void setVipkey(String vipkey) {
        this.vipkey = vipkey;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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
        BatchInfoPO other = (BatchInfoPO) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getVipkey() == null ? other.getVipkey() == null : this.getVipkey().equals(other.getVipkey()))
            && (this.getBatchId() == null ? other.getBatchId() == null : this.getBatchId().equals(other.getBatchId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getVipkey() == null) ? 0 : getVipkey().hashCode());
        result = prime * result + ((getBatchId() == null) ? 0 : getBatchId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", vipkey=").append(vipkey);
        sb.append(", batchId=").append(batchId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}