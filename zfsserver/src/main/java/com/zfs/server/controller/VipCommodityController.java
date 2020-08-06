package com.zfs.server.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.server.dto.VipCommodityDTO;
import com.zfs.server.service.IVipCommodityService;
import com.zfs.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 15:31
 * @description: 商品列表
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class VipCommodityController {

    @Autowired
    private IVipCommodityService vipCommodityService;

    @PostMapping("getcommodity")
    public ResultVO getCommodity(@RequestBody VipCommodityDTO dto) {

        return vipCommodityService.getCommodity(dto,0);
    }

    @PostMapping("buycommodity")
    public ResultVO buyCommodity() {
        return null;
    }
}
