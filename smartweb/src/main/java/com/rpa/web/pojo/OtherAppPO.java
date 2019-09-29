package com.rpa.web.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_other_app
 * @author 
 */
public class OtherAppPO implements Serializable {
    private Integer oId;

    private String oName;

    private String iconUrl;

    private String appUrl;

    /**
     * 1 直接下载  2应用市场
     */
    private Byte downloadType;

    private Integer aId;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public Byte getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(Byte downloadType) {
        this.downloadType = downloadType;
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
        OtherAppPO other = (OtherAppPO) that;
        return (this.getoId() == null ? other.getoId() == null : this.getoId().equals(other.getoId()))
            && (this.getoName() == null ? other.getoName() == null : this.getoName().equals(other.getoName()))
            && (this.getIconUrl() == null ? other.getIconUrl() == null : this.getIconUrl().equals(other.getIconUrl()))
            && (this.getAppUrl() == null ? other.getAppUrl() == null : this.getAppUrl().equals(other.getAppUrl()))
            && (this.getDownloadType() == null ? other.getDownloadType() == null : this.getDownloadType().equals(other.getDownloadType()))
            && (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getoId() == null) ? 0 : getoId().hashCode());
        result = prime * result + ((getoName() == null) ? 0 : getoName().hashCode());
        result = prime * result + ((getIconUrl() == null) ? 0 : getIconUrl().hashCode());
        result = prime * result + ((getAppUrl() == null) ? 0 : getAppUrl().hashCode());
        result = prime * result + ((getDownloadType() == null) ? 0 : getDownloadType().hashCode());
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
        sb.append(", oId=").append(oId);
        sb.append(", oName=").append(oName);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", appUrl=").append(appUrl);
        sb.append(", downloadType=").append(downloadType);
        sb.append(", aId=").append(aId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}