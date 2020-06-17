package com.rpa.server.controller;

import com.rpa.common.vo.ResultVO;
import com.rpa.server.dto.VipCommodityDTO;
import com.rpa.server.service.IVipCommodityService;
import com.rpa.server.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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
        if (dto.getId() == null || dto.getVerify() == null) {
            return new ResultVO(2000);
        }
        if(!dto.getVerify().equals(DigestUtils.md5DigestAsHex(("vbooster@123" + dto.getId()).getBytes()))){
            return new ResultVO(2000);
        }

        return vipCommodityService.getCommodity(dto);
    }

    @PostMapping("buycommodity")
    public ResultVO buyCommodity() {
        return null;
    }
}
