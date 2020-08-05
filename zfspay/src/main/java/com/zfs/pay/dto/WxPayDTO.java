package com.zfs.pay.dto;

import com.zfs.pay.dto.base.TokenDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/11/7 11:48
 * @description: 微信支付 购买商品
 * @version: 1.0
 */
@Data
public class WxPayDTO extends TokenDTO {
    private Integer cmdyid;
    private String verify;
    private String um;
}
