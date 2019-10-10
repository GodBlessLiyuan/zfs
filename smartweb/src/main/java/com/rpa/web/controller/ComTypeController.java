package com.rpa.web.controller;

import com.rpa.web.dto.ComTypeDTO;
import com.rpa.web.service.IComTypeService;
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
 * @date: Created in 2019/9/25 9:49
 * @description: 产品列表
 * @version: 1.0
 */
@RequestMapping("comtype")
@RestController
public class ComTypeController {

    @Autowired
    private IComTypeService service;

    @RequestMapping("query")
    public DTPageInfo<ComTypeDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                        @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                        @RequestParam(value = "username") String username) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("username", username);

        return service.query(draw, pageNum, pageSize, reqData);
    }

    @RequestMapping("queryAll")
    public List<ComTypeDTO> queryAll() {
        return service.queryAll();
    }

    @RequestMapping("insert")
    public void insert(@RequestParam(value = "name") String name,
                       @RequestParam(value = "days") int days,
                       @RequestParam(value = "extra") String extra) {
        // TODO: 从Session里获取管理员Id
        int aId = 1;

        service.insert(name, days, extra, aId);
    }
}
