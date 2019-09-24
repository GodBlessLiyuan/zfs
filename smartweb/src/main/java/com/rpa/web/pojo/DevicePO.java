package com.rpa.web.pojo;

import java.io.Serializable;

/**
 * t_device
 * @author 
 */
public class DevicePO implements Serializable {
    private Long deviceId;

    private String utdid;

    private String androidid;

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
            && (this.getAndroidid() == null ? other.getAndroidid() == null : this.getAndroidid().equals(other.getAndroidid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getUtdid() == null) ? 0 : getUtdid().hashCode());
        result = prime * result + ((getAndroidid() == null) ? 0 : getAndroidid().hashCode());
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}