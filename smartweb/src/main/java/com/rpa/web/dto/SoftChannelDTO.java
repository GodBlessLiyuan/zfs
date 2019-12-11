package com.rpa.web.dto;

import com.rpa.common.pojo.SoftChannelPO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 8:48
 * @description: 渠道信息
 * @version: 1.0
 */
public class SoftChannelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer softChannelId;
    private String name;
    private String extra;
    private Date createTime;
    private Date updateTime;

    public Integer getSoftChannelId() {
        return softChannelId;
    }

    public void setSoftChannelId(Integer softChannelId) {
        this.softChannelId = softChannelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * PO 转 DTO
     *
     * @param po PO
     * @return DTO
     */
    public static SoftChannelDTO convert(SoftChannelPO po) {
        SoftChannelDTO dto = new SoftChannelDTO();

        dto.setSoftChannelId(po.getSoftChannelId());
        dto.setName(po.getName());
        dto.setExtra(po.getExtra());
        dto.setCreateTime(po.getCreateTime());
        dto.setUpdateTime(po.getUpdateTime());

        return dto;
    }

    /**
     * PO 批量转 DTO
     *
     * @param pos POs
     * @return DTOs
     */
    public static List<SoftChannelDTO> convert(List<SoftChannelPO> pos) {
        List<SoftChannelDTO> dtos = new ArrayList<>();

        for (SoftChannelPO po : pos) {
            dtos.add(SoftChannelDTO.convert(po));
        }

        return dtos;
    }
}
