package com.zfs.web.vo;

import com.zfs.common.bo.UserVipBO;
import lombok.Data;

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
@Data
public class UserVipVO implements Serializable {
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
     * @param bo
     * @return
     */
    public static UserVipVO convert(UserVipBO bo) {
        UserVipVO vo = new UserVipVO();

        vo.setUserId(bo.getUserId());
        vo.setPhone(bo.getPhone());
        vo.setCreateTime(bo.getCreateTime());
        vo.setFirstTime(bo.getFirstTime());
        vo.setIsPay((byte) (bo.getFirstTime() == null ? 2 : 1));
        vo.setEndTime(bo.getEndTime());
        vo.setVendTime(bo.getVendTime());
        vo.setLastTime(bo.getLastTime());

        return vo;
    }

    /**
     * dos 转 dtos
     *
     * @param bos
     * @return
     */
    public static List<UserVipVO> convert(List<UserVipBO> bos) {
        List<UserVipVO> vos = new ArrayList<>();

        for (UserVipBO bo : bos) {
            vos.add(UserVipVO.convert(bo));
        }

        return vos;
    }
}
