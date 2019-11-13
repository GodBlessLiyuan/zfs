package com.rpa.web.dto;

import com.rpa.web.pojo.DevicePO;
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
public class DeviceDTO implements Serializable {

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
    public static DeviceDTO convert(DevicePO po) {
        DeviceDTO dto = new DeviceDTO();

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
    public static List<DeviceDTO> convert(List<DevicePO> pos) {
        List<DeviceDTO> dtos = new ArrayList<>();

        for (DevicePO po : pos) {
            dtos.add(DeviceDTO.convert(po));
        }

        return dtos;
    }
}
