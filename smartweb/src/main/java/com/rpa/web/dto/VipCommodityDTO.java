package com.rpa.web.dto;

import com.rpa.web.pojo.VipCommodityPO;

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
public class VipCommodityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cmdyId;
    private String name;
    private String comTypeName;
    private Integer days;
    private String comName;
    private String description;
    private Integer price;
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
        dto.setDiscount(po.getDiscount());
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

    public Integer getCmdyId() {
        return cmdyId;
    }

    public void setCmdyId(Integer cmdyId) {
        this.cmdyId = cmdyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComTypeName() {
        return comTypeName;
    }

    public void setComTypeName(String comTypeName) {
        this.comTypeName = comTypeName;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getShowDiscount() {
        return showDiscount;
    }

    public void setShowDiscount(String showDiscount) {
        this.showDiscount = showDiscount;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIstop() {
        return istop;
    }

    public void setIstop(Byte istop) {
        this.istop = istop;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
