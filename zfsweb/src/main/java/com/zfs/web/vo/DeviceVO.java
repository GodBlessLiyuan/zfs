package com.zfs.web.vo;

import com.zfs.common.pojo.DevicePO;
import lombok.Data;

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
@Data
public class DeviceVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Date createTime;
    private Date updateTime;
    private Integer softChannelId;
    private String chanName;
    private String versionName;
    private Byte buildVersion;
    private String manufacturer;
    private String androidModel;

    /**
     * PO 转 DTO
     *
     * @param po PO
     * @return DTO
     */
    public static DeviceVO convert(DevicePO po) {
        DeviceVO dto = new DeviceVO();

        dto.setCreateTime(po.getCreateTime());
        dto.setChanName(po.getChanName());
        dto.setSoftChannelId(po.getSoftChannelId());
        dto.setUpdateTime(po.getUpdateTime());
        dto.setAndroidModel(po.getAndroidmodel());
        dto.setBuildVersion(po.getBuildversion());
        dto.setVersionName(po.getVersionname());
        dto.setManufacturer(po.getManufacturer());

        return dto;
    }

    /**
     * PO 批量转 DTO
     *
     * @param pos POs
     * @return DTOs
     */
    public static List<DeviceVO> convert(List<DevicePO> pos) {
        List<DeviceVO> dtos = new ArrayList<>();

        for (DevicePO po : pos) {
            dtos.add(DeviceVO.convert(po));
        }

        return dtos;
    }
}
