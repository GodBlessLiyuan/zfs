package com.rpa.web.controller;

import com.rpa.web.vo.NoticeVO;
import com.rpa.web.service.NoticeService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 18:17 2019/9/26
 * @version: 1.0.0
 * @description: 通知管理
 */

@RequestMapping("notice")
@RestController
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param startTime
     * @param endTime
     * @param status
     * @param type
     * @param title
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<NoticeVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                      @RequestParam(value = "start", defaultValue = "1") int start,
                                      @RequestParam(value = "length", defaultValue = "10") int length,
                                      @RequestParam(value = "startTime", required = false) String startTime,
                                      @RequestParam(value = "endTime", required = false) String endTime,
                                      @RequestParam(value = "status", required = false) Integer status,
                                      @RequestParam(value = "type", required = false) Byte type,
                                      @RequestParam(value = "title", required = false) String title
    ) {
        // 调用业务层，返回页面结果
        DTPageInfo<NoticeVO> dTPageInfo = noticeService.query(draw, start, length, startTime, endTime, status, type, title);
        return dTPageInfo;
    }


    /**
     * 插入
     * @param type
     * @param text
     * @param picurl
     * @param title
     * @param url
     * @param showTime
     * @param startTime
     * @param endTime
     * @param httpSession
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(@RequestParam(value = "type") Byte type,
                           @RequestParam(value = "text", required = false) String text,
                           @RequestParam(value = "picurl", required = false)MultipartFile picurl,
                           @RequestParam(value = "title", required = false) String title,
                           @RequestParam(value = "url", required = false) String url,
                           @RequestParam(value = "showTime", required = false) String showTime,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           HttpSession httpSession) {
        return this.noticeService.insert(type, text, picurl, title, url, showTime, startTime, endTime, httpSession);
    }


    /**
     * 更改状态
     * @param noticeId
     * @param status
     * @param httpSession
     * @return
     */
    @PostMapping("/update/status")
    public ResultVO updateStatus(@RequestParam(value = "noticeId")Integer noticeId,
                                 @RequestParam(value = "status")Integer status,
                                 HttpSession httpSession
                                 ) {
        return this.noticeService.updateStatus(noticeId, status, httpSession);

    }


    /**
     * 删除
     * @param noticeId
     * @return
     */
    @PostMapping("delete")
    public ResultVO delete(@RequestParam(value = "noticeId")Integer noticeId) {
        return this.noticeService.delete(noticeId);
    }
}
