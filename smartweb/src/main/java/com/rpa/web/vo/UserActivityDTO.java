package com.rpa.web.vo;

import com.rpa.common.bo.UserActivityBO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 11:05
 * @description: 活动赠送记录
 * @version: 1.0
 */
@Data
public class UserActivityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer uAId;
    private String phone;
    private Date createTime;
    private String comTypeName;
    private Integer days;
    private Integer source;
    private Byte status;
    private String url;
    private String operator;

    public static UserActivityDTO convert(UserActivityBO bo) {
        UserActivityDTO dto = new UserActivityDTO();

        dto.setPhone(bo.getPhone());
        dto.setCreateTime(bo.getCreateTime());
        dto.setComTypeName(bo.getComTypeName());
        dto.setDays(bo.getDays());
        dto.setSource(bo.getSource());
        dto.setStatus(bo.getStatus());

        return dto;
    }

    public static List<UserActivityDTO> convert(List<UserActivityBO> dos) {
        List<UserActivityDTO> dtos = new ArrayList<>();

        for (UserActivityBO d : dos) {
            dtos.add(UserActivityDTO.convert(d));
        }

        return dtos;
    }
}
