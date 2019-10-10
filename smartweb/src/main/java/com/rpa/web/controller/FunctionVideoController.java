package com.rpa.web.controller;

import com.rpa.web.dto.FunctionVideoDTO;
import com.rpa.web.service.FunctionVideoService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 16:13 2019/10/9
 * @version: 1.0.0
 * @description: 功能视频
 */

@RestController
@RequestMapping("functionVideo")
public class FunctionVideoController {

    @Autowired
    private FunctionVideoService functionVideoService;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param funName
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<FunctionVideoDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                              @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                              @RequestParam(value = "funName", required = false) String funName
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<FunctionVideoDTO> dTPageInfo = this.functionVideoService.query(draw, pageNum, pageSize, funName);
        return dTPageInfo;
    }

    /**
     * 插入
     * @param functionVideoDTO
     * @param httpSession
     * @return
     * @TODO 还需插入操作人，即管理员a_id，需从session中获取
     */
    @PostMapping("insert")
    public int insert(FunctionVideoDTO functionVideoDTO, HttpSession httpSession) {
        return this.functionVideoService.insert(functionVideoDTO, httpSession);
    }
}
