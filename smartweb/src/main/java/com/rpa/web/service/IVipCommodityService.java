package com.rpa.web.service;

import com.rpa.web.dto.VipCommodityDTO;
import com.rpa.web.pojo.VipCommodityPO;
import com.rpa.web.utils.DTPageInfo;

import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 11:52
 * @description: 商品列表接口
 * @version: 1.0
 */
public interface IVipCommodityService {

    /**
     * 更新
     */
    void update(VipCommodityPO po);

    /**
     * 查询
     *
     * @param draw     draw
     * @param pageNum  页面下标
     * @param pageSize 页面大小
     * @param reqData  请求数据
     * @return
     */
    DTPageInfo<VipCommodityDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);

    /**
     * 新增
     *
     * @param channelId    销售渠道Id
     * @param comTypeId    产品类型Id
     * @param comName      商品名称
     * @param description  商品描述
     * @param price        原价
     * @param showDiscount 折扣
     * @param discount     售价
     * @param aId          管理员Id
     */
    void insert(int channelId, int comTypeId, String comName, String description,
                int price, String showDiscount, float discount, int aId);
}
