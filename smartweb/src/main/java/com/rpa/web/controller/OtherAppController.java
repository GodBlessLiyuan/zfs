package com.rpa.web.controller;

import com.rpa.web.dto.OtherAppDTO;
import com.rpa.web.service.IOtherAppService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/29 20:01
 * @description: 其他产品
 * @version: 1.0
 */
@RestController
public class OtherAppController {

    @Resource
    private IOtherAppService service;

    @RequestMapping("/otherapp/query")
    public DTPageInfo<OtherAppDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                         @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                         @RequestParam(value = "name") String name) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("name", name);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("/otherapp/insert")
    public int insert(@RequestParam(value = "oName") String oName,
                      @RequestParam(value = "extra") String extra,
                      @RequestParam(value = "iconUrl") String iconUrl,
                      @RequestParam(value = "downloadType") byte downloadType,
                      @RequestParam(value = "appUrl") String appUrl) {

        return service.insert(oName, extra, iconUrl, downloadType, appUrl);
    }

    @RequestMapping("/otherapp/delete")
    public int delete(@RequestParam(value = "oId") int oId) {
        return service.delete(oId);
    }
}
