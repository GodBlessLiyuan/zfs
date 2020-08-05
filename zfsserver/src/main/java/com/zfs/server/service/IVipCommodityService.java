package com.zfs.server.service;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.VipCommodityDTO;

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
    ResultVO getCommodity(VipCommodityDTO dto, int version);
}
