package com.rpa.web.pojo;

import java.io.Serializable;

/**
 * t_exception
 * @author 
 */
public class ExceptionPO implements Serializable {
    private Integer exceptionid;

    private Long deviceId;

    private String error;

    private static final long serialVersionUID = 1L;

    public Integer getExceptionid() {
        return exceptionid;
    }

    public void setExceptionid(Integer exceptionid) {
        this.exceptionid = exceptionid;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
        ExceptionPO other = (ExceptionPO) that;
        return (this.getExceptionid() == null ? other.getExceptionid() == null : this.getExceptionid().equals(other.getExceptionid()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getError() == null ? other.getError() == null : this.getError().equals(other.getError()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExceptionid() == null) ? 0 : getExceptionid().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getError() == null) ? 0 : getError().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", exceptionid=").append(exceptionid);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", error=").append(error);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}