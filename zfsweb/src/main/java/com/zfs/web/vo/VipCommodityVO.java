package com.zfs.web.vo;

import com.zfs.common.bo.VipcommodityBO;
import com.zfs.common.pojo.VipcommodityPO;
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
    private Integer comTypeId;
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
    private String commAttr;
    public static VipCommodityVO convert(VipcommodityPO po) {
        VipCommodityVO vo = new VipCommodityVO();

        vo.setCmdyId(po.getCmdyId());
        vo.setName(po.getName());
        vo.setComTypeName(po.getComTypeName());
        vo.setDays(po.getDays());
        vo.setComName(po.getComName());
        vo.setDescription(po.getDescription());
        vo.setPrice(po.getPrice());
        vo.setComTypeId(po.getComTypeId());
        vo.setShowDiscount(po.getShowDiscount());
        if (null != po.getDiscount()) {
            vo.setDiscount(po.getDiscount() / 100F);
        }
        vo.setStatus(po.getStatus());
        vo.setIstop(po.getIstop());
        vo.setCreateTime(po.getCreateTime());

        return vo;
    }

    public static VipCommodityVO convert(VipcommodityBO bo) {
        VipCommodityVO vo = VipCommodityVO.convert((VipcommodityPO) bo);
        vo.setUsername(bo.getUsername());
        if(bo.getCommAttr()==null||bo.getCommAttr()==1){
            vo.setCommAttr("独立商品");
        }
        else if(bo.getCommAttr()==2){
            vo.setCommAttr("通用商品");
        }
        return vo;
    }

    public static List<VipCommodityVO> convert(List<VipcommodityBO> bos) {
        List<VipCommodityVO> vos = new ArrayList<>();

        for (VipcommodityBO bo : bos) {
            vos.add(VipCommodityVO.convert(bo));
        }

        return vos;
    }
}
