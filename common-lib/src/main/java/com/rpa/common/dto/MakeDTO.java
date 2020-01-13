package com.rpa.common.dto;

import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/12/30 8:29
 * @description: TODO
 * @version: 1.0
 */
@Data
public class MakeDTO extends VerifyDTO {
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

    /**
     * 图标后缀
     */
    private String suffix;

    /**
     * 应用包名
     */
    private String apppkg;

    /**
     * 是否隔离
     */
    private String isolation;
}
