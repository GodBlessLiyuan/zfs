package com.rpa.web.controller;

import com.rpa.web.dto.ChannelDTO;
import com.rpa.web.service.ChannelService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 9:33 2019/10/7
 * @version: 1.0.0
 * @description: TODO
 */

@RequestMapping("channel")
@RestController
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    /**
     * 查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param chanNickname
     * @param proName
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<ChannelDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                        @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                        @RequestParam(value = "chanNickname", required = false) String chanNickname,
                                        @RequestParam(value = "proName", required = false) String proName
    ){
        // 调用业务层，返回页面结果
        DTPageInfo<ChannelDTO> dTPageInfo = channelService.query(draw, pageNum, pageSize, chanNickname, proName);
        return dTPageInfo;
    }

    /**
     * 插入
     * @param channelDTO
     * @return
     */
    @PostMapping("insert")
    public int insert(ChannelDTO channelDTO, HttpSession httpSession) {
        return this.channelService.insert(channelDTO, httpSession);
    }
}
