package com.rpa.kafka.controller.handler;

import com.rpa.kafka.common.ResultVO;
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
public class KafkaExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(KafkaExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResultVO handlerPromptException(Exception e) {
        logger.error("KafkaExceptionHandler: ", e);
        return new ResultVO(2000);
    }
}
