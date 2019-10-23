package com.rpa.web.dto;

import com.rpa.web.domain.WhiteDeviceDO;
import lombok.Data;

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
@Data
public class WhiteDeviceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long deviceId;
    private Date createTime;
    private String imei;
    private String extra;

    /**
     * do 转 dtos
     *
     * @param d
     * @return
     */
    public static WhiteDeviceDTO convert(WhiteDeviceDO d) {
        WhiteDeviceDTO dto = new WhiteDeviceDTO();

        dto.setDeviceId(d.getDeviceId());
        dto.setExtra(d.getExtra());
        dto.setImei(d.getImei());
        dto.setCreateTime(d.getCreateTime());

        return dto;
    }

    /**
     * dos 转 dtos
     *
     * @param dos
     * @return
     */
    public static List<WhiteDeviceDTO> convert(List<WhiteDeviceDO> dos) {
        List<WhiteDeviceDTO> dtos = new ArrayList<>();

        for (WhiteDeviceDO d : dos) {
            dtos.add(WhiteDeviceDTO.convert(d));
        }

        return dtos;
    }
}
