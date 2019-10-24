package com.rpa.web.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_device
 * @author 
 */
public class DevicePO implements Serializable {
    private Long deviceId;

    private String utdid;

    private String androidid;

    /**
     * android系统的版本号
     */
    private Byte buildversion;

    private Integer softChannelId;

    private String chanName;

    private Date createTime;

    private Date updateTime;

    private Integer versioncode;

    private String manufacturer;

    private String androidmodel;

    private String uuid;

    private String buildrelease;

    private static final long serialVersionUID = 1L;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getUtdid() {
        return utdid;
    }

    public void setUtdid(String utdid) {
        this.utdid = utdid;
    }

    public String getAndroidid() {
        return androidid;
    }

    public void setAndroidid(String androidid) {
        this.androidid = androidid;
    }

    public Byte getBuildversion() {
        return buildversion;
    }

    public void setBuildversion(Byte buildversion) {
        this.buildversion = buildversion;
    }

    public Integer getSoftChannelId() {
        return softChannelId;
    }

    public void setSoftChannelId(Integer softChannelId) {
        this.softChannelId = softChannelId;
    }

    public String getChanName() {
        return chanName;
    }

    public void setChanName(String chanName) {
        this.chanName = chanName;
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

    public Integer getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(Integer versioncode) {
        this.versioncode = versioncode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAndroidmodel() {
        return androidmodel;
    }

    public void setAndroidmodel(String androidmodel) {
        this.androidmodel = androidmodel;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getBuildrelease() {
        return buildrelease;
    }

    public void setBuildrelease(String buildrelease) {
        this.buildrelease = buildrelease;
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
        DevicePO other = (DevicePO) that;
        return (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getUtdid() == null ? other.getUtdid() == null : this.getUtdid().equals(other.getUtdid()))
            && (this.getAndroidid() == null ? other.getAndroidid() == null : this.getAndroidid().equals(other.getAndroidid()))
            && (this.getBuildversion() == null ? other.getBuildversion() == null : this.getBuildversion().equals(other.getBuildversion()))
            && (this.getSoftChannelId() == null ? other.getSoftChannelId() == null : this.getSoftChannelId().equals(other.getSoftChannelId()))
            && (this.getChanName() == null ? other.getChanName() == null : this.getChanName().equals(other.getChanName()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getVersioncode() == null ? other.getVersioncode() == null : this.getVersioncode().equals(other.getVersioncode()))
            && (this.getManufacturer() == null ? other.getManufacturer() == null : this.getManufacturer().equals(other.getManufacturer()))
            && (this.getAndroidmodel() == null ? other.getAndroidmodel() == null : this.getAndroidmodel().equals(other.getAndroidmodel()))
            && (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getBuildrelease() == null ? other.getBuildrelease() == null : this.getBuildrelease().equals(other.getBuildrelease()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getUtdid() == null) ? 0 : getUtdid().hashCode());
        result = prime * result + ((getAndroidid() == null) ? 0 : getAndroidid().hashCode());
        result = prime * result + ((getBuildversion() == null) ? 0 : getBuildversion().hashCode());
        result = prime * result + ((getSoftChannelId() == null) ? 0 : getSoftChannelId().hashCode());
        result = prime * result + ((getChanName() == null) ? 0 : getChanName().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getVersioncode() == null) ? 0 : getVersioncode().hashCode());
        result = prime * result + ((getManufacturer() == null) ? 0 : getManufacturer().hashCode());
        result = prime * result + ((getAndroidmodel() == null) ? 0 : getAndroidmodel().hashCode());
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getBuildrelease() == null) ? 0 : getBuildrelease().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deviceId=").append(deviceId);
        sb.append(", utdid=").append(utdid);
        sb.append(", androidid=").append(androidid);
        sb.append(", buildversion=").append(buildversion);
        sb.append(", softChannelId=").append(softChannelId);
        sb.append(", chanName=").append(chanName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", versioncode=").append(versioncode);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", androidmodel=").append(androidmodel);
        sb.append(", uuid=").append(uuid);
        sb.append(", buildrelease=").append(buildrelease);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}