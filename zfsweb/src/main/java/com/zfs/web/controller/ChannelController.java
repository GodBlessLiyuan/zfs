package com.zfs.web.controller;

import com.zfs.web.dto.ChannelDTO;
import com.zfs.web.service.ChannelService;
import com.zfs.common.vo.ResultVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * @return
     */
    @PostMapping("query")
    public ResultVO query(
           @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
           @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
           @RequestParam(value = "chanNickname", required = false) String chanNickname,
           @RequestParam(value = "proName", required = false) String proName
    ){
        // 调用业务层，返回页面结果
        if(!StringUtils.isEmpty(proName)){
            proName=proName.replace(" ","");
        }
        return channelService.query(pageNum, pageSize, chanNickname, proName);
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
    public ResultVO insert(ChannelDTO channelDTO, HttpServletRequest request) {
        return this.channelService.insert(channelDTO, request.getSession());
    }
}
