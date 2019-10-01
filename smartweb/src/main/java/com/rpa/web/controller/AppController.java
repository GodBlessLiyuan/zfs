package com.rpa.web.controller;

import com.rpa.web.dto.AppDTO;
import com.rpa.web.service.IAppService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/1 13:48
 * @description: 版本更新
 * @version: 1.0
 */
@RestController
public class AppController {

    @Resource
    private IAppService service;

    @RequestMapping("/appversion/query")
    public DTPageInfo<AppDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                    @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                    @RequestParam(value = "aId") int aId) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("aId", aId);

        return service.query(draw, pageNum, pageSize, reqData);
    }
}
