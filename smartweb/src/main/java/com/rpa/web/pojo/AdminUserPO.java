package com.rpa.web.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * t_admin_user
 * @author 
 */
public class AdminUserPO implements Serializable {
    private Integer aId;

    private String username;

    private String password;

    private String email;

    private Byte isLock;

    private Date createTime;

    private Date lastTime;

    /**
     * 1 未删除  2删除
     */
    private Byte dr;

    private Integer roleId;

    private Integer relationAId;

    private String name;

    private String extra;

    private String phone;

    private static final long serialVersionUID = 1L;

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getIsLock() {
        return isLock;
    }

    public void setIsLock(Byte isLock) {
        this.isLock = isLock;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Byte getDr() {
        return dr;
    }

    public void setDr(Byte dr) {
        this.dr = dr;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRelationAId() {
        return relationAId;
    }

    public void setRelationAId(Integer relationAId) {
        this.relationAId = relationAId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        AdminUserPO other = (AdminUserPO) that;
        return (this.getaId() == null ? other.getaId() == null : this.getaId().equals(other.getaId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getIsLock() == null ? other.getIsLock() == null : this.getIsLock().equals(other.getIsLock()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastTime() == null ? other.getLastTime() == null : this.getLastTime().equals(other.getLastTime()))
            && (this.getDr() == null ? other.getDr() == null : this.getDr().equals(other.getDr()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getRelationAId() == null ? other.getRelationAId() == null : this.getRelationAId().equals(other.getRelationAId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getaId() == null) ? 0 : getaId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getIsLock() == null) ? 0 : getIsLock().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastTime() == null) ? 0 : getLastTime().hashCode());
        result = prime * result + ((getDr() == null) ? 0 : getDr().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getRelationAId() == null) ? 0 : getRelationAId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", aId=").append(aId);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", email=").append(email);
        sb.append(", isLock=").append(isLock);
        sb.append(", createTime=").append(createTime);
        sb.append(", lastTime=").append(lastTime);
        sb.append(", dr=").append(dr);
        sb.append(", roleId=").append(roleId);
        sb.append(", relationAId=").append(relationAId);
        sb.append(", name=").append(name);
        sb.append(", extra=").append(extra);
        sb.append(", phone=").append(phone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}