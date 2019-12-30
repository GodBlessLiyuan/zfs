package com.rpa.server.dto;

import com.rpa.common.dto.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/30 8:29
 * @description: TODO
 * @version: 1.0
 */
@Data
public class AvatarMakeDTO extends VerifyDTO {
    /**
     * 分身Id
     */
    private Long avaid;
    /**
     * 分身图标
     */
    private String pic;
    /**
     * 分身包名
     */
    private String pkg;
    /**
     * 分身应用名称
     */
    private String name;
}
