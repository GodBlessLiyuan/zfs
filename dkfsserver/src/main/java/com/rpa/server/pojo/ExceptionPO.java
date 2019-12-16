package com.rpa.server.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_exception
 * @author 
 */
public class ExceptionPO implements Serializable {
    private Integer exceptionid;

    private Long deviceId;

    /**
     * android系统的版本号
     */
    private Byte buildversion;

    private Integer versioncode;

    private String androidmodel;

    private String pkg;

    private String buildrelease;

    private Date createTime;

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

    public Byte getBuildversion() {
        return buildversion;
    }

    public void setBuildversion(Byte buildversion) {
        this.buildversion = buildversion;
    }

    public Integer getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(Integer versioncode) {
        this.versioncode = versioncode;
    }

    public String getAndroidmodel() {
        return androidmodel;
    }

    public void setAndroidmodel(String androidmodel) {
        this.androidmodel = androidmodel;
    }

    public String getPkg() {
        return pkg;
    }

    public void setPkg(String pkg) {
        this.pkg = pkg;
    }

    public String getBuildrelease() {
        return buildrelease;
    }

    public void setBuildrelease(String buildrelease) {
        this.buildrelease = buildrelease;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
            && (this.getBuildversion() == null ? other.getBuildversion() == null : this.getBuildversion().equals(other.getBuildversion()))
            && (this.getVersioncode() == null ? other.getVersioncode() == null : this.getVersioncode().equals(other.getVersioncode()))
            && (this.getAndroidmodel() == null ? other.getAndroidmodel() == null : this.getAndroidmodel().equals(other.getAndroidmodel()))
            && (this.getPkg() == null ? other.getPkg() == null : this.getPkg().equals(other.getPkg()))
            && (this.getBuildrelease() == null ? other.getBuildrelease() == null : this.getBuildrelease().equals(other.getBuildrelease()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getError() == null ? other.getError() == null : this.getError().equals(other.getError()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExceptionid() == null) ? 0 : getExceptionid().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getBuildversion() == null) ? 0 : getBuildversion().hashCode());
        result = prime * result + ((getVersioncode() == null) ? 0 : getVersioncode().hashCode());
        result = prime * result + ((getAndroidmodel() == null) ? 0 : getAndroidmodel().hashCode());
        result = prime * result + ((getPkg() == null) ? 0 : getPkg().hashCode());
        result = prime * result + ((getBuildrelease() == null) ? 0 : getBuildrelease().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
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
        sb.append(", buildversion=").append(buildversion);
        sb.append(", versioncode=").append(versioncode);
        sb.append(", androidmodel=").append(androidmodel);
        sb.append(", pkg=").append(pkg);
        sb.append(", buildrelease=").append(buildrelease);
        sb.append(", createTime=").append(createTime);
        sb.append(", error=").append(error);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}