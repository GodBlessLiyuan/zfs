package com.rpa.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_register_user
 * @author 
 */
public class RegisterUserPO implements Serializable {
    private Long reUid;

    private String phone;

    private Date createTime;

    private String chanName;

    private String versionname;

    private String buildrelease;

    private String manufacturer;

    private String androidmodel;

    private static final long serialVersionUID = 1L;

    public Long getReUid() {
        return reUid;
    }

    public void setReUid(Long reUid) {
        this.reUid = reUid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getChanName() {
        return chanName;
    }

    public void setChanName(String chanName) {
        this.chanName = chanName;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getBuildrelease() {
        return buildrelease;
    }

    public void setBuildrelease(String buildrelease) {
        this.buildrelease = buildrelease;
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
        RegisterUserPO other = (RegisterUserPO) that;
        return (this.getReUid() == null ? other.getReUid() == null : this.getReUid().equals(other.getReUid()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getChanName() == null ? other.getChanName() == null : this.getChanName().equals(other.getChanName()))
            && (this.getVersionname() == null ? other.getVersionname() == null : this.getVersionname().equals(other.getVersionname()))
            && (this.getBuildrelease() == null ? other.getBuildrelease() == null : this.getBuildrelease().equals(other.getBuildrelease()))
            && (this.getManufacturer() == null ? other.getManufacturer() == null : this.getManufacturer().equals(other.getManufacturer()))
            && (this.getAndroidmodel() == null ? other.getAndroidmodel() == null : this.getAndroidmodel().equals(other.getAndroidmodel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getReUid() == null) ? 0 : getReUid().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getChanName() == null) ? 0 : getChanName().hashCode());
        result = prime * result + ((getVersionname() == null) ? 0 : getVersionname().hashCode());
        result = prime * result + ((getBuildrelease() == null) ? 0 : getBuildrelease().hashCode());
        result = prime * result + ((getManufacturer() == null) ? 0 : getManufacturer().hashCode());
        result = prime * result + ((getAndroidmodel() == null) ? 0 : getAndroidmodel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", reUid=").append(reUid);
        sb.append(", phone=").append(phone);
        sb.append(", createTime=").append(createTime);
        sb.append(", chanName=").append(chanName);
        sb.append(", versionname=").append(versionname);
        sb.append(", buildrelease=").append(buildrelease);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", androidmodel=").append(androidmodel);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}