package com.rpa.web.service;

import com.rpa.web.pojo.AdminUserPO;
import com.rpa.web.vo.ResultVO;

import javax.servlet.http.HttpSession;

public interface AdminUserService {
    AdminUserPO login(String username, String password) throws Exception;

    ResultVO updatePassword(HttpSession httpSession, String oldPassword, String newPassword);
}
