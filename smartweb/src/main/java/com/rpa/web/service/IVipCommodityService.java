package com.rpa.web.service;

import com.rpa.web.pojo.VipCommodityPO;

import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/26 11:52
 * @description: 商品列表接口
 * @version: 1.0
 */
public interface IVipCommodityService {

    /**
     * 新增
     */
    void insert(VipCommodityPO po);

    /**
     * 更新
     */
    void update(VipCommodityPO po);

    /**
     * 查询
     *
     * @param map
     * @return
     */
    List<VipCommodityPO> query(Map<String, Object> map);
}
