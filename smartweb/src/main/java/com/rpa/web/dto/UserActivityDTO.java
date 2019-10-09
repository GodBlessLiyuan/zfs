package com.rpa.web.dto;

import com.rpa.web.domain.UserActivityDO;

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
public class UserActivityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String phone;
    private Date createTime;
    private String comTypeName;
    private Integer days;
    private Integer source;
    private Byte status;

    public static UserActivityDTO convert(UserActivityDO d) {
        UserActivityDTO dto = new UserActivityDTO();

        dto.setPhone(d.getPhone());
        dto.setCreateTime(d.getCreateTime());
        dto.setComTypeName(d.getComTypeName());
        dto.setDays(d.getDays());
        dto.setSource(d.getSource());
        dto.setStatus(d.getStatus());

        return dto;
    }

    /**
     * dos 转 dtos
     *
     * @param dos
     * @return
     */
    public static List<UserActivityDTO> convert(List<UserActivityDO> dos) {
        List<UserActivityDTO> dtos = new ArrayList<>();

        for (UserActivityDO d : dos) {
            dtos.add(UserActivityDTO.convert(d));
        }

        return dtos;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
