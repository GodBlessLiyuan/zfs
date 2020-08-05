package com.zfs.web.vo;

import com.zfs.common.bo.OtherAppBO;
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
public class OtherAppVO implements Serializable {

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
     * @param bo PO
     * @return DTO
     */
    public static OtherAppVO convert(OtherAppBO bo) {
        OtherAppVO vo = new OtherAppVO();

        vo.setId(bo.getoId());
        vo.setName(bo.getoName());
        vo.setExtra(bo.getExtra());
        vo.setIconUrl(OtherAppVO.publicPath + bo.getIconUrl());
        vo.setDownloadType(bo.getDownloadType());
        if (bo.getDownloadType() == 1) {
            vo.setAppUrl(OtherAppVO.publicPath + bo.getAppUrl());
        } else {
            vo.setAppUrl(bo.getAppUrl());
        }
        vo.setUsername(bo.getUsername());

        return vo;
    }

    /**
     * PO 批量转 DTO
     *
     * @param bos POs
     * @return DTOs
     */
    public static List<OtherAppVO> convert(List<OtherAppBO> bos) {
        List<OtherAppVO> vos = new ArrayList<>();

        for (OtherAppBO bo : bos) {
            vos.add(OtherAppVO.convert(bo));
        }

        return vos;
    }

    @Value("${file.publicPath}")
    private void setPublicPath(String publicPath) {
        OtherAppVO.publicPath = publicPath;
    }
}
