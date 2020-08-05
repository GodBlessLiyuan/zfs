package com.rpa.web.controller;

import com.rpa.web.service.BatchInfoService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.BatchInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: dangyi
 * @date: Created in 19:45 2019/10/8
 * @version: 1.0.0
 * @description: 会员卡查询
 */

@RequestMapping("batchinfo")
@RestController
public class BatchInfoController {

    @Autowired
    private BatchInfoService batchInfoService;

    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param vipkey
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<BatchInfoVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                         @RequestParam(value = "start", defaultValue = "1") int start,
                                         @RequestParam(value = "length", defaultValue = "10") int length,
                                         @RequestParam(value = "vipkey", required = false) String vipkey
    ){
        // 调用业务层，返回页面结果
        DTPageInfo<BatchInfoVO> dTPageInfo = batchInfoService.query(draw, start, length, vipkey);
        return dTPageInfo;
    }


    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param batchId
     * @return
     */
    @GetMapping("queryByBatchid")
    public DTPageInfo<BatchInfoVO> queryByBatchid(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                                   @RequestParam(value = "start", defaultValue = "1") int start,
                                                   @RequestParam(value = "length", defaultValue = "10") int length,
                                                   @RequestParam(value = "batchId") Integer batchId,
                                                   @RequestParam(value = "status") Byte status
    ){
        // 调用业务层，返回页面结果
        DTPageInfo<BatchInfoVO> dTPageInfo = batchInfoService.queryByBatchid(draw, start, length, batchId, status);
        return dTPageInfo;
    }

    /**
     * 导出数据
     * @param batchId
     * @param status
     * @param response
     */
    @GetMapping("export")
    public void export(@RequestParam(value = "batchId") Integer batchId,
                       @RequestParam(value = "status") Byte status,
                       HttpServletResponse response) {
        this.batchInfoService.export(batchId, status, response);
    }
}
