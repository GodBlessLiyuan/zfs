package com.rpa.web.controller;

import com.rpa.web.dto.PluginDTO;
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
    public DTPageInfo<PluginDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                       @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                       @RequestParam(value = "aId", defaultValue = "0") int aId) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("aId", aId);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("/plugin/insert")
    public int insert(@RequestParam(value = "url") String url,
                      @RequestParam(value = "appId") int appId,
                      @RequestParam(value = "softChannel") int[] softChannel,
                      @RequestParam(value = "context") String context,
                      @RequestParam(value = "extra") String extra) {

        return service.insert(url, appId, softChannel, context, extra);
    }

    @RequestMapping("/plugin/updateStatus")
    public int updateStatus(@RequestParam(value = "pluginId") int pluginId,
                            @RequestParam(value = "status") byte status) {
        return service.updateStatus(pluginId, status);
    }

    @RequestMapping("/plugin/delete")
    public int delete(@RequestParam(value = "pluginId") int pluginId) {
        return service.delete(pluginId);
    }
}
