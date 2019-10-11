package com.rpa.web.service;

import com.rpa.web.dto.AdminUserDTO;
import com.rpa.web.pojo.AdminUserPO;
import com.rpa.web.utils.DTPageInfo;
import com.rpa.web.vo.ResultVO;

import javax.servlet.http.HttpSession;

public interface AdminUserService {
    AdminUserPO login(String username, String password) throws Exception;

    ResultVO updatePassword(HttpSession httpSession, String oldPassword, String newPassword);

    DTPageInfo<AdminUserDTO> query(int draw, int pageNum, int pageSize, String phone, Byte extra);

    ResultVO insert(AdminUserDTO adminUserDTO, HttpSession httpSession);

    ResultVO update(AdminUserDTO adminUserDTO, HttpSession httpSession);

    ResultVO delete(int aId);
}
