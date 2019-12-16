package com.rpa.server.dto;

import com.rpa.server.dto.base.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/17 9:33
 * @description: 插件更新
 * @version: 1.0
 */
@Data
public class PluginDTO extends VerifyDTO {
    /**
     * 插件版本
     */
    private Integer pluginv;
}
