package com.rpa.web.exception;

import com.rpa.web.enumeration.ExceptionEnum;
import lombok.Getter;

/**
 * @author: xiahui
 * @date: Created in 2019/10/11 10:28
 * @description: 捕捉异常，前端提示
 * @version: 1.0
 */
@Getter
public class PromptException extends RuntimeException {
    private Integer code;
    private String msg;

    public PromptException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    public PromptException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
