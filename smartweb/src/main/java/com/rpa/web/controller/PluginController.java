package com.rpa.web.controller;

import com.rpa.web.dto.AppDTO;
import com.rpa.web.service.IPluginService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/6 20:09
 * @description: 插件更新
 * @version: 1.0
 */
@RestController
public class PluginController {

    @Autowired
    private IPluginService service;


    @RequestMapping("/plugin/query")
    public DTPageInfo<AppDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                    @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                    @RequestParam(value = "userId") int userId) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("userId", userId);

        return null;
    }

    @RequestMapping("/plugin/insert")
    public int insert(@RequestParam(value = "url") String url,
                      @RequestParam(value = "appId") int appId,
                      @RequestParam(value = "softChannel") int[] softChannel,
                      @RequestParam(value = "context") String context,
                      @RequestParam(value = "extra") String extra) {

        return service.insert(url, appId, softChannel, context, extra);
    }
}
