package com.rpa.web.service;

import com.rpa.web.pojo.UserPO;

public interface LoginService {
    UserPO login(String username, String password) throws Exception;
}
