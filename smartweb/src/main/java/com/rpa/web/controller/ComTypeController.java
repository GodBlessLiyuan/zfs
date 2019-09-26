package com.rpa.web.controller;

import com.rpa.web.dto.ComTypeDTO;
import com.rpa.web.pojo.AdminUserPO;
import com.rpa.web.service.IComTypeService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiahui
 * @date: Created in 2019/9/25 9:49
 * @description: 产品列表
 * @version: 1.0
 */
@RestController
public class ComTypeController {

    @Resource
    private IComTypeService service;

    @RequestMapping("/comtype/insert")
    public void insert(@RequestParam(value = "name") String name, @RequestParam(value = "days") int days,
                       @RequestParam(value = "extra") String extra, HttpSession session) {

        // 从Session里获取管理员Id
        AdminUserPO loginUser = (AdminUserPO) session.getAttribute("loginUser");

        service.insert(name, days, extra, loginUser.getaId());
    }

    @RequestMapping("/comtype/query")
    public DTPageInfo<ComTypeDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                        @RequestParam(value = "start", defaultValue = "1") int pageNum,
                                        @RequestParam(value = "length", defaultValue = "10") int pageSize,
                                        @RequestParam(value = "username") String username) {

        Map<String, Object> reqData = new HashMap<>(1);
        reqData.put("username", username);

        DTPageInfo<ComTypeDTO> dtodtPageInfo = service.query(draw, pageNum, pageSize, reqData);

        return dtodtPageInfo;
    }
}
