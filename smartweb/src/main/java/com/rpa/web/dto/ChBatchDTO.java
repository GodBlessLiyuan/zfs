package com.rpa.web.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * t_ch_batch
 * @author 
 */
public class ChBatchDTO implements Serializable {

    private Integer batchId;

    private String chanNickname;

    private String chanName;

    private Integer chanId;

    private Date createTime;

    private String creater;

    private String comTypeName;

    private Integer comTypeId;

    private Integer num;

    private Byte activity;

    private Byte nonActivity;

    private String extra;

    private Byte status;

    private Date updateTime;

    private String operator;

    private static final long serialVersionUID = 1L;

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getChanNickname() {
        return chanNickname;
    }

    public void setChanNickname(String chanNickname) {
        this.chanNickname = chanNickname;
    }

    public String getChanName() {
        return chanName;
    }

    public void setChanName(String chanName) {
        this.chanName = chanName;
    }

    public Integer getChanId() {
        return chanId;
    }

    public void setChanId(Integer chanId) {
        this.chanId = chanId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getComTypeName() {
        return comTypeName;
    }

    public void setComTypeName(String comTypeName) {
        this.comTypeName = comTypeName;
    }

    public Integer getComTypeId() {
        return comTypeId;
    }

    public void setComTypeId(Integer comTypeId) {
        this.comTypeId = comTypeId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Byte getActivity() {
        return activity;
    }

    public void setActivity(Byte activity) {
        this.activity = activity;
    }

    public Byte getNonActivity() {
        return nonActivity;
    }

    public void setNonActivity(Byte nonActivity) {
        this.nonActivity = nonActivity;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChBatchDTO that = (ChBatchDTO) o;
        return Objects.equals(chanNickname, that.chanNickname) &&
                Objects.equals(chanName, that.chanName) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(creater, that.creater) &&
                Objects.equals(comTypeName, that.comTypeName) &&
                Objects.equals(num, that.num) &&
                Objects.equals(activity, that.activity) &&
                Objects.equals(nonActivity, that.nonActivity) &&
                Objects.equals(extra, that.extra) &&
                Objects.equals(status, that.status) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(operator, that.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chanNickname, chanName, createTime, comTypeName, num, activity, nonActivity, extra, status, updateTime, operator);
    }

    @Override
    public String toString() {
        return "ChBatchDTO{" +
                "chanNickname='" + chanNickname + '\'' +
                ", chanName='" + chanName + '\'' +
                ", createTime=" + createTime +
                ", comTypeName='" + comTypeName + '\'' +
                ", num=" + num +
                ", activity=" + activity +
                ", nonActivity=" + nonActivity +
                ", extra='" + extra + '\'' +
                ", status=" + status +
                ", updateTime=" + updateTime +
                ", username='" + operator + '\'' +
                '}';
    }
}