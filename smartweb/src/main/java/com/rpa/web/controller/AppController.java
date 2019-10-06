package com.rpa.web.controller;

import com.rpa.web.dto.AppDTO;
import com.rpa.web.service.IAppService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/10/1 13:48
 * @description: 版本更新
 * @version: 1.0
 */
@RestController
public class AppController {

    @Autowired
    private IAppService service;

    @RequestMapping("/appversion/query")
    public DTPageInfo<AppDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                    @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                    @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                    @RequestParam(value = "updateType") byte updateType) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("updateType", updateType);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("/appversion/insert")
    public int insert(@RequestParam(value = "url") String url,
                      @RequestParam(value = "updateType") byte updateType,
                      @RequestParam(value = "softChannel") int[] softChannel,
                      @RequestParam(value = "context") String context,
                      @RequestParam(value = "extra") String extra) {

        return service.insert(url, updateType, softChannel, context, extra);
    }

    @RequestMapping("/appversion/updateStatus")
    public int updateStatus(@RequestParam(value = "appId") int appId,
                            @RequestParam(value = "status") int status) {
        return service.updateStatus(appId, status);
    }
}
