package com.zfs.web.controller;

import com.zfs.common.vo.ResultVO;
import com.zfs.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("query")
    public ResultVO query(
            @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
    ) {
        // 调用业务层，返回页面结果
        return roleService.query(pageNum, pageSize);
    }
}