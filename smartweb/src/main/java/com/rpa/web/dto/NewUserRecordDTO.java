package com.rpa.web.dto;

import com.rpa.web.pojo.NewUserRecordPO;

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
public class NewUserRecordDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer nurId;
    private Date createTime;
    private String phone;
    private String comTypeName;
    private Integer days;

    public Integer getNurId() {
        return nurId;
    }

    public void setNurId(Integer nurId) {
        this.nurId = nurId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    /**
     * po 转 dto
     *
     * @param po
     * @return
     */
    public static NewUserRecordDTO convert(NewUserRecordPO po) {
        NewUserRecordDTO dto = new NewUserRecordDTO();

        dto.setNurId(po.getNurId());
        dto.setPhone(po.getPhone());
        dto.setCreateTime(po.getCreateTime());
        dto.setComTypeName(po.getComTypeName());
        dto.setDays(po.getDays());
        return dto;
    }

    /**
     * pos 转 dtos
     *
     * @param pos
     * @return
     */
    public static List<NewUserRecordDTO> convert(List<NewUserRecordPO> pos) {
        List<NewUserRecordDTO> dtos = new ArrayList<>(pos.size());

        for (NewUserRecordPO po : pos) {
            dtos.add(NewUserRecordDTO.convert(po));
        }

        return dtos;
    }
}
