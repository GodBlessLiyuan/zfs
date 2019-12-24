package com.rpa.server.dto;

import com.rpa.common.dto.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 15:33
 * @description: 商品列表
 * @version: 1.0
 */
@Data
public class VipCommodityDTO extends VerifyDTO {
    /**
     * 应用版本
     */
    private Integer softv;
    /**
     * 应用渠道
     */
    private String channel;
}
