package com.rpa.server.dto;

import com.rpa.common.dto.VerifyDTO;
import lombok.Data;

/**
 * @author: dangyi
 * @date: Created in 14:56 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Data
public class AdconfigDTO extends VerifyDTO {
    /**
     * 应用版本
     */
    private Integer softv;

    /**
     * 应用渠道
     */
    private String channel;
}
