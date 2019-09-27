package com.rpa.web.controller;

import com.rpa.web.dto.NoticeDTO;
import com.rpa.web.pojo.NoticePO;
import com.rpa.web.service.NoticeService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
     * @param pageNum
     * @param pageSize
     * @param startTime
     * @param endTime
     * @param status
     * @param type
     * @param title
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<NoticeDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                       @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "startTime", required = false) Date startTime,
                                       @RequestParam(value = "endTime", required = false) Date endTime,
                                       @RequestParam(value = "status", required = false) Integer status,
                                       @RequestParam(value = "type", required = false) Byte type,
                                       @RequestParam(value = "title", required = false) String title
    ) {
        // 调用业务层，返回页面结果
        DTPageInfo<NoticeDTO> dTPageInfo = noticeService.query(draw, pageNum, pageSize, startTime, endTime, status, type, title);
        return dTPageInfo;
    }

    /**
     * 插入
     * @param noticeDTO
     * @return
     */
    @PostMapping("insert")
    public int insert(NoticeDTO noticeDTO) {
        return this.noticeService.insert(noticeDTO);
    }

    @PostMapping("delete")
    public int delete(int id) {
        return this.noticeService.delete(id);
    }
}
