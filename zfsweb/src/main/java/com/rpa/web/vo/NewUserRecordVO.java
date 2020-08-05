package com.rpa.web.vo;

import com.rpa.common.bo.NewUserRecordBO;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/3 17:58
 * @description: 新用户赠送记录
 * @version: 1.0
 */
@Data
public class NewUserRecordVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer nurId;
    private Date createTime;
    private String phone;
    private String comTypeName;
    private Integer days;

    public static NewUserRecordVO convert(NewUserRecordBO bo) {
        NewUserRecordVO dto = new NewUserRecordVO();

        dto.setNurId(bo.getNurId());
        dto.setPhone(bo.getPhone());
        dto.setCreateTime(bo.getCreateTime());
        dto.setComTypeName(bo.getComTypeName());
        dto.setDays(bo.getDays());
        return dto;
    }

    public static List<NewUserRecordVO> convert(List<NewUserRecordBO> bos) {
        List<NewUserRecordVO> dtos = new ArrayList<>(bos.size());

        for (NewUserRecordBO po : bos) {
            dtos.add(NewUserRecordVO.convert(po));
        }

        return dtos;
    }
}
