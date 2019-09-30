package com.rpa.web.dto;

import com.rpa.web.pojo.OtherAppPO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 20:01
 * @description: 其他产品
 * @version: 1.0
 */
public class OtherAppDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer oId;
    private String oName;
    private String extra;
    private String iconUrl;
    private Byte downloadType;
    private String appUrl;
    private String username;

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Byte getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(Byte downloadType) {
        this.downloadType = downloadType;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * PO 转 DTO
     *
     * @param po PO
     * @return DTO
     */
    public static OtherAppDTO convert(OtherAppPO po) {
        OtherAppDTO dto = new OtherAppDTO();

        dto.setoId(po.getoId());
        dto.setoName(po.getoName());
        dto.setExtra(po.getExtra());
        dto.setIconUrl(po.getIconUrl());
        dto.setDownloadType(po.getDownloadType());
        dto.setAppUrl(po.getAppUrl());
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
}
