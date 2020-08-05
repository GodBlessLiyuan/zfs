package com.zfs.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 16:58
 * @description: 用户会员详细信息
 * @version: 1.0
 */
@Data
public class UserVipDetailsVO implements Serializable, Comparable<UserVipDetailsVO> {
    private static final long serialVersionUID = 1L;
    /**
     * 会员获取方式
     */
    private String vipType;
    /**
     * 用户渠道名称
     */
    private String userChanName;
    /**
     * 销售渠道名称
     */
    private String saleChanName;
    /**
     * 获得会员时间
     */
    private Date createTime;
    /**
     * 产品类型
     */
    private String comTypeName;
    /**
     * 会员天数
     */
    private Integer days;
    /**
     * 支付方式：1-微信；2-支付宝
     */
    private Integer type;
    private String  commAttr;

    @Override
    public int compareTo(UserVipDetailsVO vo) {
        if (null == this.createTime || null == vo.getCreateTime()) {
            return -1;
        }
        // 降序
        return vo.getCreateTime().compareTo(this.createTime);
    }
}
