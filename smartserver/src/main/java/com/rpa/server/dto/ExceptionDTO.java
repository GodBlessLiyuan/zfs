package com.rpa.server.dto;

import com.rpa.server.dto.base.VerifyDTO;
import lombok.Data;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 8:48
 * @description: 上传异常log
 * @version: 1.0
 */
@Data
public class ExceptionDTO extends VerifyDTO {
    private Byte osv;
    private String osre;
    private Integer softv;
    private String model;
    private String exception;
    private String pkg;
}
