package com.rpa.web.dto;

import com.rpa.web.pojo.WhilteDevicePO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 14:22
 * @description: 测试白名单
 * @version: 1.0
 */
public class WhilteDeviceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long deviceId;
    private Date createTime;
    private String imei;
    private String extra;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * po 转 dtos
     *
     * @param po
     * @return
     */
    public static WhilteDeviceDTO convert(WhilteDevicePO po) {
        WhilteDeviceDTO dto = new WhilteDeviceDTO();

        dto.setDeviceId(po.getDeviceId());
        dto.setExtra(po.getExtra());
        dto.setImei(po.getImei());

        return dto;
    }

    /**
     * pos 转 dtos
     *
     * @param pos
     * @return
     */
    public static List<WhilteDeviceDTO> convert(List<WhilteDevicePO> pos) {
        List<WhilteDeviceDTO> dtos = new ArrayList<>();

        for (WhilteDevicePO po : pos) {
            dtos.add(WhilteDeviceDTO.convert(po));
        }

        return dtos;
    }
}
