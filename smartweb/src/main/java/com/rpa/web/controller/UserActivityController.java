package com.rpa.web.controller;

import com.rpa.web.dto.UserActivityDTO;
import com.rpa.web.service.IUserActivityService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/9 11:04
 * @description: 活动赠送记录
 * @version: 1.0
 */
@RestController
public class UserActivityController {

    @Autowired
    private IUserActivityService service;

    @RequestMapping("/useractivity/query")
    public DTPageInfo<UserActivityDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                             @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                             @RequestParam(value = "phone", required = false) String phone) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("phone", phone);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    /**
     * 好评活动查询
     * @author: dangyi
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param phone
     * @return
     */
    @GetMapping("/goodcomment/query")
    public DTPageInfo<UserActivityDTO> goodCommentQuery(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                         @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "phone", required = false) String phone
    ) {
        // 调用业务层，返回页面结果
        DTPageInfo<UserActivityDTO> dTPageInfo = this.service.goodCommentQuery(draw, pageNum, pageSize, phone);
        return dTPageInfo;
    }

    /**
     * 好评活动：修改审核状态
     * @param httpSession
     * @param uAId
     * @param status
     * @return
     */
    @PostMapping("/goodcomment/update/status")
    public ResultVO updateStatus(HttpSession httpSession,
                                 @RequestParam(value = "uAId") Integer uAId,
                                 @RequestParam(value = "status") Byte status) {
        return this.service.updateStatus(httpSession, uAId, status);
    }
}
