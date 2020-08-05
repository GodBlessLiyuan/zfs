package com.zfs.server.dto;

import com.zfs.common.dto.VerifyDTO;
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
    private Integer avatarv;
    /**
     * 系统操作系统版本（0代表低于10的系统，10表示10及以上的系统）
     * */
    private int os_version;
}
