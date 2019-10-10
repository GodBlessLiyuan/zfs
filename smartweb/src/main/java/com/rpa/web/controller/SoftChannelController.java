package com.rpa.web.controller;

import com.rpa.web.dto.SoftChannelDTO;
import com.rpa.web.service.ISoftChannelService;
import com.rpa.web.utils.DTPageInfo;
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
    public DTPageInfo<SoftChannelDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                            @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                            @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                            @RequestParam(value = "channelName") String channelName) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("name", channelName);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("queryAll")
    public List<SoftChannelDTO> queryAll() {
        return service.queryAll();
    }

    @RequestMapping("insert")
    public int insert(@RequestParam(value = "channelName") String channelName,
                      @RequestParam(value = "extra") String extra) {
        return service.insert(channelName, extra);
    }
}
