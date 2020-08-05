package com.zfs.server.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 16:09
 * @description: 商品列表
 * @version: 1.0
 */
@Data
public class VipCommodityVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private Integer cmdid;
    /**
     * 描述
     */
    private String description;
    /**
     * 原价
     */
    private String price;
    /**
     * 显示折扣
     */
    private String showdiscount;
    /**
     * 显示价格
     */
    private Float discount;
    /**
     * 商品类型名称   日卡 月卡 年卡
     */
    private String typename;
    /**
     * 天数
     */
    private Integer days;
    /**
     * 是否置顶
     */
    private Byte istop;
}
