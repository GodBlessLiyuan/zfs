package com.rpa.web.service.impl;

import com.rpa.web.exception.PasswordErrorException;
import com.rpa.web.exception.UserNotExistsException;
import com.rpa.web.mapper.LoginMapper;
import com.rpa.web.pojo.UserPO;
import com.rpa.web.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public UserPO login(String username, String password) throws Exception{

        //判断用户名是否存在
        UserPO loginUser = loginMapper.getUserByUserName(username);
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
