package com.rpa.web.service.impl;

import com.rpa.web.exception.PasswordErrorException;
import com.rpa.web.exception.UserNotExistsException;
import com.rpa.web.mapper.AdminUserMapper;
import com.rpa.web.pojo.AdminUserPO;
import com.rpa.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUserPO login(String username, String password) throws Exception{

        //判断用户名是否存在
        AdminUserPO loginUser = adminUserMapper.getUserByUserName(username);
        if(loginUser==null){
            throw new UserNotExistsException("用户名不存在");
        }
        //判断密码是否正确
        if(!loginUser.getPassword().equals(password)){
            throw new PasswordErrorException("密码错误");
        }

        return loginUser;
    }
}
