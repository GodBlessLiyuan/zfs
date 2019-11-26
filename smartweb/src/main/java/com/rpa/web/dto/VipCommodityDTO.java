package com.rpa.web.dto;

import com.rpa.web.pojo.VipCommodityPO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 18:58
 * @description: 商品列表
 * @version: 1.0
 */
@Data
public class VipCommodityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cmdyId;
    private String name;
    private String comTypeName;
    private Integer days;
    private String comName;
    private String description;
    private String price;
    private String showDiscount;
    private Float discount;
    private Byte status;
    private Byte istop;
    private Date createTime;
    private String username;

    /**
     * PO 转 DTO
     *
     * @param po PO
     * @return DTO
     */
    public static VipCommodityDTO convert(VipCommodityPO po) {
        VipCommodityDTO dto = new VipCommodityDTO();

        dto.setCmdyId(po.getCmdyId());
        dto.setName(po.getName());
        dto.setComTypeName(po.getComTypeName());
        dto.setDays(po.getDays());
        dto.setComName(po.getComName());
        dto.setDescription(po.getDescription());
        dto.setPrice(po.getPrice());
        dto.setShowDiscount(po.getShowDiscount());
        if (null != po.getDiscount()) {
            dto.setDiscount(po.getDiscount() / 100F);
        }
        dto.setStatus(po.getStatus());
        dto.setIstop(po.getIstop());
        dto.setCreateTime(po.getCreateTime());
        dto.setUsername(po.getUsername());

        return dto;
    }

    /**
     * PO 批量转 DTO
     *
     * @param pos POs
     * @return DTOs
     */
    public static List<VipCommodityDTO> convert(List<VipCommodityPO> pos) {
        List<VipCommodityDTO> dtos = new ArrayList<>();

        for (VipCommodityPO po : pos) {
            dtos.add(VipCommodityDTO.convert(po));
        }

        return dtos;
    }
}
