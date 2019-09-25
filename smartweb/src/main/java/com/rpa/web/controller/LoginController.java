package com.rpa.web.controller;

import com.rpa.web.exception.PasswordErrorException;
import com.rpa.web.exception.UserNotExistsException;
import com.rpa.web.pojo.ResultInfo;
import com.rpa.web.pojo.UserPO;
import com.rpa.web.service.LoginService;
import com.rpa.web.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return ResultInfo
     */
    @PostMapping("login")
    @ResponseBody
    public ResultInfo login(HttpSession session,
                            @RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @RequestParam("check") String userCheckCode) {
        ResultInfo resultInfo = null;

        /**
         * 比对验证码
         * 获取服务器端生成的验证码
         * 获取用户输入的验证码，然后比对
         */
        String serverCheckCode = (String) session.getAttribute("CHECKCODE_SERVER");
        if (!serverCheckCode.equalsIgnoreCase(userCheckCode)) {
            // 验证失败
            resultInfo = new ResultInfo(false, null, "验证码错误");
        } else {
            // 验证码通过，才开始去验证用户名和密码信息
            resultInfo = checkUsernameAndPassword(session, username, password);
        }
        return resultInfo;
    }

    /**
     * 验证用户名和密码信息
     *
     * @return
     */
    public ResultInfo checkUsernameAndPassword(HttpSession session, String username, String password) {

        ResultInfo resultInfo = null;

        try {
            // 加密密码，之后再去数据库对比
            password = Md5Util.encodeByMd5(password);

            // 调用业务进行登录
            UserPO loginUser = loginService.login(username, password);

            // 登录成功，返回成功信息
            if (loginUser != null) {
                // 登录成功，将登录用户数据写入session
                session.setAttribute("loginUser", loginUser);
                resultInfo = new ResultInfo(true, null, null);
            }

        } catch (PasswordErrorException e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, e.getMessage());
        } catch (UserNotExistsException e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultInfo = new ResultInfo(false, null, "服务器忙");
        }
        return resultInfo;
    }
}
