package com.rpa.web.service;

import com.rpa.web.pojo.AdminUserPO;

public interface LoginService {
    AdminUserPO login(String username, String password) throws Exception;
}
