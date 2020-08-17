package com.zfs.web.controller;
import com.zfs.web.service.ISoftChannelService;
import com.zfs.common.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @PostMapping("query")
    public ResultVO query(
               @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
               @RequestParam(value = "channelName",required = false) String channelName) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("name", channelName);

        return service.query(pageNum, pageSize, reqData);
    }

    @PostMapping("queryAll")
    public ResultVO queryAll() {
        return new ResultVO(1000,service.queryAll());
    }

    @PostMapping("insert")
    public ResultVO insert(@RequestParam(value = "channelName") String channelName,
                           @RequestParam(value = "extra") String extra) {
        return service.insert(channelName, extra);
    }
}
