package com.rpa.web.controller.handler;

import com.rpa.web.exception.PromptException;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
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
public class WebExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResultVO handlerPromptException(Exception e) {
        logger.error("WebExceptionHandler: ", e);
        if (e instanceof PromptException) {
            return ResultVOUtil.error(((PromptException) e).getCode(), e.getMessage());
        }

        return new ResultVO(2000, "服务器内部错误!");
    }
}
