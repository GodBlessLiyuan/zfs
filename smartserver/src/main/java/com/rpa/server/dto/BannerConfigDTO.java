package com.rpa.server.dto;

import com.rpa.server.dto.base.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/18 9:06
 * @description: banner广告信息
 * @version: 1.0
 */
@Data
public class BannerConfigDTO extends VerifyDTO {
    /**
     * 应用版本
     */
    private Integer softv;
    /**
     * 应用渠道
     */
    private String channel;
}
