package com.zfs.web.dto;

import com.zfs.common.dto.TokenDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 9:26
 * @description: 卡密表
 * @version: 1.0
 */
@Data
public class BatchInfoDTO extends TokenDTO {
    private String phone;
    private String key;
}
