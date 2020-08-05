package com.zfs.server.dto;

import com.zfs.common.dto.VerifyDTO;
import lombok.Data;

/**
 * @author: dangyi
 * @date: Created in 9:35 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Data
public class WxsupportDTO extends VerifyDTO {

    /**
     * 应用版本
     */
    private Integer softv;

    /**
     * 应用渠道
     */
    private String channel;

    /**
     * 微信白名单唯一标识
     */
    private Integer index;

}
