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
     * @param draw     draw
     * @param pageNum  页面下标
     * @param pageSize 页面大小
     * @param reqData  请求数据
     * @return
     */
    DTPageInfo<VipCommodityDTO> query(int draw, int pageNum, int pageSize, Map<String, Object> reqData);
}
