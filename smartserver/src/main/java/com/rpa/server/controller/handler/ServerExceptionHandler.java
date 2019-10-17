package com.rpa.server.controller.handler;

import com.rpa.server.common.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: xiahui
 * @date: Created in 2019/10/11 10:26
 * @description: 异常处理
 * @version: 1.0
 */
@RestControllerAdvice
public class ServerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultVO handlerPromptException(Exception e) {
        return new ResultVO(2000);
    }
}
