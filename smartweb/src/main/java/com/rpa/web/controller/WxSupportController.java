package com.rpa.web.controller;

import com.rpa.web.dto.WxSupportDTO;
import com.rpa.web.service.IWxSupportService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/30 11:10
 * @description: 微信白名单
 * @version: 1.0
 */
@RestController
public class WxSupportController {

    @Resource
    private IWxSupportService service;

    @RequestMapping("/wxsupport/query")
    public DTPageInfo<WxSupportDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                          @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                          @RequestParam(value = "packageName") String name) {
        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("name", name);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("/wxsupport/insert")
    public int insert(@RequestParam(value = "packageName") String packageName,
                      @RequestParam(value = "extra") String extra) {
        return service.insert(packageName, extra);
    }

    @RequestMapping("/wxsupport/delete")
    public int delete(@RequestParam(value = "wId") int wId) {
        return service.delete(wId);
    }
}
