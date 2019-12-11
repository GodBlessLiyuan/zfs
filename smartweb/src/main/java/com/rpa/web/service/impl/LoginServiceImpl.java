package com.rpa.web.service.impl;

import com.rpa.web.common.Constant;
import com.rpa.common.dto.AdminUserDTO;
import com.rpa.common.mapper.AdminUserMapper;
import com.rpa.common.pojo.AdminUserPO;
import com.rpa.web.service.LoginService;
import com.rpa.web.utils.Md5Util;
import com.rpa.common.vo.ResultVO;
import com.rpa.web.utils.OperatorUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

import static com.rpa.web.common.Constant.ADMIN_USER;

/**
 * @author: dangyi
 * @date: Created in 16:49 2019/10/12
 * @version: 1.0.0
 * @description:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public String login(HttpSession session, HttpServletResponse response, Map<String, Object> result,
                        String username, String password, String checkcode) {

        // 先校验验证码，从session中获取服务器端生成并传递给前端的验证码
        String serverCheckcode = (String) session.getAttribute("CHECKCODE_SERVER");

        AdminUserPO po = adminUserMapper.queryUserByUsername(username);

        try {
            // 对密码加密，之后再去数据库对比
            password = Md5Util.encodeByMd5(Constant.SALT + password);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("flag", false);
            result.put("msg", "MD5异常");
            return "forward:login";
        }

        // 将用户输入的验证码与服务器生成的进行比对
        if (serverCheckcode == null) {
            result.put("flag", false);
            result.put("msg", "服务器异常，请联系管理员！");
            return "forward:/login";
        }

        else if (!serverCheckcode.equalsIgnoreCase(checkcode)) {
            // 验证码校验失败
            result.put("flag", false);
            result.put("msg", "验证码输入错误，请重新输入！");
            return "forward:/login";
        }

        //判断用户名是否存在
        else if (po == null) {
            result.put("flag", false);
            result.put("msg", "用户名不存在");
            return "forward:/login";
        }

        //判断密码是否正确
        else if (!po.getPassword().equals(password)) {
            result.put("flag", false);
            result.put("msg", "密码错误");
            return "forward:/login";
        }

        else {
            // 登录成功，将登录用户数据写入session
            // 考虑到将来要被其他模块引用，这里将 po 转换为 dto，将 dto 存入session
            AdminUserDTO dto = new AdminUserDTO();
            dto.setaId(po.getaId());
            dto.setUsername(po.getUsername());
            dto.setPassword(po.getPassword());
            dto.setEmail(po.getEmail());
            dto.setIsLock(po.getIsLock());
            dto.setCreateTime(po.getCreateTime());
            dto.setLastTime(po.getLastTime());
            dto.setDr(po.getDr());
            dto.setRoleId(po.getRoleId());
            dto.setName(po.getName());
            dto.setExtra(po.getExtra());
            dto.setPhone(po.getPhone());

            session.setAttribute(ADMIN_USER, dto);
            session.setMaxInactiveInterval(60 * 60 * 2);

            // 供前端取用
            session.setAttribute("username", username);
            session.setAttribute("roleId", po.getRoleId());



            /**
             * 获取服务器自动为session对象所生成的ID
             * 创建cookie对象，名为JSESSIONID，值为该ID
             * 设置该cookie对象的有效时间，与session的有效时间保持一致
             * 创建cookie对象，名为userInfo，值为用户名和密码的拼接值，并且进行MD5加密
             * 将这这两个cookie对象发送给浏览器
             */
            String id = session.getId();
            Cookie cookie = new Cookie("JSESSIONID", id);
            cookie.setMaxAge(60 * 60 * 2);

            String userInfo = "";
            try {
                userInfo = Md5Util.encodeByMd5(username + password);
            } catch (Exception e) {
                e.printStackTrace();
                result.put("flag", false);
                result.put("msg", "MD5异常");
                return "forward:/login";
            }
            Cookie userInfoCookie = new Cookie("userInfo", userInfo);
            userInfoCookie.setMaxAge(60 * 60 * 2);

            response.addCookie(cookie);
            response.addCookie(userInfoCookie);

            return "redirect:/main";
        }
    }



    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO updatePassword(HttpSession httpSession, String oldPassword, String newPassword) {

        // 获取当前用户ID
        int aId = OperatorUtil.getOperatorId(httpSession);

        // 保存在数据库中的密码
        String password = this.adminUserMapper.queryPassword(aId);

        try {
            oldPassword = Md5Util.encodeByMd5(Constant.SALT + oldPassword);
            newPassword = Md5Util.encodeByMd5(Constant.SALT + newPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 校验：用户输入的旧密码与数据库比对
        if (oldPassword.equals(password)) {
            this.adminUserMapper.updatePassword(aId, newPassword);
            return new ResultVO(1000);
        }
        return new ResultVO(1004);
    }


    /**
     * 退出登录
     * @param httpSession
     * @return
     */
    @Override
    public String logout(HttpSession httpSession) {

        httpSession.setAttribute(ADMIN_USER, null);

        return "redirect:/login";
    }

}
