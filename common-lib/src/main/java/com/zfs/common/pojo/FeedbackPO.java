package com.zfs.common.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_feedback
 * @author 
 */
public class FeedbackPO implements Serializable {
    private Integer feedbackId;

    private Long userId;

    private Long deviceId;

    private Integer userDeviceId;

    private String context;

    private String contact;

    private Date createTime;

    private String manufacturer;

    private String androidmodel;

    /**
     * android系统的版本号
     */
    private Byte buildversion;

    private Integer versioncode;

    private String buildrelease;

    private String url1;

    private String url2;

    private String url3;

    private static final long serialVersionUID = 1L;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getUserDeviceId() {
        return userDeviceId;
    }

    public void setUserDeviceId(Integer userDeviceId) {
        this.userDeviceId = userDeviceId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getBuildrelease() {
        return buildrelease;
    }

    public void setBuildrelease(String buildrelease) {
        this.buildrelease = buildrelease;
    }

    public String getUrl1() {
        return url1;
    }

    public void setUrl1(String url1) {
        this.url1 = url1;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getUrl3() {
        return url3;
    }

    public void setUrl3(String url3) {
        this.url3 = url3;
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
        FeedbackPO other = (FeedbackPO) that;
        return (this.getFeedbackId() == null ? other.getFeedbackId() == null : this.getFeedbackId().equals(other.getFeedbackId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getDeviceId() == null ? other.getDeviceId() == null : this.getDeviceId().equals(other.getDeviceId()))
            && (this.getUserDeviceId() == null ? other.getUserDeviceId() == null : this.getUserDeviceId().equals(other.getUserDeviceId()))
            && (this.getContext() == null ? other.getContext() == null : this.getContext().equals(other.getContext()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getManufacturer() == null ? other.getManufacturer() == null : this.getManufacturer().equals(other.getManufacturer()))
            && (this.getAndroidmodel() == null ? other.getAndroidmodel() == null : this.getAndroidmodel().equals(other.getAndroidmodel()))
            && (this.getBuildversion() == null ? other.getBuildversion() == null : this.getBuildversion().equals(other.getBuildversion()))
            && (this.getVersioncode() == null ? other.getVersioncode() == null : this.getVersioncode().equals(other.getVersioncode()))
            && (this.getBuildrelease() == null ? other.getBuildrelease() == null : this.getBuildrelease().equals(other.getBuildrelease()))
            && (this.getUrl1() == null ? other.getUrl1() == null : this.getUrl1().equals(other.getUrl1()))
            && (this.getUrl2() == null ? other.getUrl2() == null : this.getUrl2().equals(other.getUrl2()))
            && (this.getUrl3() == null ? other.getUrl3() == null : this.getUrl3().equals(other.getUrl3()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFeedbackId() == null) ? 0 : getFeedbackId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDeviceId() == null) ? 0 : getDeviceId().hashCode());
        result = prime * result + ((getUserDeviceId() == null) ? 0 : getUserDeviceId().hashCode());
        result = prime * result + ((getContext() == null) ? 0 : getContext().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getManufacturer() == null) ? 0 : getManufacturer().hashCode());
        result = prime * result + ((getAndroidmodel() == null) ? 0 : getAndroidmodel().hashCode());
        result = prime * result + ((getBuildversion() == null) ? 0 : getBuildversion().hashCode());
        result = prime * result + ((getVersioncode() == null) ? 0 : getVersioncode().hashCode());
        result = prime * result + ((getBuildrelease() == null) ? 0 : getBuildrelease().hashCode());
        result = prime * result + ((getUrl1() == null) ? 0 : getUrl1().hashCode());
        result = prime * result + ((getUrl2() == null) ? 0 : getUrl2().hashCode());
        result = prime * result + ((getUrl3() == null) ? 0 : getUrl3().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", feedbackId=").append(feedbackId);
        sb.append(", userId=").append(userId);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", userDeviceId=").append(userDeviceId);
        sb.append(", context=").append(context);
        sb.append(", contact=").append(contact);
        sb.append(", createTime=").append(createTime);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", androidmodel=").append(androidmodel);
        sb.append(", buildversion=").append(buildversion);
        sb.append(", versioncode=").append(versioncode);
        sb.append(", buildrelease=").append(buildrelease);
        sb.append(", url1=").append(url1);
        sb.append(", url2=").append(url2);
        sb.append(", url3=").append(url3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}