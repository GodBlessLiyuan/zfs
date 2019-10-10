package com.rpa.web.dto;

import com.rpa.web.domain.UserVipDO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 14:28
 * @description: 用户会员数据
 * @version: 1.0
 */
public class UserVipDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String phone;
    /**
     * 用户注册时间
     */
    private Date createTime;
    private Date firstTime;
    /**
     * 是否付费用户: 1-付费用户；2-非付费用户
     */
    private Byte isPay;
    private Date endTime;
    private Date vendTime;
    private Date lastTime;

    /**
     * do 转 dto
     *
     * @param d
     * @return
     */
    public static UserVipDTO convert(UserVipDO d) {
        UserVipDTO dto = new UserVipDTO();

        dto.setUserId(d.getUserId());
        dto.setPhone(d.getPhone());
        dto.setCreateTime(d.getCreateTime());
        dto.setFirstTime(d.getFirstTime());
        dto.setIsPay((byte) (d.getFirstTime() == null ? 2 : 1));
        dto.setEndTime(d.getEndTime());
        dto.setVendTime(d.getVendTime());
        dto.setLastTime(d.getLastTime());

        return dto;
    }

    /**
     * dos 转 dtos
     *
     * @param dos
     * @return
     */
    public static List<UserVipDTO> convert(List<UserVipDO> dos) {
        List<UserVipDTO> dtos = new ArrayList<>();

        for (UserVipDO d : dos) {
            dtos.add(UserVipDTO.convert(d));
        }

        return dtos;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Byte getIsPay() {
        return isPay;
    }

    public void setIsPay(Byte isPay) {
        this.isPay = isPay;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getVendTime() {
        return vendTime;
    }

    public void setVendTime(Date vendTime) {
        this.vendTime = vendTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }
}
