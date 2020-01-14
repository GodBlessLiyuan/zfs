package com.rpa.server.dto;

import com.rpa.common.dto.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2020/1/14 18:40
 * @description: 应用黑名单
 * @version: 1.0
 */
@Data
public class BlankAppDTO extends VerifyDTO {
    private Long bid;
}
