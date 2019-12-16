package com.rpa.server.dto;

import com.rpa.server.dto.base.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/29 10:38
 * @description: 获取砖助智能助手的基础信息
 * @version: 1.0
 */
@Data
public class ConfigDTO extends VerifyDTO {
    private Integer softv;
    private String channel;
    private Integer index;
}
