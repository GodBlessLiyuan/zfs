package com.rpa.web.dto;

import com.rpa.web.pojo.ComTypePO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 18:46
 * @description: 产品列表
 * @version: 1.0
 */
public class ComTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer comTypeId;
    private String name;
    private Integer days;
    private String extra;
    private Date createTime;
    private Date updateTime;
    private String username;

    public Integer getComTypeId() {
        return comTypeId;
    }

    public void setComTypeId(Integer comTypeId) {
        this.comTypeId = comTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", comTypeId=").append(comTypeId);
        sb.append(", name=").append(name);
        sb.append(", days=").append(days);
        sb.append(", extra=").append(extra);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * PO 转 DTO
     *
     * @param po PO
     * @return DTO
     */
    public static ComTypeDTO convert(ComTypePO po) {
        ComTypeDTO dto = new ComTypeDTO();

        dto.setComTypeId(po.getComTypeId());
        dto.setName(po.getName());
        dto.setDays(po.getDays());
        dto.setExtra(po.getExtra());
        dto.setCreateTime(po.getCreateTime());
        dto.setUpdateTime(po.getUpdateTime());
        dto.setUsername(po.getUsername());

        return dto;
    }

    /**
     * PO 批量转 DTO
     *
     * @param pos POs
     * @return DTOs
     */
    public static List<ComTypeDTO> convert(List<ComTypePO> pos) {
        List<ComTypeDTO> dtos = new ArrayList<>();

        for (ComTypePO po : pos) {
            dtos.add(ComTypeDTO.convert(po));
        }

        return dtos;
    }
}
