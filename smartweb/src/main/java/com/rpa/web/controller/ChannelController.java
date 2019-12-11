package com.rpa.web.controller;

import com.rpa.web.dto.ChannelDTO;
import com.rpa.web.service.ChannelService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 9:33 2019/10/7
 * @version: 1.0.0
 * @description: 推广渠道
 */

@RequestMapping("channel")
@RestController
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    /**
     * 查询
     * @param draw
     * @param start
     * @param length
     * @param chanNickname
     * @param proId
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<ChannelDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                        @RequestParam(value = "start", defaultValue = "1") int start,
                                        @RequestParam(value = "length", defaultValue = "10") int length,
                                        @RequestParam(value = "chanNickname", required = false) String chanNickname,
                                        @RequestParam(value = "proId", required = false) Integer proId
    ){
        // 调用业务层，返回页面结果
        DTPageInfo<ChannelDTO> dTPageInfo = channelService.query(draw, start, length, chanNickname, proId);
        return dTPageInfo;
    }


    /**
     * 查询t_channel表中所有的推广负责人
     * @return
     */
    @GetMapping("queryProNames")
    public ResultVO queryProNames() {
        return this.channelService.queryProNames();
    }

    /**
     * 查询t_promoter表所有的推广负责人
     * @return
     */
    @GetMapping("queryAllProNames")
    public ResultVO queryAllProNames() {
        return this.channelService.queryAllProNames();
    }


    /**
     * 插入
     * @param channelDTO
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(ChannelDTO channelDTO, HttpSession httpSession) {
        return this.channelService.insert(channelDTO, httpSession);
    }
}
