package com.zfs.server.dto;

import com.zfs.common.dto.VerifyDTO;
import lombok.Data;

/**
 * @author: dangyi
 * @date: Created in 14:31 2019/10/29
 * @version: 1.0.0
 * @description:
 */
@Data
public class FunctionvideoDTO extends VerifyDTO {

    /**
     * 视频模块名称
     */
    private String function;
    private Integer softv;
}
