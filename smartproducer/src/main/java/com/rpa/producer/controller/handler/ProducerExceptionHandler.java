package com.rpa.producer.controller.handler;

import com.rpa.producer.common.ResultVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: xiahui
 * @date: Created in 2019/10/11 10:26
 * @description: 异常处理
 * @version: 1.0
 */
@RestControllerAdvice
public class ProducerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultVO handlerPromptException(Exception e) {
        return new ResultVO(2000);
    }
}
