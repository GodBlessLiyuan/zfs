package com.rpa.web.vo;

import com.rpa.common.bo.ComTypeBO;
import com.rpa.common.pojo.ComTypePO;
import lombok.Data;

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
@Data
public class ComTypeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer comTypeId;
    private String name;
    private Integer days;
    private String extra;
    private Date createTime;
    private Date updateTime;
    private String username;

    /**
     * BO 转 VO
     *
     * @param bo PO
     * @return VO
     */
    public static ComTypeVO convert(ComTypeBO bo) {
        ComTypeVO dto = new ComTypeVO();

        dto.setComTypeId(bo.getComTypeId());
        dto.setName(bo.getName());
        dto.setDays(bo.getDays());
        dto.setExtra(bo.getExtra());
        dto.setCreateTime(bo.getCreateTime());
        dto.setUpdateTime(bo.getUpdateTime());
        dto.setUsername(bo.getUsername());

        return dto;
    }

    /**
     * PO 批量转 DTO
     *
     * @param bos POs
     * @return DTOs
     */
    public static List<ComTypeVO> convert(List<ComTypeBO> bos) {
        List<ComTypeVO> dtos = new ArrayList<>();

        for (ComTypeBO bo : bos) {
            dtos.add(ComTypeVO.convert(bo));
        }

        return dtos;
    }
}
