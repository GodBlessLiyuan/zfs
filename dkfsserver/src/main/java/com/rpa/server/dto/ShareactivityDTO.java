package com.rpa.server.dto;

import com.rpa.server.dto.base.VerifyDTO;
import lombok.Data;

/**
 * @author: dangyi
 * @date: Created in 11:17 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Data
public class ShareactivityDTO extends VerifyDTO {
    /**
     * 应用版本
     */
    private Integer softv;
    /**
     * 应用渠道
     */
    private String channel;
}
