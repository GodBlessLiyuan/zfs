package com.rpa.web.dto;

import com.rpa.web.pojo.UserPO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 16:16
 * @description: 用户信息
 * @version: 1.0
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;
    private String username;
    private String phone;
    private Date createTime;

    private String chanName;
    private Integer versionCode;
    private Byte buildVersion;
    private String manufacturer;
    private String androidModel;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    public Byte getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(Byte buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getAndroidModel() {
        return androidModel;
    }

    public void setAndroidModel(String androidModel) {
        this.androidModel = androidModel;
    }

    /**
     * PO 转 DTO
     *
     * @param po PO
     * @return DTO
     */
    public static UserDTO convert(UserPO po) {
        UserDTO dto = new UserDTO();

        dto.setPhone(po.getPhone());
        dto.setCreateTime(po.getCreateTime());
        dto.setChanName(po.getChanName());
        dto.setVersionCode(po.getVersionCode());
        dto.setBuildVersion(po.getBuildVersion());
        dto.setManufacturer(po.getManufacturer());
        dto.setAndroidModel(po.getAndroidModel());

        return dto;
    }

    /**
     * PO 批量转 DTO
     *
     * @param pos POs
     * @return DTOs
     */
    public static List<UserDTO> convert(List<UserPO> pos) {
        List<UserDTO> dtos = new ArrayList<>();

        for (UserPO po : pos) {
            dtos.add(UserDTO.convert(po));
        }

        return dtos;
    }
}
