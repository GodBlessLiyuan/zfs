package com.zfs.web.vo;

import com.zfs.common.bo.ComTypeBO;
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

    public static ComTypeVO convert(ComTypeBO bo) {
        ComTypeVO vo = new ComTypeVO();

        vo.setComTypeId(bo.getComTypeId());
        vo.setName(bo.getName());
        vo.setDays(bo.getDays());
        vo.setExtra(bo.getExtra());
        vo.setCreateTime(bo.getCreateTime());
        vo.setUpdateTime(bo.getUpdateTime());
        vo.setUsername(bo.getUsername());

        return vo;
    }

    public static List<ComTypeVO> convert(List<ComTypeBO> bos) {
        List<ComTypeVO> dtos = new ArrayList<>();

        for (ComTypeBO bo : bos) {
            dtos.add(ComTypeVO.convert(bo));
        }

        return dtos;
    }
}
