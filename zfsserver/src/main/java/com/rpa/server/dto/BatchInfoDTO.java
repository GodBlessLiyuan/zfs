package com.rpa.server.dto;

import com.rpa.common.dto.TokenDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 9:26
 * @description: 卡密表
 * @version: 1.0
 */
@Data
public class BatchInfoDTO extends TokenDTO {
    /**
     * 特别注意：手机端传的参数没有手机号
     * */
    private String phone;
    private String key;
}
