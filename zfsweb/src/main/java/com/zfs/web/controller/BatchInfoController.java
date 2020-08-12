package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.BatchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param vipkey
     * @return
     */
    @PostMapping("query")
    public ResultVO query(
          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
          @RequestParam(value = "vipkey", required = false) String vipkey
    ){
        // 调用业务层，返回页面结果
        return batchInfoService.query(pageNum, pageSize, vipkey);
    }


    /**
     * 查询
     * @param start
     * @param length
     * @param batchId
     * @return
     */
    @PostMapping("queryByBatchid")
    public ResultVO queryByBatchid(
           @RequestParam(value = "start", defaultValue = "1") int start,
           @RequestParam(value = "length", defaultValue = "10") int length,
           @RequestParam(value = "batchId") Integer batchId,
           @RequestParam(value = "status",required = false) Byte status
    ){
        // 调用业务层，返回页面结果
        return batchInfoService.queryByBatchid(start, length, batchId, status);
    }

    /**
     * 导出数据
     * @param batchId
     * @param status
     * @param response
     */
    @GetMapping("export")
    public ResultVO export(@RequestParam(value = "batchId") Integer batchId,
                       @RequestParam(value = "status",required = false) Byte status,
                       HttpServletResponse response) {
        this.batchInfoService.export(batchId, status, response);
        return new ResultVO(1000);
    }
}
