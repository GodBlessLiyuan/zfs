package com.rpa.server.controller;

import com.rpa.server.common.ResultVO;
import com.rpa.server.dto.ExceptionDTO;
import com.rpa.server.service.IExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xiahui
 * @date: Created in 2019/10/21 8:47
 * @description: 上传异常log
 * @version: 1.0
 */
@RequestMapping("v1.0")
@RestController
public class ExceptionController {
    @Autowired
    private IExceptionService service;

    @PostMapping("exception")
    public ResultVO exception(@RequestBody ExceptionDTO dto) {
        return service.insert(dto);
    }
}
