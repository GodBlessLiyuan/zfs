package com.rpa.server.dto;

import com.rpa.server.pojo.DevicePO;
import lombok.Data;

import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/15 15:36
 * @description: 设备请求参数
 * @version: 1.0
 */
@Data
public class DeviceDTO {
    /**
     * androidid唯一标识
     */
    private String androidid;
    /**
     * 阿里唯一标识
     */
    private String utdid;
    /**
     * 系统版本号
     */
    private Byte osv;
    /**
     * 应用渠道
     */
    private String channel;
    /**
     * 手机厂商
     */
    private String factory;
    /**
     * 手机型号
     */
    private String model;
    /**
     * 应用版本号
     */
    private Integer softv;
    /**
     * 手机编译版本号
     */
    private String buildv;
    /**
     * Android端随机生成的字符串
     */
    private String uuid;
    /**
     * 设备imei或meid
     */
    private List<String> imei;


    /**
     * dto 转 po
     * @param dto
     * @return
     */
    public static DevicePO convertPO(DeviceDTO dto) {
        DevicePO po = new DevicePO();
        DeviceDTO.updatePObyDTO(po, dto);
        return po;
    }

    /**
     * 跟新po值
     *
     * @param po
     * @param dto
     */
    public static void updatePObyDTO(DevicePO po, DeviceDTO dto) {
        if (dto.getAndroidid() != null) {
            po.setAndroidid(dto.getAndroidid());
        }
        if (dto.getUtdid() != null) {
            po.setUtdid(dto.getUtdid());
        }
        if (dto.getOsv() != null) {
            po.setBuildversion(dto.getOsv());
        }
//        po.setSoftChannelId(dto.getChannel()); // Redis 查询
        if (dto.getFactory() != null) {
            po.setManufacturer(dto.getFactory());
        }
        if (dto.getModel() != null) {
            po.setAndroidmodel(dto.getModel());
        }
        if (dto.getSoftv() != null) {
            po.setVersioncode(dto.getSoftv());
        }
        if (dto.getUuid() != null) {
            po.setUuid(dto.getUuid());
        }
    }
}
