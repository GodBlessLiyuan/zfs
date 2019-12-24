package com.rpa.server.dto;

import com.rpa.common.dto.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/24 14:57
 * @description: 分身更新
 * @version: 1.0
 */
@Data
public class AvatarDTO extends VerifyDTO {
    /**
     * 应用版本
     */
    private Integer softv;

    /**
     * 应用渠道
     */
    private String channel;

    /**
     * 分身版本
     */
    private String avatarv;
}
