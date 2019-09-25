package com.rpa.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.rpa.web.pojo.UserPO;
import com.rpa.web.service.IUserService;
import com.rpa.web.utils.DTPageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: xiahui
 * @date: Created in 2019/9/24 15:05
 * @description: 用户信息
 * @version: 1.0
 */
@RestController
public class UserController {
    @Resource
    private IUserService service;

    @RequestMapping("/user/list")
    public DTPageInfo<UserPO> list(@RequestParam(value = "draw", defaultValue = "1") int draw, @RequestParam(value =
            "start", defaultValue = "1") int pageNum,
                                 @RequestParam(value = "length", defaultValue = "10") int pageSize) {
        Page<UserPO> page = PageHelper.startPage(pageNum, pageSize);

        List<UserPO> data = service.list();

        return new DTPageInfo<>(draw, page.getTotal(), data);
    }
}
