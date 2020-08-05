package com.zfs.server.dto;

import com.zfs.common.dto.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2020/1/14 18:40
 * @description: 应用黑名单
 * @version: 1.0
 */
@Data
public class BlackAppDTO extends VerifyDTO {
    private Long bid;
}
