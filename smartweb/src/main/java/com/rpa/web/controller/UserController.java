package com.rpa.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rpa.web.pojo.User;
import com.rpa.web.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 10:07
 * @description: TODO
 * @version: 1.0
 */
@RestController
public class UserController {

    @Resource
    private IUserService service;

    @RequestMapping("/list")
    public PageInfo<User> list(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo, @RequestParam(value = "pageSize", defaultValue = "10")int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        List<User> list = service.list();
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
