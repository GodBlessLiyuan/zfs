package com.rpa.web.controller;

import com.rpa.web.dto.ChBatchDTO;
import com.rpa.web.service.ChBatchService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 19:40 2019/10/7
 * @version: 1.0.0
 * @description: 会员卡配置
 */

@RequestMapping("chBatch")
@RestController
public class ChBatchController {

    @Autowired
    private ChBatchService chBatchService;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param chanNickname
     * @param comTypeName
     * @param status
     * @param username
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<ChBatchDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                        @RequestParam(value = "chanNickname", required = false) String chanNickname,
                                        @RequestParam(value = "comTypeName", required = false) String comTypeName,
                                        @RequestParam(value = "status", required = false) String status,
                                        @RequestParam(value = "username", required = false) String username
    ){
        // 调用业务层，返回页面结果
        DTPageInfo<ChBatchDTO> dTPageInfo = chBatchService.query(draw, pageNum, pageSize, chanNickname, comTypeName, status, username);
        return dTPageInfo;
    }

    /**
     * 插入
     * @param chBatchDTO
     * @param httpSession
     * @return
     */
    @PostMapping("insert")
    public int insert(ChBatchDTO chBatchDTO, HttpSession httpSession) {
        return this.chBatchService.insert(chBatchDTO, httpSession);
    }

    /**
     * 修改
     * @param chBatchDTO
     * @return
     */
    @PostMapping("update")
    public int update(ChBatchDTO chBatchDTO, HttpSession httpSession){
        return this.chBatchService.update(chBatchDTO, httpSession);
    }
}
