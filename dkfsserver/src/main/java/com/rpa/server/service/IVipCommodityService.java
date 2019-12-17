package com.rpa.server.service;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.VipCommodityDTO;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 15:39
 * @description: 商品列表
 * @version: 1.0
 */
public interface IVipCommodityService {
    /**
     * 获取商品列表
     *
     * @param dto
     * @return
     */
    ResultVO getCommodity(VipCommodityDTO dto);
}
