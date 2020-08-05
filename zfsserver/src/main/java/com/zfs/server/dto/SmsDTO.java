package com.zfs.server.dto;

import com.zfs.common.dto.TokenDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2020/1/17 15:56
 * @description: 验证码
 * @version: 1.0
 */
@Data
public class SmsDTO extends TokenDTO {
    private String sms;
}
