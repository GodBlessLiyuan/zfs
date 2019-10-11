package com.rpa.web.service.impl;

import com.rpa.web.enumeration.ExceptionEnum;
import com.rpa.web.exception.PasswordErrorException;
import com.rpa.web.exception.UserNotExistsException;
import com.rpa.web.mapper.AdminUserMapper;
import com.rpa.web.pojo.AdminUserPO;
import com.rpa.web.service.AdminUserService;
import com.rpa.web.utils.ResultVOUtil;
import com.rpa.web.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class AdminUserServiceImpl implements AdminUserService {

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

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param httpSession
     * @return
     */
    @Override
    public ResultVO updatePassword(HttpSession httpSession, String oldPassword, String newPassword) {

        // 先从session中获取当前用户的a_id
        // 能从session中获取用户的信息，说明当前用户是登录状态
        int aId = (int)httpSession.getAttribute("aId");

        // 对输入的旧密码进行校验，以确保的确是用户本人在进行修改密码操作
        String password = this.adminUserMapper.queryPassword(aId);
        if (oldPassword.equals(password)) {
            int count = this.adminUserMapper.updatePassword(aId, newPassword);
            if (count == 1) {
                return ResultVOUtil.success();
            }
            return ResultVOUtil.error(ExceptionEnum.PASSWORD_UPDATE_ERROR);
        }
        return ResultVOUtil.error(ExceptionEnum.PASSWORD_ERROR);
    }
}
