package com.zfs.web.controller;

import com.zfs.web.dto.ChannelDTO;
import com.zfs.web.service.ChannelService;
import com.zfs.common.vo.ResultVO;
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
     * @param chanNickname
     * @param proId
     * @return
     */
    @PostMapping("query")
    public ResultVO query(
           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
           @RequestParam(value = "chanNickname", required = false) String chanNickname,
           @RequestParam(value = "proId", required = false) Integer proId
    ){
        // 调用业务层，返回页面结果
        return channelService.query(pageNum, pageSize, chanNickname, proId);
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
    @PostMapping("queryAllProNames")
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
