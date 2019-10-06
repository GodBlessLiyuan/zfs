package com.rpa.web.dto;

import com.rpa.web.pojo.UserGiftsPO;

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
public class UserGiftsDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private Integer nugId;
    private Integer comTypeId;
    private String comTypeName;
    private Integer days;
    private Byte status;
    private Date createTime;
    private Date updateTime;
    private String username;

    public Integer getNugId() {
        return nugId;
    }

    public void setNugId(Integer nugId) {
        this.nugId = nugId;
    }

    public Integer getComTypeId() {
        return comTypeId;
    }

    public void setComTypeId(Integer comTypeId) {
        this.comTypeId = comTypeId;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    /**
     * po 转 dto
     *
     * @param po
     * @return
     */
    public static UserGiftsDTO convert(UserGiftsPO po) {
        UserGiftsDTO dto = new UserGiftsDTO();

        dto.setNugId(po.getNugId());
        dto.setComTypeId(po.getComTypeId());
        dto.setComTypeName(po.getComTypeName());
        dto.setDays(po.getDays());
        dto.setStatus(po.getStatus());
        dto.setCreateTime(po.getCreateTime());
        dto.setUsername(po.getUsername());

        return dto;
    }

    /**
     * pos 转 dtos
     *
     * @param pos
     * @return
     */
    public static List<UserGiftsDTO> convert(List<UserGiftsPO> pos) {
        List<UserGiftsDTO> dtos = new ArrayList<>();

        for (UserGiftsPO po : pos) {
            dtos.add(UserGiftsDTO.convert(po));
        }

        return dtos;
    }
}
