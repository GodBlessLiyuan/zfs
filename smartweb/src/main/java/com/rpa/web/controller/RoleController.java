package com.rpa.web.controller;

import com.rpa.web.dto.RoleDTO;
import com.rpa.web.service.RoleService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: dangyi
 * @date: Created in 9:42 2019/10/11
 * @version: 1.0.0
 * @description:
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询
     *
     * @param draw
     * @param start
     * @param length
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<RoleDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                     @RequestParam(value = "start", defaultValue = "1") int start,
                                     @RequestParam(value = "length", defaultValue = "10") int length
    ) {
        // 调用业务层，返回页面结果
        DTPageInfo<RoleDTO> dTPageInfo = roleService.query(draw, start, length);
        return dTPageInfo;
    }
}