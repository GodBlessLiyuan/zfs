package com.zfs.web.service;

import com.zfs.web.dto.AdminUserDTO;
import com.zfs.common.vo.ResultVO;

import javax.servlet.http.HttpSession;

public interface AdminUserService {

    ResultVO query(int start, int length, String phone, String extra);

    ResultVO insert(AdminUserDTO adminUserDTO, HttpSession httpSession);

    ResultVO update(AdminUserDTO adminUserDTO, HttpSession httpSession);

    ResultVO delete(Integer aId);

    ResultVO queryAllRoles();

    ResultVO queryById(Integer aId);
}
