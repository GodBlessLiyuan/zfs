package com.rpa.web.dto;

import com.rpa.web.pojo.OtherAppPO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 20:01
 * @description: 其他产品
 * @version: 1.0
 */
@Data
@Component
public class OtherAppDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private static String publicPath;
    private String extra;
    private String iconUrl;
    private Byte downloadType;
    private String appUrl;
    private String username;

    /**
     * PO 转 DTO
     *
     * @param po PO
     * @return DTO
     */
    public static OtherAppDTO convert(OtherAppPO po) {
        OtherAppDTO dto = new OtherAppDTO();

        dto.setId(po.getoId());
        dto.setName(po.getoName());
        dto.setExtra(po.getExtra());
        dto.setIconUrl(OtherAppDTO.publicPath + po.getIconUrl());
        dto.setDownloadType(po.getDownloadType());
        if (po.getDownloadType() == 1) {
            dto.setAppUrl(OtherAppDTO.publicPath + po.getAppUrl());
        } else {
            dto.setAppUrl(po.getAppUrl());
        }
        dto.setUsername(po.getUsername());

        return dto;
    }

    /**
     * PO 批量转 DTO
     *
     * @param pos POs
     * @return DTOs
     */
    public static List<OtherAppDTO> convert(List<OtherAppPO> pos) {
        List<OtherAppDTO> dtos = new ArrayList<>();

        for (OtherAppPO po : pos) {
            dtos.add(OtherAppDTO.convert(po));
        }

        return dtos;
    }

    @Value("${file.publicPath}")
    private void setPublicPath(String publicPath) {
        OtherAppDTO.publicPath = publicPath;
    }
}
