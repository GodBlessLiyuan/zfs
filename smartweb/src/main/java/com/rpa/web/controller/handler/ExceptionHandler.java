package com.rpa.web.controller.handler;

import com.rpa.web.exception.PromptException;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: xiahui
 * @date: Created in 2019/10/11 10:26
 * @description: 异常处理
 * @version: 1.0
 */
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = PromptException.class)
    public ResultVO handlerPromptException(PromptException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }
}
