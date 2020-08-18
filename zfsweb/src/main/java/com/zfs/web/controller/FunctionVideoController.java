package com.zfs.web.controller;

import com.zfs.web.service.FunctionVideoService;
import com.zfs.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("queryById")
    public ResultVO queryById(@RequestParam(value = "functionId") Integer functionId) {
        return this.functionVideoService.queryById(functionId);
    }


    /**
     * 插入
     * @param httpSession
     * @param funName
     * @param url
     * @param extra
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(HttpSession httpSession,
                           @RequestParam(value = "funName") String funName,
                           @RequestParam(value = "url") String url,
                           @RequestParam(value = "extra", required = false) String extra) {
        return this.functionVideoService.insert(httpSession, funName, url, extra);
    }


    /**
     * 修改
     * @param httpSession
     * @param funName
     * @param url
     * @param extra
     * @return
     */
    @PostMapping("update")
    public ResultVO update(HttpSession httpSession,
                           @RequestParam(value = "functionId") Integer functionId,
                           @RequestParam(value = "funName", required = false) String funName,
                           @RequestParam(value = "url", required = false) String url,
                           @RequestParam(value = "extra", required = false) String extra) {
        return this.functionVideoService.update(httpSession, functionId, funName, url, extra);
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
