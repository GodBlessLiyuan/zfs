package com.rpa.web.controller;

import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.exception.PasswordErrorException;
import com.rpa.web.exception.UserNotExistsException;
import com.rpa.web.pojo.AdminUserPO;
import com.rpa.web.pojo.ResultInfo;
import com.rpa.web.service.AdminUserService;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.utils.Md5Util;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("admin")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    /**
     * 账号管理——查询
     * @param draw
     * @param pageNum
     * @param pageSize
     * @param phone
     * @param extra
     * @return
     */
    @GetMapping("query")
    public DTPageInfo<AdminUserDTO> query(@RequestParam(value = "draw", defaultValue = "1") int draw,
                                          @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                          @RequestParam(value = "phone", required = false) String phone,
                                          @RequestParam(value = "extra", required = false) Byte extra
    ) {

        // 调用业务层，返回页面结果
        DTPageInfo<AdminUserDTO> dTPageInfo = adminUserService.query(draw, pageNum, pageSize, phone, extra);
        return dTPageInfo;
    }

    /**
     * 账号管理——插入
     * @param
     * @return
     */
    @PostMapping("insert")
    public ResultVO insert(AdminUserDTO adminUserDTO, HttpSession httpSession) {
        return this.adminUserService.insert(adminUserDTO, httpSession);
    }

    /**
     * 账号管理——修改
     * @param
     * @return
     */
    @PostMapping("update")
    public ResultVO update(AdminUserDTO adminUserDTO, HttpSession httpSession) {
        return this.adminUserService.update(adminUserDTO, httpSession);
    }

    /**
     * 账号管理——删除
     * @param
     * @return
     */
    @PostMapping("delete")
    public ResultVO delete(int aId) {
        return this.adminUserService.delete(aId);
    }


    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return ResultInfo
     */
    @PostMapping("login")
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
            AdminUserPO loginUser = adminUserService.login(username, password);

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

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param httpSession
     * @return
     */
    public ResultVO updatePassword (HttpSession httpSession,
                                    @RequestParam("oldPassword") String oldPassword,
                                    @RequestParam("newPassword") String newPassword){
        return this.adminUserService.updatePassword(httpSession, oldPassword, newPassword);
    }

}
