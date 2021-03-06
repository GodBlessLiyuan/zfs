package com.zfs.web.controller;

import com.zfs.web.dto.AdminUserDTO;
import com.zfs.web.utils.Md5Util;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.zfs.common.constant.Constant.ADMIN_USER;

@Controller
public class MainController {
    @GetMapping("main")
    public String main(HttpSession session){
        if(session == null || session.getAttribute(ADMIN_USER) == null){
            return "redirect:/login";
        }
        return "main";
    }

    @RequestMapping("/login")
    public String index(HttpServletRequest request){
        boolean flag = isLogin(request);
        if (flag) {
            return "redirect:/main";
        }
        return "index";
    }

    @RequestMapping("/")
    public String root(HttpServletRequest request){
        boolean flag = isLogin(request);
        if (flag) {
            return "redirect:/main";
        }
        return "index";
    }


    /**
     * 对登录状态进行判定，以决定用户是否可以免予登录
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request) {

        // 获取到前端传来的、保存在cookie中的用户信息
        Cookie[] cookies = request.getCookies();

        // 首次登录，无cookie可携带
        if (null == cookies) {
            return false;
        }

        String userInfo = "";
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userInfo")) {
                userInfo = cookie.getValue();
            }
        }

        // 获取后台的session对象，其中保存有用户信息，前后进行比对
        HttpSession session = request.getSession();

        String realUserInfo = "";
        if (session == null || session.getAttribute(ADMIN_USER) == null) {
            return false;
        } else {
            AdminUserDTO dto = (AdminUserDTO) session.getAttribute(ADMIN_USER);
            try {
                realUserInfo = Md5Util.encodeByMd5(dto.getUsername() + dto.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return userInfo.equals(realUserInfo);
        }
    }
}
