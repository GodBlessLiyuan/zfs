package com.rpa.web.dto;

import com.rpa.web.pojo.DevicePO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 11:41
 * @description: 设备DTO
 * @version: 1.0
 */
public class DeviceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date createTime;
    private Date updateTime;
    private Integer softChannelId;
    private String chanName;
    private Integer versionCode;
    private Byte buildVersion;
    private String manufacturer;
    private String androidModel;

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
    public static DeviceDTO convert(DevicePO po) {
        DeviceDTO dto = new DeviceDTO();

        dto.setCreateTime(po.getCreateTime());
        dto.setChanName(po.getChanName());
        dto.setSoftChannelId(po.getSoftChannelId());
        dto.setUpdateTime(po.getUpdateTime());
        dto.setAndroidModel(po.getAndroidmodel());
        dto.setBuildVersion(po.getBuildversion());
        dto.setVersionCode(po.getVersioncode());
        dto.setManufacturer(po.getManufacturer());

        return dto;
    }

    /**
     * PO 批量转 DTO
     *
     * @param pos POs
     * @return DTOs
     */
    public static List<DeviceDTO> convert(List<DevicePO> pos) {
        List<DeviceDTO> dtos = new ArrayList<>();

        for (DevicePO po : pos) {
            dtos.add(DeviceDTO.convert(po));
        }

        return dtos;
    }
}
