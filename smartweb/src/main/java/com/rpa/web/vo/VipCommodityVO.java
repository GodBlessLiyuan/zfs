package com.rpa.web.vo;

import com.rpa.common.bo.VipcommodityBO;
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
public class VipCommodityVO implements Serializable {

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
     * @param bo PO
     * @return DTO
     */
    public static VipCommodityVO convert(VipcommodityBO bo) {
        VipCommodityVO vo = new VipCommodityVO();

        vo.setCmdyId(bo.getCmdyId());
        vo.setName(bo.getName());
        vo.setComTypeName(bo.getComTypeName());
        vo.setDays(bo.getDays());
        vo.setComName(bo.getComName());
        vo.setDescription(bo.getDescription());
        vo.setPrice(bo.getPrice());
        vo.setShowDiscount(bo.getShowDiscount());
        if (null != bo.getDiscount()) {
            vo.setDiscount(bo.getDiscount() / 100F);
        }
        vo.setStatus(bo.getStatus());
        vo.setIstop(bo.getIstop());
        vo.setCreateTime(bo.getCreateTime());
        vo.setUsername(bo.getUsername());

        return vo;
    }

    /**
     * PO 批量转 DTO
     *
     * @param bos POs
     * @return DTOs
     */
    public static List<VipCommodityVO> convert(List<VipcommodityBO> bos) {
        List<VipCommodityVO> vos = new ArrayList<>();

        for (VipcommodityBO bo : bos) {
            vos.add(VipCommodityVO.convert(bo));
        }

        return vos;
    }
}
