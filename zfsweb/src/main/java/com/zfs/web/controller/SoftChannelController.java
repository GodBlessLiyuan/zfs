package com.zfs.web.controller;

import com.zfs.web.vo.SoftChannelVO;
import com.zfs.web.service.ISoftChannelService;
import com.zfs.web.utils.DTPageInfo;
import com.zfs.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 8:48
 * @description: 渠道信息
 * @version: 1.0
 */
@RequestMapping("softchannel")
@RestController
public class SoftChannelController {

    @Autowired
    private ISoftChannelService service;

    @RequestMapping("query")
    public DTPageInfo<SoftChannelVO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                           @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                           @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                           @RequestParam(value = "channelName") String channelName) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("name", channelName);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("queryAll")
    public List<SoftChannelVO> queryAll() {
        return service.queryAll();
    }

    @RequestMapping("insert")
    public ResultVO insert(@RequestParam(value = "channelName") String channelName,
                           @RequestParam(value = "extra") String extra) {
        return service.insert(channelName, extra);
    }
}
