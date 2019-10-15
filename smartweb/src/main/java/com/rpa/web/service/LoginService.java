package com.rpa.web.service;

import com.rpa.web.vo.ResultVO;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author: dangyi
 * @date: Created in 16:48 2019/10/12
 * @version: 1.0.0
 * @description:
 */
public interface LoginService {
    String login(HttpSession session, Map<String, Object> result, String username, String password, String checkcode);

    ResultVO updatePassword(HttpSession httpSession, String oldPassword, String newPassword);
}