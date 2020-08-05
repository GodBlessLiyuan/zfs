package com.zfs.server.dto;

import com.zfs.common.dto.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 8:52
 * @description: 应用更新请求参数
 * @version: 1.0
 */
@Data
public class AppDTO extends VerifyDTO {
    /**
     * 应用版本
     */
    private Integer softv;

    /**
     * 应用渠道
     */
    private String channel;
}
