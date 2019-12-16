package com.rpa.server.dto;

import com.rpa.server.dto.base.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/16 10:58
 * @description: 注册/登录 DTO
 * @version: 1.0
 */
@Data
public class LoginDTO extends VerifyDTO {
    /**
     * 手机号码
     */
    private String ph;
    /**
     * 短信验证码
     */
    private String sms;
    /**
     * 应用渠道
     */
    private String channel;
}
