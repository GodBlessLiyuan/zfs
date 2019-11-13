package com.rpa.producer.controller.handler;

import com.rpa.producer.common.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final static Logger logger = LoggerFactory.getLogger(ProducerExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResultVO handlerPromptException(Exception e) {
        logger.info("ProducerExceptionHandler: ", e);
        return new ResultVO(2000);
    }
}
