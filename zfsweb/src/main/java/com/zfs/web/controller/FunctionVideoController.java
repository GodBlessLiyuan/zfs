package com.zfs.web.controller;

import com.zfs.web.dto.FunctionVideoDTO;
import com.zfs.web.service.FunctionVideoService;
import com.zfs.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
     * @param pageNum
     * @param pageSize
     * @param funName
     * @return
     */
    @PostMapping("query")
    public ResultVO query(
             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
             @RequestParam(value = "funName", required = false) String funName
    ) {

        // 调用业务层，返回页面结果
        return this.functionVideoService.query(pageNum, pageSize, funName);
    }

    /**
     * 查询
     * @param functionId
     * @return
     */
    @Deprecated
    @PostMapping("queryById")
    public ResultVO queryById(@RequestParam(value = "functionId") Integer functionId) {
        return this.functionVideoService.queryById(functionId);
    }


    /**
     * 插入
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(HttpServletRequest request,
                           @RequestBody FunctionVideoDTO videoDTO) {
        return this.functionVideoService.insert(request.getSession(), videoDTO.getFunName(), videoDTO.getUrls(),videoDTO.getExtra());
    }


    /**
     * 修改
     * @return
     */
    @PostMapping("update")
    public ResultVO update(HttpServletRequest request,
                           @RequestBody FunctionVideoDTO videoDTO) {
        return this.functionVideoService.update(request.getSession(), videoDTO.getFunctionId(),
                videoDTO.getFunName(),videoDTO.getUrls(),videoDTO.getExtra());
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
