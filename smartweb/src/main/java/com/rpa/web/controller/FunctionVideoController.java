package com.rpa.web.controller;

import com.rpa.web.dto.FunctionVideoDTO;
import com.rpa.web.service.FunctionVideoService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;
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
@RequestMapping("functionvideo")
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
     * 查询
     * @param functionId
     * @return
     */
    @GetMapping("queryById")
    public ResultVO queryById(@RequestParam(value = "functionId") Integer functionId) {
        return this.functionVideoService.queryById(functionId);
    }

    /**
     * 插入
     * @param functionVideoDTO
     * @param httpSession
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(FunctionVideoDTO functionVideoDTO, HttpSession httpSession) {
        return this.functionVideoService.insert(functionVideoDTO, httpSession);
    }

    /**
     * 修改
     * @param functionVideoDTO
     * @param httpSession
     * @return
     */
    @PostMapping("update")
    public ResultVO update(FunctionVideoDTO functionVideoDTO, HttpSession httpSession) {
        return this.functionVideoService.update(functionVideoDTO, httpSession);
    }

    /**
     * 删除
     * @param functionId
     * @return
     */
    @PostMapping("delete")
    public ResultVO delete(@RequestParam(value = "functionId") Integer functionId) {
        return this.functionVideoService.delete(functionId);
    }



}
