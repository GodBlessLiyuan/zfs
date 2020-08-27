package com.zfs.web.controller;

import com.zfs.web.dto.ChBatchDTO;
import com.zfs.web.dto.ChBatchSyncDTO;
import com.zfs.web.service.ChBatchService;
import com.zfs.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 19:40 2019/10/7
 * @version: 1.0.0
 * @description: 会员卡配置
 */

@RequestMapping("chbatch")
@RestController
public class ChBatchController {

    @Autowired
    private ChBatchService chBatchService;

    /**
     * 查询
     * @param pageNum
     * @param pageSize
     * @param chanNickname
     * @param comTypeId 商品类型名称
     * @param status 状态
     * @param operator
     * @return
     */
    @PostMapping("query")
    public ResultVO query(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "chanNickname", required = false) String chanNickname,
            @RequestParam(value = "comTypeId", required = false) Integer comTypeId,
            @RequestParam(value = "status", required = false) Byte status,
            @RequestParam(value = "operator", required = false) String operator
    ){
        // 调用业务层，返回页面结果
        return chBatchService.query(pageNum, pageSize, chanNickname, comTypeId, status, operator);
    }


    /**
     * 查询所有产品类型
     * @return
     */
    @GetMapping("queryComTypes")
    public ResultVO queryComTypes() {
        return this.chBatchService.queryComTypes();
    }


    /**
     * 查询所有渠道标识
     * @return
     */
    @GetMapping("queryChanNicknames")
    public ResultVO queryChanNicknames() {
        return this.chBatchService.queryChanNicknames();
    }


    /**
     * 插入
     * @param chBatchDTO
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(ChBatchDTO chBatchDTO, HttpServletRequest request) {
        return this.chBatchService.insert(chBatchDTO, request.getSession());
    }

    @PostMapping("insertSync")
    public ResultVO insertSync(ChBatchSyncDTO chBatchDTO, HttpSession httpSession) {
        return this.chBatchService.insertSync(chBatchDTO, httpSession);
    }

    /**
     * 修改
     * @param batchId
     * @param status
     * @param httpSession
     * @return
     */
    @PostMapping("/update/status")
    public ResultVO updateStatus(@RequestParam(value = "batchId") Integer batchId,
                           @RequestParam(value = "status") Byte status,
                           HttpSession httpSession){
        return this.chBatchService.updateStatus(batchId, status, httpSession);
    }

    /**
     * 导出数据
     * @param chanNickname
     * @param comTypeId
     * @param status
     * @param operator
     * @return
     */
    @RequestMapping("export")
    public void export(@RequestParam(value = "chanNickname", required = false) String chanNickname,
                       @RequestParam(value = "comTypeId", required = false) Integer comTypeId,
                       @RequestParam(value = "status", required = false) Byte status,
                       @RequestParam(value = "operator", required = false) String operator,
                       HttpServletResponse response) {
        this.chBatchService.export(chanNickname, comTypeId, status, operator, response);
    }
}
