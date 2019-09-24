package com.rpa.web.controller;

import com.google.gson.Gson;
import com.rpa.web.pojo.User;
import com.rpa.web.service.IUserService;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/query")
    public String query(User user){
        List<User> users = service.list(user);
        return new Gson().toJson(users).toString();
    }
}
