package com.rpa.web.controller;

import com.rpa.web.dto.BatchInfoDTO;
import com.rpa.web.service.BatchInfoService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: dangyi
 * @date: Created in 19:45 2019/10/8
 * @version: 1.0.0
 * @description: TODO
 */

@RequestMapping("batchInfo")
@RestController
public class BatchInfoController {

    @Autowired
    private BatchInfoService batchInfoService;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param vipkey
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<BatchInfoDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                          @RequestParam(value = "vipkey", required = false) String vipkey
    ){
        // 调用业务层，返回页面结果
        DTPageInfo<BatchInfoDTO> dTPageInfo = batchInfoService.query(draw, pageNum, pageSize, vipkey);
        return dTPageInfo;
    }
}
