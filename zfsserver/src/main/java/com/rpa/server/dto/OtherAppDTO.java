package com.rpa.server.dto;

import com.rpa.common.dto.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/25 11:16
 * @description: 其他应用
 * @version: 1.0
 */
@Data
public class OtherAppDTO extends VerifyDTO {
    /**
     * 应用渠道
     */
    private String channel;
}
