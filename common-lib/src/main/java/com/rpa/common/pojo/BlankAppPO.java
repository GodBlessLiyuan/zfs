package com.rpa.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_blank_app
 * @author 
 */
public class BlankAppPO implements Serializable {
    private Long blankId;

    private String packageName;

    private String appName;

    private Date createTime;

    private String extra;

    private Integer aId;

    private static final long serialVersionUID = 1L;

    public Long getBlankId() {
        return blankId;
    }

    public void setBlankId(Long blankId) {
        this.blankId = blankId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        BlankAppPO other = (BlankAppPO) that;
        return (this.getBlankId() == null ? other.getBlankId() == null : this.getBlankId().equals(other.getBlankId()))
            && (this.getPackageName() == null ? other.getPackageName() == null : this.getPackageName().equals(other.getPackageName()))
            && (this.getAppName() == null ? other.getAppName() == null : this.getAppName().equals(other.getAppName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBlankId() == null) ? 0 : getBlankId().hashCode());
        result = prime * result + ((getPackageName() == null) ? 0 : getPackageName().hashCode());
        result = prime * result + ((getAppName() == null) ? 0 : getAppName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        result = prime * result + ((getaId() == null) ? 0 : getaId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", blankId=").append(blankId);
        sb.append(", packageName=").append(packageName);
        sb.append(", appName=").append(appName);
        sb.append(", createTime=").append(createTime);
        sb.append(", extra=").append(extra);
        sb.append(", aId=").append(aId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}