package com.rpa.web.vo;

import com.rpa.common.bo.UserGiftsBO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/3 16:52
 * @description: 新用户送会员
 * @version: 1.0
 */
@Data
public class UserGiftsVO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer nugId;
    private Integer comTypeId;
    private String comTypeName;
    private Integer days;
    private Byte status;
    private Date createTime;
    private Date updateTime;
    private String username;

    public static UserGiftsVO convert(UserGiftsBO bo) {
        UserGiftsVO dto = new UserGiftsVO();

        dto.setNugId(bo.getNugId());
        dto.setComTypeId(bo.getComTypeId());
        dto.setComTypeName(bo.getComTypeName());
        dto.setDays(bo.getDays());
        dto.setStatus(bo.getStatus());
        dto.setCreateTime(bo.getCreateTime());
        dto.setUsername(bo.getUsername());

        return dto;
    }

    public static List<UserGiftsVO> convert(List<UserGiftsBO> bos) {
        List<UserGiftsVO> dtos = new ArrayList<>();

        for (UserGiftsBO bo : bos) {
            dtos.add(UserGiftsVO.convert(bo));
        }

        return dtos;
    }
}
