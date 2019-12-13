package com.rpa.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_avatar
 * @author 
 */
public class AvatarPO implements Serializable {
    private Long avatarId;

    private String versionName;

    private Integer versionCode;

    private Date createTime;

    private Date updateTime;

    private String url;

    /**
     * 1 未发布  2 发布
     */
    private Byte status;

    /**
     * 1 不同更新 2 强制更新
     */
    private Byte updateType;

    private Integer size;

    private String extra;

    private String context;

    private Date publishTime;

    /**
     * 1 未删除  2删除
     */
    private Byte dr;

    private String md5;

    private Integer aId;

    private static final long serialVersionUID = 1L;

    public Long getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getUpdateType() {
        return updateType;
    }

    public void setUpdateType(Byte updateType) {
        this.updateType = updateType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Byte getDr() {
        return dr;
    }

    public void setDr(Byte dr) {
        this.dr = dr;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
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
        AvatarPO other = (AvatarPO) that;
        return (this.getAvatarId() == null ? other.getAvatarId() == null : this.getAvatarId().equals(other.getAvatarId()))
            && (this.getVersionName() == null ? other.getVersionName() == null : this.getVersionName().equals(other.getVersionName()))
            && (this.getVersionCode() == null ? other.getVersionCode() == null : this.getVersionCode().equals(other.getVersionCode()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUpdateType() == null ? other.getUpdateType() == null : this.getUpdateType().equals(other.getUpdateType()))
            && (this.getSize() == null ? other.getSize() == null : this.getSize().equals(other.getSize()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getContext() == null ? other.getContext() == null : this.getContext().equals(other.getContext()))
            && (this.getPublishTime() == null ? other.getPublishTime() == null : this.getPublishTime().equals(other.getPublishTime()))
            && (this.getDr() == null ? other.getDr() == null : this.getDr().equals(other.getDr()))
            && (this.getMd5() == null ? other.getMd5() == null : this.getMd5().equals(other.getMd5()))
            && (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAvatarId() == null) ? 0 : getAvatarId().hashCode());
        result = prime * result + ((getVersionName() == null) ? 0 : getVersionName().hashCode());
        result = prime * result + ((getVersionCode() == null) ? 0 : getVersionCode().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUpdateType() == null) ? 0 : getUpdateType().hashCode());
        result = prime * result + ((getSize() == null) ? 0 : getSize().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        result = prime * result + ((getContext() == null) ? 0 : getContext().hashCode());
        result = prime * result + ((getPublishTime() == null) ? 0 : getPublishTime().hashCode());
        result = prime * result + ((getDr() == null) ? 0 : getDr().hashCode());
        result = prime * result + ((getMd5() == null) ? 0 : getMd5().hashCode());
        result = prime * result + ((getaId() == null) ? 0 : getaId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", avatarId=").append(avatarId);
        sb.append(", versionName=").append(versionName);
        sb.append(", versionCode=").append(versionCode);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", url=").append(url);
        sb.append(", status=").append(status);
        sb.append(", updateType=").append(updateType);
        sb.append(", size=").append(size);
        sb.append(", extra=").append(extra);
        sb.append(", context=").append(context);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", dr=").append(dr);
        sb.append(", md5=").append(md5);
        sb.append(", aId=").append(aId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}