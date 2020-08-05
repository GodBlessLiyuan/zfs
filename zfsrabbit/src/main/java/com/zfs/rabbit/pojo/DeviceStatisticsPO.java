package com.zfs.rabbit.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_device_statistics
 * @author 
 */
public class DeviceStatisticsPO implements Serializable {
    private Integer sid;

    private Long deviceId;

    private Date visitTime;

    private String ip;

    private static final long serialVersionUID = 1L;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
        DeviceStatisticsPO other = (DeviceStatisticsPO) that;
        return (this.getSid() == null ? other.getSid() == null : this.getSid().equals(other.getSid()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getVisitTime() == null ? other.getVisitTime() == null : this.getVisitTime().equals(other.getVisitTime()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSid() == null) ? 0 : getSid().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getVisitTime() == null) ? 0 : getVisitTime().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", visitTime=").append(visitTime);
        sb.append(", ip=").append(ip);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}