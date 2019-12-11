package com.rpa.voice.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: xiahui
 * @date: Created in 2019/12/4 15:09
 * @description: 异常处理
 * @version: 1.0
 */
@RestControllerAdvice
public class VoiceExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(VoiceExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResultVO handlerException(Exception e) {
        logger.error("VoiceExceptionHandler: ", e);
        return new ResultVO(2000);
    }
}
